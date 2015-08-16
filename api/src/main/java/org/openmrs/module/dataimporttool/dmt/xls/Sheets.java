/**
 * The contents of this file are subject to the OpenMRS Public License
 * Version 1.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://license.openmrs.org
 *
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * License for the specific language governing rights and limitations
 * under the License.
 *
 * Copyright (C) OpenMRS, LLC.  All Rights Reserved.
 */

package org.openmrs.module.dataimporttool.dmt.xls;



/**
 * A list of index mapped to the XLS input file
 *
 */
public class Sheets {
	
	public static final class TUPLE {
		public static final String NAME = "TUPLE"; 
		public static final int INDEX = 0;
		public static final int ROW_START = 1;
		public static final int ID = 0;
		public static final int TERMINOLOGY = 1;
		public static final int TABLE = 2;
		public static final int PREDECESSOR = 3;
		public static final int DESC = 4;
	}
	
	public static final class MATCH_L_TO_R {
		public static final String NAME = "MATCH_L_TO_R"; 
		public static final int INDEX = 1;
		public static final int ROW_START = 2;
		public static final int TUPLE_ID = 0;
		public static final int TERMINOLOGY = 1;
		public static final int COLUMN_L = 2;
		public static final int DATATYPE_L = 3;
		public static final int SIZE_L = 4;
		public static final int REQUIRED_L = 5;
		public static final int PK = 6;
		public static final int TABLE_R = 7;
		public static final int COLUMN_R = 8;
		public static final int DATATYPE_R = 9;
		public static final int SIZE_R = 10;
		public static final int REQUIRED_R = 11;
		public static final int MATCH_ID = 12;
		public static final int OBS = 13;
		public static final int DEFAULT_VALUE = 14;
		public static final int VALUE_MATCH = 15;
		public static final int POOL = 16;
	}
	
	public static final class REFERENCES {
		public static final String NAME_L = "REFERENCES_L"; 
		public static final int INDEX_L = 2;
		public static final String NAME_R = "REFERENCES_R"; 
		public static final int INDEX_R = 3;
		public static final int ROW_START = 1;
		public static final int ID = 0;
		public static final int TUPLE_MATCH_ID = 1;
		public static final int REFERENCE_TABLE = 2;
		public static final int REFERENCE_COLUMN = 3;
		public static final int REFERENCED_TABLE = 4;
		public static final int REFERENCED_COLUMN = 5;
		public static final int DATATYPE = 6;
		public static final int SIZE = 7;
		public static final int REFERENCED_VALUE = 8;
		public static final int SEQUENCE = 9;
		public static final int NAME_DESC = 10;	
	}
	
	public static final class VALUE_MATCH {
		public static final String NAME = "VALUE_MATCH"; 
		public static final int INDEX = 4;
		public static final int ROW_START = 2;
		public static final int ID = 0;
		public static final int GROUP_NAME = 1;
		public static final int TABLE_L = 2;
		public static final int COLUMN_L = 3;
		public static final int VALUE_L = 4;
		public static final int PK_L = 5;
		public static final int TABLE_R = 6;
		public static final int COLUMN_R = 7;
		public static final int VALUE_R = 8;
		public static final int PK_R = 9;
	}
}
