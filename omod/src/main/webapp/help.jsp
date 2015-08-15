<%@ include file="/WEB-INF/template/include.jsp"%>

<%@ include file="/WEB-INF/template/header.jsp"%>

<%@ include file="template/localHeader.jsp"%>

<h2><b> The OpenMRS Data Import Tool Guide</b></h2>
<br>
<p>This project improves ease of data migration for production settings by improving upon an existing data migration tool to make it more user friendly. The Data Import Tool integrates  the eSaude Data Migration Tool (eSaudeDMT) it in order to migrate data from SQL-based data source into OpenMRS. This module provides a WUI to configure migration settings that are currently being performed using an XML file (config.xml), and a Start Migration(command) button to ignite the execution. 
</p><br>
<h3><b> Instructions </b></h3>
<br>
This document will describe how to use the Data Import Tool to migrate data from a dummy database into OpenMRS.
<br>

<h3> Step 1. Build The Data Import Tool</h3>
<br>
Clone the DMT repo and build it.
<p>
````
<br><i>  $ git git clone https://github.com/Ch3ck/openmrs-module-dataimporttool.git</i>
<br><i>  $ cd openmrs-module-dataimporttool </i>
<br><i>  $ mvn clean install </i>
<br><br><br>
  
  This command generates a omod file for the data-importtool 
<br><i>````</i><br>
</p>
<br><h3> Step 2. Install OpenMRS 1.11.3 </h3>

Download the [OpenMRS WAR file](http://sourceforge.net/projects/openmrs/files/releases/OpenMRS_Platform_1.11.3/openmrs.war/download) and deploy it to Tomcat.

<br><h3> Step 3. Prepare Dummy Database </h3>

Import the dummy database.
<br><i>
````
<br>  $ mysql -uroot -p
<br>  mysql> create database dummy;
<br>  mysql> use dummy;
<br>  mysql> \. resources/source_medical_dummy.sql
<br>````
<br></i><br>
<br><h3> Step 4. Complete The Mapping Spreadsheet </h3>
<p>
<br>The mapping sheet we are using can be download from [Google Sheets](https://docs.google.com/spreadsheets/d/1ljn2hyf9Qk3IFfQWYiCmuwgJxDWn2hnzX4m2dLhR0mk/edit#gid=1416522886).

<br>Once the mappings have been correctly specified and the file has been downloaded and saved. Update the `file_name` and `location` values in the [`config.xml`](https://github.com/esaude/data-migration-system/blob/master/src/main/resources/config.xml) file.
<br></p><br>

<br><h3> Step 4. Linux Users Only</h3>

<br><i> 1.) Install the MySQL/Mariadb java connector/driver[MariaDB Connector](https://code.mariadb.com/connectors/java/).
      Alternatively you can copy the mariadb connector.jar files to the appropriate /usr/lib/java*/jre/lib/ext/ directory
<br>  
<br>  2.) Modify the [`config.xml`](https://github.com/esaude/dmt-guide/tree/master/resources/config.xml)
</i><br>

<br><h3> Step 5. Run The DIT from OpenMRS </h3>

<br><i>1.) Upload the module from the OpenMRS Manage modules link

<br>2.) Click on the Start Migration link under the Data Import Tool

<br>3.) Verify the migration settings and click 'Start Migration'
</i><br><br>

<br><h3> Resources </h3>

<br><i>1.) [Google Sheets](https://docs.google.com/spreadsheets/d/1ljn2hyf9Qk3IFfQWYiCmuwgJxDWn2hnzX4m2dLhR0mk/edit#gid=1416522886)

<br>2.) [MariaDB Connector](https://code.mariadb.com/connectors/java/)

<br>3.) [Sample Linux Config](https://github.com/esaude/dmt-guide/tree/master/resources/config.xml)
</i><br>
<br>The docs can be downloaded from [the OpenMRS DMT wiki page](https://wiki.openmrs.org/pages/viewpageattachments.action?pageId=80379983).
<br>OpenMRS mailing list [OpenMRS talk](https://talk.openmrs.org).

<br>If you have any further questions send an email to ch3ck [at] openmrs [dot] org 
<br>

<br><h3>Authors and Contributors </h3>
<br>
<br>1.)Nyah Check(Ch3ck)
<br>2.) Valerio Joao(vjoao)
<br>3.) Pascal Brandt(psbrandt) 
<br>

<%@ include file="/WEB-INF/template/footer.jsp"%>
