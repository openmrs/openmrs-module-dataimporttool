<%@ include file="/WEB-INF/template/include.jsp"%>
<%@ include file="/WEB-INF/template/header.jsp"%>
<%@ include file="template/localHeader.jsp"%>


<html>
<head>
<openmrs:htmlInclude file="/moduleResources/dataimporttool/css/component.css"/>
<openmrs:htmlInclude file="/moduleResources/dataimporttool/css/font-awesome.min.css"/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>The OpenMRS Data Import Tool Guide</title>
</head>

<body>
<br>
<p>This project improves ease of data migration for production settings by improving upon an existing data migration tool to make it more user friendly. The Data Import Tool integrates  the eSaude Data Migration Tool (eSaudeDMT) it in order to migrate data from SQL-based data source into OpenMRS. This module provides a WUI to configure migration settings that are currently being performed using an XML file (config.xml), and a Start Migration(command) button to ignite the execution. 
</p>
<h3><b> Instructions </b></h3>
This document describes how to use the Data Import Tool to migrate data from a database into an OpenMRS database.
<br>

<h3>You will need </h3>
<ul>
<li>An installation of OpenMRS</li>
<li>Tomcat</li>
<li>MySQL</li>
<li>MariaDB Connector</li>
<li>A source database</li>
</ul>

<h3> 1- Build The Data Import Tool</h3>
Clone the DMT repo and build it.
<i>
<p>$ git clone https://github.com/openmrs/openmrs-module-dataimporttool.git</p>
<p>$ cd openmrs-module-dataimporttool</p>
<p>$ mvn clean install</p>
</i>
<p>
  The "mvn clean install" command generates an omod file located in the module's target folder which we will later use to upload DIT to OpenMRS.
</p>
<h3> 2- Install OpenMRS 1.11.3 </h3>

Download the <a href="http://sourceforge.net/projects/openmrs/files/releases/OpenMRS_Platform_1.11.3/openmrs.war/download" target="_blank">OpenMRS WAR file</a> and deploy it to <a href="https://tomcat.apache.org/tomcat-7.0-doc/deployer-howto.html" target="_blank">Tomcat</a>.

<br><h3> 3- Prepare the Dummy Database </h3>
<p> This step is optional and is mainly for test purposes. If you have your own database, use it instead of this dummy database. </p>

<i>
<p> Create and import the dummy database.</p>
<p> $ mysql -uroot -p<p>
<p> mysql> create database dummy;<p>
<p> mysql> use dummy;</p>
<p> mysql> \. resources/source_medical_dummy.sql</p>
</i>

<h3> 4- Complete The Mapping Spreadsheet </h3>
<p>The mapping sheet we use can be downloaded from <a href="https://docs.google.com/spreadsheets/d/1ljn2hyf9Qk3IFfQWYiCmuwgJxDWn2hnzX4m2dLhR0mk/edit#gid=1416522886" target="_blank">Google Sheets</a>. </p>

<p>Once the mappings have been correctly specified and the file has been downloaded and saved. Update the 'file_name' and 'location' values in the <a href="https://github.com/esaude/data-migration-system/blob/master/src/main/resources/config.xml" target="_blank">config.xml file</a> according to the file's name and location.
</p>

<h3> 5- Linux Users Only</h3>

<p> 1.) Install the <a href="https://code.mariadb.com/connectors/java/" target="_blank">MariaDB Connector</a>. Alternatively you can copy the MariaDB connector.jar files to the appropriate /usr/lib/java*/jre/lib/ext/ directory.
</p>
<p> 2.) Modify the <a href="https://github.com/esaude/data-migration-system/blob/master/src/main/resources/config.xml" target="_blank">config.xml file</a> to match your database settings.</p>

<h3> 6- Run the DIT from OpenMRS </h3>

<p> 1.) Upload the module from the OpenMRS <a href="${pageContext.request.contextPath}/admin/modules/module.list">Manage Modules menu</a>.</p>
<p> 2.) Click on the Start Migration link under the Data Import Tool menu in <a href="${pageContext.request.contextPath}/admin/">OpenMRS admin menu</a>.</p>
<p> 3.) Modify the migration settings to match your database and click 'Start Migration' </p>

<br><h3> Resources </h3>

<p> 1.) <a href="https://docs.google.com/spreadsheets/d/1ljn2hyf9Qk3IFfQWYiCmuwgJxDWn2hnzX4m2dLhR0mk/edit#gid=1416522886" target="_blank">Google Sheets</a>
<p> 2.) <a href="https://code.mariadb.com/connectors/java/" target="_blank">MariaDB Java Connector</a></p>
<p> 3.) <a href="https://github.com/esaude/dmt-guide/tree/master/resources/config.xml" target="_blank">Sample Linux Config</a></p>
<p>The docs can be downloaded from <a href="https://wiki.openmrs.org/pages/viewpageattachments.action?pageId=80379983" target="_blank">the OpenMRS DMT wiki page</a>.</p>
<p>OpenMRS mailing list: <a href="https://talk.openmrs.org" target="_blank">OpenMRS Talk</a>.</p>
<p> If you have any further questions send an email to ch3ck@openmrs.org</p>

<br><h3>Authors and Contributors </h3>
<p> 1.) Nyah Check (Ch3ck)</p>
<p> 2.) Valerio Joao (vjoao)</p>
<p> 3.) Pascal Brandt (psbrandt)</p>

</body>
</html>

<%@ include file="/WEB-INF/template/footer.jsp"%>
