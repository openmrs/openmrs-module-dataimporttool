### The OpenMRS Data Import Tool Guide

This project improves ease of data migration for production settings by improving upon an existing data migration tool to make it more user friendly. The Data Import Tool integrates  the eSaude Data Migration Tool (eSaudeDMT) it in order to migrate data from SQL-based data source into OpenMRS. This module provides a WUI to configure migration settings that are currently being performed using an XML file (config.xml), and a Start Migration(command) button to ignite the execution. 

## Instructions

This document will describe how to use the Data Import Tool to migrate data from a dummy database into OpenMRS.

### Step 1. Build The Data Import Tool

Clone the DMT repo and build it.

````
  $ git git clone https://github.com/Ch3ck/openmrs-module-dataimporttool.git
  $ cd openmrs-module-dataimporttool
  $ mvn clean install

  
  This command generates a omod file for the data-importtool 
````

### Step 2. Install OpenMRS 1.11.3

Download the [OpenMRS WAR file](http://sourceforge.net/projects/openmrs/files/releases/OpenMRS_Platform_1.11.3/openmrs.war/download) and deploy it to Tomcat.

### Step 3. Prepare Dummy Database

Import the dummy database.

````
  $ mysql -uroot -p
  mysql> create database dummy;
  mysql> use dummy;
  mysql> \. resources/source_medical_dummy.sql
````

### Step 4. Complete The Mapping Spreadsheet

The mapping sheet we are using can be download from [Google Sheets](https://docs.google.com/spreadsheets/d/1ljn2hyf9Qk3IFfQWYiCmuwgJxDWn2hnzX4m2dLhR0mk/edit#gid=1416522886).

Once the mappings have been correctly specified and the file has been downloaded and saved. Update the `file_name` and `location` values in the [`config.xml`](https://github.com/esaude/data-migration-system/blob/master/src/main/resources/config.xml) file.


### Step 4. Linux Users Only

I 1.) Install the MySQL/Mariadb java connector/driver[MariaDB Connector](https://code.mariadb.com/connectors/java/).
      Alternatively you can copy the mariadb connector.jar files to the appropriate /usr/lib/java*/jre/lib/ext/ directory
  
  2.) Modify the [`config.xml`](https://github.com/esaude/dmt-guide/tree/master/resources/config.xml)


### Step 5. Run The DIT from OpenMRS 

1.) Upload the module from the OpenMRS Manage modules link

2.) Click on the Start Migration link under the Data Import Tool

3.) Verify the migration settings and click 'Start Migration'


## Resources

1.) [Google Sheets](https://docs.google.com/spreadsheets/d/1ljn2hyf9Qk3IFfQWYiCmuwgJxDWn2hnzX4m2dLhR0mk/edit#gid=1416522886)

2.) [MariaDB Connector](https://code.mariadb.com/connectors/java/)

3.) [Sample Linux Config](https://github.com/esaude/dmt-guide/tree/master/resources/config.xml)

The docs can be downloaded from [the OpenMRS DMT wiki page](https://wiki.openmrs.org/pages/viewpageattachments.action?pageId=80379983).
OpenMRS mailing list [OpenMRS talk](https://talk.openmrs.org).

If you have any further questions send an email to ch3ck [at] openmrs [dot] org 


### Authors and Contributors

1.)Nyah Check(Ch3ck)
2.) Valerio Joao(vjoao)
3.) Pascal Brandt(psbrandt) 
