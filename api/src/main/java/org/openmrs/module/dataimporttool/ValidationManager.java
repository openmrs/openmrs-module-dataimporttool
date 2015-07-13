package org.openmrs.module.dataimporttool;

import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import org.openmrs.module.dataimporttool.dmt.helper.EventCodeContants;
import org.openmrs.module.dataimporttool.dmt.helper.MatchConstants;
import org.openmrs.module.dataimporttool.dmt.helper.ProcessPhases;
import org.openmrs.module.dataimporttool.dmt.helper.SystemException;
import org.openmrs.module.dataimporttool.dmt.util.KeyPoolUtil;
import org.openmrs.module.dataimporttool.dmt.util.MatchBuilder;
import org.openmrs.module.dataimporttool.dmt.util.ReferenceBuilder;
import org.openmrs.module.dataimporttool.dmt.util.TupleBuilder;
import org.openmrs.module.dataimporttool.dmt.util.TupleTree;
import org.openmrs.module.dataimporttool.dmt.util.datatypemapping.DatatypeMappingReader;
import org.openmrs.module.dataimporttool.dmt.util.log.Error;
import org.openmrs.module.dataimporttool.dmt.util.log.Event;
import org.openmrs.module.dataimporttool.dmt.util.log.EventCode;
import org.openmrs.module.dataimporttool.dmt.util.log.Info;
import org.openmrs.module.dataimporttool.dmt.util.log.LogIt;
import org.openmrs.module.dataimporttool.dmt.util.log.LogWriter;
import org.openmrs.module.dataimporttool.dmt.util.log.Warning;
import org.openmrs.module.dataimporttool.dmt.xls.Sheets;
import org.openmrs.module.dataimporttool.dmt.xls.XlsProcessor;
import org.openmrs.module.dataimporttool.matchingschema.MatchType;
import org.openmrs.module.dataimporttool.matchingschema.ReferenceType;
import org.openmrs.module.dataimporttool.matchingschema.ReferencedPart;
import org.openmrs.module.dataimporttool.matchingschema.TupleType;

/**
 * The manager that performs the validation of the matches
 */
public class ValidationManager implements LogIt {

	private static final long serialVersionUID = 1L;
	private XlsProcessor processor;
	private LogWriter writer;
	private DatatypeMappingReader dmr;
	private TupleTree tree;
	private EventCode eventCode;
	private final Map<Integer, TupleType> LINEAR_TUPLES = new HashMap<Integer, TupleType>();// allow to order and access tuples 													// in a linear fashion
																							// 
	// counters for log report
	private int warningCount, tupleCount, matchCount, leftRefCount,
			rightRefCount = 0;

	/**
	 * Parameterized constructor
	 * @param processor
	 * @param writer
	 * @param dmr
	 * @param eventCode
	 * @param keyPool
	 */
	public ValidationManager(final XlsProcessor processor,
			final LogWriter writer, final DatatypeMappingReader dmr,
			final EventCode eventCode) {
		this.processor = processor;
		this.writer = writer;
		this.dmr = dmr;
		this.eventCode = eventCode;
	}

	/**
	 * Default constructor
	 */
	public ValidationManager() {
		processor = new XlsProcessor();
		writer = LogWriter.getWriter();
		dmr = new DatatypeMappingReader();
		dmr.process();
		eventCode = new EventCode();
	}

	/**
	 * Executes the validation logic
	 * 
	 * @return
	 * @throws SystemException
	 */
	public boolean execute() throws SystemException {

		// log start of process
		writeSimpleInfoLog(ProcessPhases.VALIDATION,
				eventCode.getString(EventCodeContants.INF001));
		writeSimpleInfoLog(null,
				eventCode.getString(EventCodeContants.SEPARATOR));

		TupleBuilder tupleBuilder = new TupleBuilder();
		final Map<Integer, MatchType> matches = new HashMap<Integer, MatchType>();// hold
																					// all
																					// the
																					// matches
		// read tuple
		for (int i = Sheets.TUPLE.ROW_START; i < processor
				.getSize(Sheets.TUPLE.INDEX); i++) {
			// set tuple values
			createTuple(tupleBuilder, i);
			LINEAR_TUPLES.put(tupleBuilder.getTuple().getId(),
					tupleBuilder.getTuple());// for validation purposes only
			tupleCount++;// keep counting the number of tuples affected
		}
		// process and validate tuple matches
		for (int j = Sheets.MATCH_L_TO_R.ROW_START; j < processor
				.getSize(Sheets.MATCH_L_TO_R.INDEX); j++) {
			// create match and add to tuple
			MatchBuilder matchBuilder = new MatchBuilder();
			createMatch(matchBuilder, j);

			// create left and right sides of match
			createMatchSides(matchBuilder, j);

			// validate default value
			if (!validateDefaultValueOfMatch(matchBuilder.getMatch()))
				return false;

			// check if match has right side
			if (matchBuilder.getMatch().getRight() == null) {
				if (!validateDefaultValueWithoutRightSideOfMatch(matchBuilder
						.getMatch()))
					return false;
			} else {
				// check if left side is required and right is not
				if (!validateDefaultValueWithRightSideOfMatch(matchBuilder
						.getMatch()))
					return false;

				// check size compatibility
				validateSizeOfMatch(matchBuilder.getMatch());
			}
			// check datatype compatibility
			if (!validateDatatypeCompatibility(matchBuilder.getMatch())) {
				return false;
			}
			// check primary key conditions
			if (!validatePk(matchBuilder.getMatch())) {
				return false;
			}
			// add match to map of matches
			matches.put(matchBuilder.getMatch().getId(),
					matchBuilder.getMatch());
			// add match to tuple
			try {
			tupleBuilder.process()
					.getTree(matchBuilder.getMatch().getTupleId()).getHead()
					.getMatches().add(matchBuilder.getMatch());
			} catch (Throwable t) {
				System.err.println("An error occured processing the match in line: " + matchCount);
				t.printStackTrace();
				throw t;
			}

			matchCount++;// keep counting the number of matches affected
		}
		// create and validate left references
		for (int row = Sheets.REFERENCES.ROW_START; row < processor
				.getSize(Sheets.REFERENCES.INDEX_L); row++) {

			// create left reference
			ReferenceBuilder referenceBuilder = new ReferenceBuilder();

			createReference(referenceBuilder, row, Sheets.REFERENCES.INDEX_L);

			Integer tupleId = null;
			
			try {
				tupleId = Integer.valueOf(processor.process(
						Sheets.REFERENCES.INDEX_L,
						Sheets.REFERENCES.TUPLE_MATCH_ID, row));
			} catch (Throwable t) {
				System.err
						.println("A processing error occured after L-References in line: "
								+ (leftRefCount + 1));
				t.printStackTrace();
				throw t;
			}

			TupleType tuple = tupleBuilder.process().getTree(tupleId).getHead();
			// validate referenced value
			if (!validateLReferenceSequenceValue(
					referenceBuilder.getReference(), tuple, row,
					Sheets.REFERENCES.INDEX_L, Sheets.REFERENCES.NAME_L,
					tuple.getTable())) {
				return false;
			}
			// add reference to tuple
			tuple.getReferences().put(referenceBuilder.getReference().getId(),
					referenceBuilder.getReference());
			leftRefCount++;// keep counting the number of matches affected
		}
		// create and validate right references
		for (int row = Sheets.REFERENCES.ROW_START; row < processor
				.getSize(Sheets.REFERENCES.INDEX_R); row++) {

			// create right reference
			ReferenceBuilder referenceBuilder = new ReferenceBuilder();
			// retrieve match that this reference is part of
			Integer matchId = null;
			try {
				matchId = Integer.valueOf(processor.process(
						Sheets.REFERENCES.INDEX_R,
						Sheets.REFERENCES.TUPLE_MATCH_ID, row));
			} catch (Throwable t) {
				System.err
						.println("A processing error occured after R-References in line: "
								+ (rightRefCount + 1));
				t.printStackTrace();
				throw t;
			}

			MatchType match = matches.get(matchId);

			createReference(referenceBuilder, row, Sheets.REFERENCES.INDEX_R);

			// validate referenced value
			if (!validateLReferenceSequenceValue(
					referenceBuilder.getReference(), match, row,
					Sheets.REFERENCES.INDEX_R, Sheets.REFERENCES.NAME_R,
					(match.getRight() == null) ? MatchConstants.NA : match
							.getRight().getTable())) {
				return false;
			}
			// add reference to match
			match.getReferences().put(referenceBuilder.getReference().getId(),
					referenceBuilder.getReference());
			rightRefCount++; // keep counting the number of matches affected
		}
		// update the tree
		tree = tupleBuilder.process();
		// validate the structure of all the tuples after composed
		if (!validateTupleStructure()) {
			return false;
		}
		// log end of process
		logEndOfProcess();

		return true;
	}

	/**
	 * Validates the datatype size compatibility between the two sides
	 * 
	 * @param match
	 */
	private void validateSizeOfMatch(MatchType match) {
		if (match.getLeft().getSize().intValue() < match.getRight().getSize()
				.intValue()) {
			// write warning log
			writer.writeLog(new Warning(eventCode
					.getString(EventCodeContants.WAR002),
					ProcessPhases.VALIDATION, Calendar.getInstance().getTime(),
					EventCodeContants.WAR002, match.getTupleId(),
					match.getId(), Sheets.MATCH_L_TO_R.NAME));
			warningCount++;
		}

	}

	/**
	 * Write the log report at the end of the validation process
	 */
	public void logEndOfProcess() {
		writeSimpleInfoLog(null,
				eventCode.getString(EventCodeContants.SEPARATOR));
		// check if there is any warning
		if (warningCount > 0) {
			writeSimpleInfoLog(ProcessPhases.VALIDATION,
					eventCode.getString(EventCodeContants.INF003));
		} else {
			writeSimpleInfoLog(ProcessPhases.VALIDATION,
					eventCode.getString(EventCodeContants.INF002));
		}
		writeSimpleInfoLog(null,
				eventCode.getString(EventCodeContants.SEPARATOR));
		writeSimpleInfoLog(
				null,
				tupleCount + " "
						+ eventCode.getString(EventCodeContants.INF004));
		writeSimpleInfoLog(
				null,
				matchCount + " "
						+ eventCode.getString(EventCodeContants.INF005));
		writeSimpleInfoLog(
				null,
				warningCount + " "
						+ eventCode.getString(EventCodeContants.INF006));
	}

	/**
	 * Writes a simple text report containing the phase and message
	 * 
	 * @param phase
	 * @param text
	 */
	public void writeSimpleInfoLog(final String phase, final String text) {
		Event event = new Info();
		event.setFase(phase);
		event.setDescricao(text);
		writer.writeLog(event);
	}

	/**
	 * Create instance of {@link ReferenceType } using builder This method
	 * applies for L-References
	 * 
	 * @param referenceBuilder
	 * @param row
	 * @param sheetIndex
	 * @throws SystemException
	 */
	private void createReference(final ReferenceBuilder referenceBuilder,
			final int row, final int sheetIndex) throws SystemException {
		try {
			Integer referenceId = Integer.valueOf(processor.process(sheetIndex,
					Sheets.REFERENCES.ID, row));
			String datatype = processor.process(sheetIndex,
					Sheets.REFERENCES.DATATYPE, row);
			Integer size = Integer.valueOf(processor.process(sheetIndex,
					Sheets.REFERENCES.SIZE, row));
			String predecessorStr = processor.process(sheetIndex,
					Sheets.REFERENCES.SEQUENCE, row);
			Integer predecessor = (predecessorStr.equals(MatchConstants.NA)) ? 0
					: Integer.valueOf(predecessorStr);
			String nameDesc = processor.process(sheetIndex,
					Sheets.REFERENCES.NAME_DESC, row);
			// referencee side values
			String table_l = processor.process(sheetIndex,
					Sheets.REFERENCES.REFERENCE_TABLE, row);
			String column_l = processor.process(sheetIndex,
					Sheets.REFERENCES.REFERENCE_COLUMN, row);
			// referenced side values
			String table_r = processor.process(sheetIndex,
					Sheets.REFERENCES.REFERENCED_TABLE, row);
			String column_r = processor.process(sheetIndex,
					Sheets.REFERENCES.REFERENCED_COLUMN, row);
			// referenced value
			Object value = processor.process(sheetIndex,
					Sheets.REFERENCES.REFERENCED_VALUE, row);

			referenceBuilder
					.createReference(referenceId, datatype, size, nameDesc,
							predecessor)
					.createReferenceSide(table_r, column_r,
							ReferenceBuilder.REFERENCED)
					.createReferencedValue(value);
			// create left side if exist
			if (!table_l.equals(MatchConstants.NA)) {
				referenceBuilder.createReferenceSide(table_l, column_l,
						ReferenceBuilder.REFERENCEE);
			}
		} catch (Throwable t) {
			if (sheetIndex == 2) {
				System.err
						.println("A processing error occured after L-References in line: "
								+ (leftRefCount + 1));
			} else {
				System.err
						.println("A processing error occured after R-References in line: "
								+ (rightRefCount + 1));
			}

			t.printStackTrace();
			throw t;
		}
	}

	/**
	 * Validates the sequence of reference according to validation logic
	 * 
	 * @param reference
	 *            the reference to be validated
	 * @param part
	 *            the object to whom the reference belongs to. It can be { @link
	 *            MatchType } or { @link TupleType }
	 * @param row
	 *            the row in which the reference is located in the XLS file
	 * @param sheetIndex
	 *            the index of the sheet of the reference. It can be
	 *            L-References or R-References
	 * @param sheetName
	 *            the name of the sheet of the reference. It can be L-References
	 *            or R-References
	 * @param tableName
	 *            the name of the table in the part that should be used to know
	 *            whether the reference is direct or indirect
	 * @return
	 */
	private boolean validateLReferenceSequenceValue(
			final ReferenceType reference, final ReferencedPart part,
			final int row, final int sheetIndex, final String sheetName,
			final String tableName) {

		// A reference is direct if its reference table is equal to the table
		// of its part or N/A
		if (reference.getReferencee() == null) {

			// If a left reference is direct (the reference table is equal to
			// the tuple table)
			// then it should not have sequence value, an error must be logged
			// otherwise.
			if (!reference.getPredecessor().equals(Integer.valueOf(0))) {
				// write error log
				writer.writeLog(new Error(eventCode
						.getString(EventCodeContants.ERR008),
						ProcessPhases.VALIDATION, Calendar.getInstance()
								.getTime(), EventCodeContants.ERR008,
						Integer.valueOf(processor.process(sheetIndex,
								Sheets.REFERENCES.TUPLE_MATCH_ID, row)),
						Integer.valueOf(processor.process(sheetIndex,
								Sheets.REFERENCES.ID, row)), sheetName));
				return false;
			}
		} else {
			if (tableName
					.equalsIgnoreCase(reference.getReferencee().getTable())
					&& !reference.getPredecessor().equals(Integer.valueOf(0))) {
				// write error log
				writer.writeLog(new Error(eventCode
						.getString(EventCodeContants.ERR008),
						ProcessPhases.VALIDATION, Calendar.getInstance()
								.getTime(), EventCodeContants.ERR008,
						Integer.valueOf(processor.process(sheetIndex,
								Sheets.REFERENCES.TUPLE_MATCH_ID, row)),
						Integer.valueOf(processor.process(sheetIndex,
								Sheets.REFERENCES.ID, row)), sheetName));
				return false;
			}
			//indirect reference
			if (!tableName.equalsIgnoreCase(reference.getReferencee()
					.getTable()) && ! tableName.equalsIgnoreCase(MatchConstants.NA)) {
				if (reference.getPredecessor().equals(Integer.valueOf(0))) {
					// write error log
					writer.writeLog(new Error(eventCode
							.getString(EventCodeContants.ERR009),
							ProcessPhases.VALIDATION, Calendar.getInstance()
									.getTime(), EventCodeContants.ERR009,
							Integer.valueOf(processor.process(sheetIndex,
									Sheets.REFERENCES.TUPLE_MATCH_ID, row)),
							Integer.valueOf(processor.process(sheetIndex,
									Sheets.REFERENCES.ID, row)), sheetName));
					return false;
				}
				// The sequence of a left reference must belong to the same
				// tuple,
				// an error must be logged otherwise
				if (!part.getReferences().containsKey(
						reference.getPredecessor())) {
					// write error log
					writer.writeLog(new Error(eventCode
							.getString(EventCodeContants.ERR010),
							ProcessPhases.VALIDATION, Calendar.getInstance()
									.getTime(), EventCodeContants.ERR010,
							Integer.valueOf(processor.process(sheetIndex,
									Sheets.REFERENCES.TUPLE_MATCH_ID, row)),
							Integer.valueOf(processor.process(sheetIndex,
									Sheets.REFERENCES.ID, row)), sheetName));
					return false;
				}
				// The REFERENCE TABLE of an indirect reference must be the same
				// as
				// the REFERENCED TABLE of its sequence, an error must be logged
				// otherwise
				String indirectReferenceTable = reference.getReferencee()
						.getTable();
				String sequenceReferencedTable = part.getReferences()
						.get(reference.getPredecessor()).getReferenced()
						.getTable();
				if (!indirectReferenceTable.equals(sequenceReferencedTable)) {
					// write error log
					writer.writeLog(new Error(eventCode
							.getString(EventCodeContants.ERR011),
							ProcessPhases.VALIDATION, Calendar.getInstance()
									.getTime(), EventCodeContants.ERR011,
							Integer.valueOf(processor.process(sheetIndex,
									Sheets.REFERENCES.TUPLE_MATCH_ID, row)),
							Integer.valueOf(processor.process(sheetIndex,
									Sheets.REFERENCES.ID, row)), sheetName));
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * Creates instances of {@link TupleType } using a builder
	 * 
	 * @param tupleBuilder
	 * @param index
	 * @throws SystemException
	 */
	private void createTuple(final TupleBuilder tupleBuilder, final int index)
			throws SystemException {
		try {
			tupleBuilder.createTuple(
					Integer.valueOf(processor.process(Sheets.TUPLE.INDEX,
							Sheets.TUPLE.ID, index)),
					processor.process(Sheets.TUPLE.INDEX,
							Sheets.TUPLE.TERMINOLOGY, index),
					processor.process(Sheets.TUPLE.INDEX, Sheets.TUPLE.TABLE,
							index),
					processor.process(Sheets.TUPLE.INDEX, Sheets.TUPLE.DESC,
							index),
					(processor.process(Sheets.TUPLE.INDEX,
							Sheets.TUPLE.PREDECESSOR, index).equals(
							MatchConstants.NA) ? null : Integer
							.valueOf(processor.process(Sheets.TUPLE.INDEX,
									Sheets.TUPLE.PREDECESSOR, index))));
		} catch (Throwable t) {
			System.err
					.println("A processing error occured after Tuple in line: "
							+ (tupleCount + 1));
			t.printStackTrace();
			throw t;
		}

	}

	/**
	 * Creates instances of {@link MatchType } using a builder
	 * 
	 * @param matchBuilder
	 * @param row
	 */
	private void createMatch(final MatchBuilder matchBuilder, final int row) {
		try {
			Integer tupleId = Integer.valueOf(processor.process(
					Sheets.MATCH_L_TO_R.INDEX, Sheets.MATCH_L_TO_R.TUPLE_ID,
					row));
			Integer matchId = Integer.valueOf(processor.process(
					Sheets.MATCH_L_TO_R.INDEX, Sheets.MATCH_L_TO_R.MATCH_ID,
					row));
			String terminology = processor.process(Sheets.MATCH_L_TO_R.INDEX,
					Sheets.MATCH_L_TO_R.TERMINOLOGY, row);
			Object valueMatch = processor.process(Sheets.MATCH_L_TO_R.INDEX,
					Sheets.MATCH_L_TO_R.VALUE_MATCH, row);
			Object defaultValue = processor.process(Sheets.MATCH_L_TO_R.INDEX,
					Sheets.MATCH_L_TO_R.DEFAULT_VALUE, row);
			String pk = processor.process(Sheets.MATCH_L_TO_R.INDEX,
					Sheets.MATCH_L_TO_R.PK, row);
			String pool = processor.process(Sheets.MATCH_L_TO_R.INDEX,
					Sheets.MATCH_L_TO_R.POOL, row);
			// create the match using builder
			matchBuilder.createMatch(tupleId, matchId, terminology, valueMatch,
					defaultValue, pk, pool);
		} catch (Throwable t) {
			System.err
					.println("A processing error occured after Match-L-to-R in line: "
							+ (matchCount + 2));
			t.printStackTrace();
			throw t;
		}

	}

	/**
	 * Creates instances of {@link MatchSideType } using a builder
	 * 
	 * @param matchBuilder
	 * @param row
	 * @throws SystemException
	 */
	private void createMatchSides(final MatchBuilder matchBuilder, final int row)
			throws SystemException {
		try {
			// create left side of match
			String table_l = null;
			String column_l = processor.process(Sheets.MATCH_L_TO_R.INDEX,
					Sheets.MATCH_L_TO_R.COLUMN_L, row);
			String datatype_l = processor.process(Sheets.MATCH_L_TO_R.INDEX,
					Sheets.MATCH_L_TO_R.DATATYPE_L, row);
			Integer size_l = Integer
					.valueOf(processor.process(Sheets.MATCH_L_TO_R.INDEX,
							Sheets.MATCH_L_TO_R.SIZE_L, row));
			String required_l = processor.process(Sheets.MATCH_L_TO_R.INDEX,
					Sheets.MATCH_L_TO_R.REQUIRED_L, row);

			matchBuilder.createMatchSide(table_l, column_l, datatype_l, size_l,
					required_l, MatchBuilder.LEFT_SIDE);
			// create right side match if there is any
			if (!processor.process(Sheets.MATCH_L_TO_R.INDEX,
					Sheets.MATCH_L_TO_R.TABLE_R, row).equals(MatchConstants.NA)) {
				String table_r = processor.process(Sheets.MATCH_L_TO_R.INDEX,
						Sheets.MATCH_L_TO_R.TABLE_R, row);
				String column_r = processor.process(Sheets.MATCH_L_TO_R.INDEX,
						Sheets.MATCH_L_TO_R.COLUMN_R, row);
				String datatype_r = processor.process(
						Sheets.MATCH_L_TO_R.INDEX,
						Sheets.MATCH_L_TO_R.DATATYPE_R, row);
				Integer size_r = Integer.valueOf(processor.process(
						Sheets.MATCH_L_TO_R.INDEX, Sheets.MATCH_L_TO_R.SIZE_R,
						row));
				String required_r = processor.process(
						Sheets.MATCH_L_TO_R.INDEX,
						Sheets.MATCH_L_TO_R.REQUIRED_R, row);

				matchBuilder.createMatchSide(table_r, column_r, datatype_r,
						size_r, required_r, MatchBuilder.RIGHT_SIDE);
			}
		} catch (Throwable t) {
			System.err
					.println("A processing error occured after Match-L-to-R in line: "
							+ (matchCount + 2));
			t.printStackTrace();
			throw t;
		}
	}

	/**
	 * Validates the datatypes compatibilities in both directions: left_to_right
	 * and righnt_to_left
	 * 
	 * @param match
	 * @param row
	 * @return
	 */
	private boolean validateDatatypeCompatibility(final MatchType match) {
		if (match.getRight() != null) {
			// check compatibility left to right
			if (!dmr.verify(match.getLeft().getDatatype(), match.getRight()
					.getDatatype())) {
				// check compatibility right to left
				if (!dmr.verify(match.getRight().getDatatype(), match.getLeft()
						.getDatatype())) {
					// write error log
					writer.writeLog(new Error(eventCode
							.getString(EventCodeContants.ERR002),
							ProcessPhases.VALIDATION, Calendar.getInstance()
									.getTime(), EventCodeContants.ERR002, match
									.getTupleId(), match.getId(),
							Sheets.MATCH_L_TO_R.NAME));

					return false;// end execution
				} else {
					// write warning log
					writer.writeLog(new Warning(eventCode
							.getString(EventCodeContants.WAR001),
							ProcessPhases.VALIDATION, Calendar.getInstance()
									.getTime(), EventCodeContants.WAR001, match
									.getTupleId(), match.getId(),
							Sheets.MATCH_L_TO_R.NAME));
					warningCount++;
				}
			}
		}
		return true;
	}

	/**
	 * This method is used to validate default value of the match
	 * 
	 * @param match
	 * @return
	 */
	private boolean validateDefaultValueOfMatch(final MatchType match) {
		Object defaultValue = match.getDefaultValue();
		// 19. If in a Match-L-to-R the left side is required, then its default
		// value cannot be NULL
		if (match.getLeft().isIsRequired().equals(MatchConstants.YES)
				&& defaultValue.equals(MatchConstants.NULL)) {
			writer.writeLog(new Error(eventCode
					.getString(EventCodeContants.ERR012),
					ProcessPhases.VALIDATION, Calendar.getInstance().getTime(),
					EventCodeContants.ERR012, match.getTupleId(),
					match.getId(), Sheets.MATCH_L_TO_R.NAME));

			return false;// end execution
		}
		// 18. If the default value of a Match-L-to-R is TOP, the match must not
		// have a right side, an error must be thrown otherwise
		if (defaultValue.equals(MatchConstants.TOP) && match.getRight() != null) {
			writer.writeLog(new Error(eventCode
					.getString(EventCodeContants.ERR013),
					ProcessPhases.VALIDATION, Calendar.getInstance().getTime(),
					EventCodeContants.ERR013, match.getTupleId(),
					match.getId(), Sheets.MATCH_L_TO_R.NAME));

			return false;// end execution
		}
		// 17. If the default value of a Match-L-to-R is SKIP, the match must
		// have a right side, an error must be thrown otherwise
		if (defaultValue.equals(MatchConstants.SKIP)
				&& match.getRight() == null) {
			writer.writeLog(new Error(eventCode
					.getString(EventCodeContants.ERR014),
					ProcessPhases.VALIDATION, Calendar.getInstance().getTime(),
					EventCodeContants.ERR014, match.getTupleId(),
					match.getId(), Sheets.MATCH_L_TO_R.NAME));

			return false;// end execution
		}
		return true;
	}

	/**
	 * This method is used to validate default values only for cases in which
	 * the right side doesn't exist
	 * 
	 * @param match
	 * @return
	 */
	private boolean validateDefaultValueWithoutRightSideOfMatch(
			final MatchType match) {
		Object defaultValue = match.getDefaultValue();
		// check if match has a default value
		if (defaultValue.equals(MatchConstants.NA)) {
			writer.writeLog(new Error(eventCode
					.getString(EventCodeContants.ERR001),
					ProcessPhases.VALIDATION, Calendar.getInstance().getTime(),
					EventCodeContants.ERR001, match.getTupleId(),
					match.getId(), Sheets.MATCH_L_TO_R.NAME));

			return false;// end execution
		}
		// in case the default value is AI, the left datatype must be INT or
		// compatible
		if (defaultValue.equals(MatchConstants.AI)
				&& !match.getLeft().getDatatype().equals(MatchConstants.INT)) {
			writer.writeLog(new Error(eventCode
					.getString(EventCodeContants.ERR003),
					ProcessPhases.VALIDATION, Calendar.getInstance().getTime(),
					EventCodeContants.ERR003, match.getTupleId(),
					match.getId(), Sheets.MATCH_L_TO_R.NAME));
			return false;
		}
		return true;
	}

	/**
	 * This method is used to validate default values only for cases in which
	 * the right side exist
	 * 
	 * @param match
	 * @return
	 */
	private boolean validateDefaultValueWithRightSideOfMatch(
			final MatchType match) {
		Object defaultValue = match.getDefaultValue();
		// check if match has a default value
		if (match.getRight().isIsRequired().equals(MatchConstants.NO)) {
			if (defaultValue.equals(MatchConstants.NA)) {
				writer.writeLog(new Error(eventCode
						.getString(EventCodeContants.ERR001),
						ProcessPhases.VALIDATION, Calendar.getInstance()
								.getTime(), EventCodeContants.ERR001, match
								.getTupleId(), match.getId(),
						Sheets.MATCH_L_TO_R.NAME));

				return false;// end execution
			}
		}
		// in case the default value is AI, the left datatype must be INT or
		// compatible
		if (defaultValue.equals(MatchConstants.AI)
				&& !match.getLeft().getDatatype().equals(MatchConstants.INT)) {
			writer.writeLog(new Error(eventCode
					.getString(EventCodeContants.ERR003),
					ProcessPhases.VALIDATION, Calendar.getInstance().getTime(),
					EventCodeContants.ERR003, match.getTupleId(),
					match.getId(), Sheets.MATCH_L_TO_R.NAME));
			return false;
		}
		// If default value is AI/SKIP/TRUE or AI/SKIP/FALSE, then the right
		// side datatype must be logic (true, false)
		if (defaultValue.equals(MatchConstants.AI_SKIP_TRUE)
				|| defaultValue.equals(MatchConstants.AI_SKIP_FALSE)) {

			String datatype = match.getRight().getDatatype();

			if (!dmr.verify(MatchConstants.BOOL, datatype)) {
				writer.writeLog(new Error(eventCode
						.getString(EventCodeContants.ERR004),
						ProcessPhases.VALIDATION, Calendar.getInstance()
								.getTime(), EventCodeContants.ERR004, match
								.getTupleId(), match.getId(),
						Sheets.MATCH_L_TO_R.NAME));
				return false;
			}
		}
		// If right side is required, then the default value cannot not be SKIP
		if (defaultValue.equals(MatchConstants.SKIP)
				&& match.getRight().isIsRequired().equals(MatchConstants.YES)) {
			writer.writeLog(new Error(eventCode
					.getString(EventCodeContants.ERR005),
					ProcessPhases.VALIDATION, Calendar.getInstance().getTime(),
					EventCodeContants.ERR005, match.getTupleId(),
					match.getId(), Sheets.MATCH_L_TO_R.NAME));
			return false;
		}
		// 20. If in a Match-L-to-R the default value is NOW, then its right
		// side datatype must be DATE or DATETIME compatible.
		if (defaultValue.equals(MatchConstants.NOW)) {
			if (!Arrays.asList(MatchConstants.DATETIME, MatchConstants.DATE)
					.contains(match.getLeft().getDatatype())) {
				writer.writeLog(new Error(eventCode
						.getString(EventCodeContants.ERR016),
						ProcessPhases.VALIDATION, Calendar.getInstance()
								.getTime(), EventCodeContants.ERR016, match
								.getTupleId(), match.getId(),
						Sheets.MATCH_L_TO_R.NAME));

				return false;// end execution
			}
		}
		return true;
	}

	/**
	 * This method validates the structure of the Tuple after composed.
	 * 
	 * @return
	 */
	private boolean validateTupleStructure() {
		// linear search tuples
		for (TupleType tuple : LINEAR_TUPLES.values()) {
			MatchType pkMatch = null;
			// validate tuple must have a PK match
			for (MatchType match : tuple.getMatches()) {
				if (match.isPk().equals(MatchConstants.YES)) {
					pkMatch = match;
					break;
				}
			}
			// print and log error message
			if (pkMatch == null) {
				writer.writeLog(new Error(eventCode
						.getString(EventCodeContants.ERR017),
						ProcessPhases.VALIDATION, Calendar.getInstance()
								.getTime(), EventCodeContants.ERR017, tuple
								.getId(), 0, Sheets.TUPLE.NAME));

				return false;// end execution
			}
			// validate PK match must have L-References
			if (pkMatch.getReferences() == null
					|| pkMatch.getReferences().isEmpty()) {
				writer.writeLog(new Error(eventCode
						.getString(EventCodeContants.ERR018),
						ProcessPhases.VALIDATION, Calendar.getInstance()
								.getTime(), EventCodeContants.ERR018, tuple
								.getId(), pkMatch.getId(),
						Sheets.TUPLE.NAME));

				return false;// end execution
			}
			// validate Tuple must have L-References with direct parent
			boolean hasReferenceWithParent = true;
			TupleTree parentTree = tree.getTree(tuple.getId()).getParent();
			//skip root element
			if (parentTree != null) {
				for (ReferenceType lReference : tuple.getReferences().values()) {
					if (parentTree
							.getHead()
							.getTable()
							.equalsIgnoreCase(
									lReference.getReferenced().getTable())) {
						hasReferenceWithParent = true;
						break;
					}
				}
				if (!hasReferenceWithParent) {
					writer.writeLog(new Error(eventCode
							.getString(EventCodeContants.ERR019),
							ProcessPhases.VALIDATION, Calendar.getInstance()
									.getTime(), EventCodeContants.ERR019, tuple
									.getId(), 0, Sheets.TUPLE.NAME));

					return false;// end execution
				}
			}
		}
		return true;
	}

	/**
	 * This method returns the resulting tree structure of the matching file
	 * 
	 * @return
	 */
	public TupleTree getTree() {
		return tree;
	}

	/**
	 * Validate the conditions of the matches with PK value equals to YES
	 * 
	 * @param match
	 * @return
	 */
	private boolean validatePk(final MatchType match) {
		final String pk = match.isPk();
		// If match is PK, then it’s datatype must be INT or compatible,
		// otherwise an error must be logged
		if (pk.equals(MatchConstants.YES)) {
			if (!dmr.verify(MatchConstants.INT, match.getLeft().getDatatype())) {
				writer.writeLog(new Error(eventCode
						.getString(EventCodeContants.ERR006),
						ProcessPhases.VALIDATION, Calendar.getInstance()
								.getTime(), EventCodeContants.ERR006, match
								.getTupleId(), match.getId(),
						Sheets.MATCH_L_TO_R.NAME));
				return false;
			}
			// If match is PK, then it must be required, otherwise an error must
			// be logged
			if (!match.getLeft().isIsRequired().equals(MatchConstants.YES)) {
				writer.writeLog(new Error(eventCode
						.getString(EventCodeContants.ERR007),
						ProcessPhases.VALIDATION, Calendar.getInstance()
								.getTime(), EventCodeContants.ERR007, match
								.getTupleId(), match.getId(),
						Sheets.MATCH_L_TO_R.NAME));
				return false;
			}
		}
		return true;
	}
}
