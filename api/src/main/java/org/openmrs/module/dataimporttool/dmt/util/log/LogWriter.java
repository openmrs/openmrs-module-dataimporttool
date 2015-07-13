/**
 * 
 * Esta classe define os metodos que escrevem os eventos no logfile 
 * @author Edias Jambaia
 * @author Valério João
 * @since 21-08-2014
 */
package org.openmrs.module.dataimporttool.dmt.util.log;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

public final class LogWriter {

	private static LogWriter writer;

	/**
	 * Creates and returns a single instance of this class
	 * @return
	 */
	public static LogWriter getWriter() {

		if (writer == null) {
			writer = new LogWriter();
			DOMConfigurator.configure("log4j.xml");
		}
		return writer;
	}
	
	/**
	 * Write the event to the log file
	 * @param event
	 */
	public String writeLog(Event event) {
		Logger log = Logger.getLogger(this.getClass());
		
		if(event.getType().equals(Info.TYPE)) {
			log.info(event.toString());
		}
		if(event.getType().equals(Warning.TYPE)) {
			log.warn(event.toString());
		}
		if(event.getType().equals(Error.TYPE)) {
			log.error(event.toString());
		}
		
		return event.toString();
	}
}
