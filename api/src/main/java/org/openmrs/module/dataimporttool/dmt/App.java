package org.openmrs.module.dataimporttool.dmt;

import org.openmrs.module.dataimporttool.dmt.component.TranslationManager;
import org.openmrs.module.dataimporttool.dmt.component.ValidationManager;
import org.openmrs.module.dataimporttool.dmt.helper.SystemException;

/**
 * The main class
 *
 */
public class App 
{
	public static final String MAIN_PATH = "api/src/main/resources";
	public static final String TEST_PATH = "api/src/main/src/test/java"; //Still to be properly fixed.

    public static void main( String[] args ) throws SystemException
    {
    	ValidationManager vm = new ValidationManager();
    	if(!vm.execute()) return;
    	TranslationManager tm = new TranslationManager(vm.getTree());
    	tm.execute();
    }



}
