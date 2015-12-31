### The OpenMRS Data Import Tool Guide

This project eases data migration for production settings by improving upon eSaude Data Migration Tool making it more user friendly. The Data Import Tool integrates  the eSaude Data Migration Tool (eSaude DMT) in order to migrate data from SQL-based data sources into OpenMRS. This module provides a WUI to configure migration settings and perform migration. 

## Instructions

This document describes how to use the Data Import Tool to migrate data from a dummy database into OpenMRS.

### Step 1. Build The Data Import Tool

Clone the **DIT** repo and build it.

````
  $ git clone https://github.com/openmrs/openmrs-module-dataimporttool.git
  $ cd openmrs-module-dataimporttool
  $ mvn clean install

  
  This command generates an **omod** file for the openmrs-module-data-importtool 
````

### Step 2. Install OpenMRS 1.11.3

Download the [OpenMRS WAR file](http://sourceforge.net/projects/openmrs/files/releases/OpenMRS_Platform_1.11.3/openmrs.war/download) and deploy it to Tomcat.

### Step 3. Prepare Dummy Database

Refer to the [DMT-Guide](https://github.com/esaude/dmt-guide)
for test Database and instructions on data migration.

I.) Create a dummy database, populated with data.

Clone dmt-guide
````
	$git clone https://github.com/esaude/dmt-guide
	cd dmt-guide
````

````
  $ mysql -uroot -p
  mysql> create database dummy;
  mysql> use dummy;
  mysql> \. resources/source_medical_dummy.sql
````

II.) Create a dummy OpenMRS database

````
  $ mysql -uroot -p
  mysql> create database openmrs2;
  mysql> use openmrs2;
  mysql> \. resources/openmrs-1.11.3-clean.sql
````
There is a required change that must be done in OpenMRS database in order to run the migration, in OpenMRS 11 the PATIENT.patient_id column is not AUTO_INCREMENT, this way JDBC will not return the generated key. 
Use the script(alter_patient_id.sql) to alter the patient_id, after the migration you can change it back to normal

````
  mysql> \. resources/alter_patient_id.sql
````

### Step 4. Complete The Mapping Spreadsheet

The mapping sheet we are using can be download from [Mapping Sheet](https://github.com/esaude/dmt-guide/blob/master/resources/dummy-data-mapping_version.xls).


I)   **Linux Users** only

Install the **MySQL/Mariadb** Java connector or driver[MariaDB Connector](https://code.mariadb.com/connectors/java/).
Alternatively you could copy the mariadb connector.jar files to the appropriate /usr/lib/java*/jre/lib/ext/ directory
      
II)  **Windows User** and **Mac OSX Users** only

Refer to the [DMT README](https://github.com/esaude/data-migration-system/blob/master/README.md) document for specific configuration settings.


### Step 5. Run The DIT from OpenMRS 

1.) Upload the module from the OpenMRS Manage modules link

2.) Click on the Start Migration link under the Data Import Tool

3.) Verify the migration settings and click 'Start Migration'


## Resources

1.) [Matching File](https://github.com/esaude/dmt-guide/blob/master/resources/dummy-data-mapping_version.xls)

2.) [MariaDB Connector](https://code.mariadb.com/connectors/java/)

3.) [Sample Linux Config](https://github.com/esaude/dmt-guide/tree/master/resources/config.xml)

The Docs can be downloaded from [the OpenMRS DMT wiki page](https://wiki.openmrs.org/pages/viewpageattachments.action?pageId=80379983).
OpenMRS mailing list [OpenMRS talk](https://talk.openmrs.org).

If you have any further questions reach out on [OpenMRS Talk](https://talk.openmrs.org)


## Authors and Contributors

1.) Nyah Check(**Ch3ck**)

2.) Valerio Joao(**vjoao**)

3.) Pascal Brandt(**psbrandt**) 
