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
<div class='help'>
<p>This project improves ease of data migration for production settings by improving upon an existing data migration tool to make it more user friendly. The Data Import Tool integrates  the eSaude Data Migration Tool (eSaudeDMT) it in order to migrate data from SQL-based data source into OpenMRS. This module provides a WUI to configure migration settings that are currently being performed using an XML file (config.xml), and a Start Migration(command) button to ignite the execution.</p>
    
<h3> Instructions </h3>
<p>This document describes how to use the Data Import Tool to migrate data from a database into an OpenMRS database.</p>

<h3>You will need </h3>
<ul>
    <li>An installation of OpenMRS</li>
    <li>Tomcat</li>
    <li>MySQL</li>
    <li>MariaDB Connector</li>
    <li>A source database</li>
</ul>

<h3> 1 - Build The Data Import Tool</h3>
<p>Clone the DMT repo and build it.</p>
<section>
    <div class='code'>
        <ul>
            <li class='norm'><span>git clone https://github.com/openmrs/openmrs-module-dataimporttool.git</span></li>
            <li class='norm'><span>cd openmrs-module-dataimporttool</span></li>
            <li class='norm'><span>mvn clean install</span></li>
        </ul>
    </div>
</section>
<p>The "mvn clean install" command generates an omod file located in the module's target folder which we will later use to upload DIT to OpenMRS.</p>

<h3> 2 - Install OpenMRS 1.11.3 </h3>
<p>Download the <a href="http://sourceforge.net/projects/openmrs/files/releases/OpenMRS_Platform_1.11.3/openmrs.war/download" target="_blank">OpenMRS WAR file</a> and deploy it to <a href="https://tomcat.apache.org/tomcat-7.0-doc/deployer-howto.html" target="_blank">Tomcat</a>.</p>

<h3> 3 - Prepare the Dummy Database </h3>
<p> This step is optional and is mainly for test purposes. If you have your own database, use it instead of this dummy database. </p>

<section>
    <div class='code'>
        <ul class='ulmys'>
            <li class='norm'><span class='comm'>Create and import the dummy database.</span></li>
            <li class='norm'><span>mysql -uroot -p</span></li>
            <li class='mysql'><span>create database dummy;</span></li>
            <li class='mysql'><span>use dummy;</span></li>
            <li class='mysql'><span>\. resources/source_medical_dummy.sql</span></li>
        </ul>
    </div>
</section>

<h3> 4 - Complete The Mapping Spreadsheet </h3>
<p>The mapping sheet we use can be downloaded from <a href="https://docs.google.com/spreadsheets/d/1ljn2hyf9Qk3IFfQWYiCmuwgJxDWn2hnzX4m2dLhR0mk/edit#gid=1416522886" target="_blank">Google Sheets</a>. </p>

<p>Once the mappings have been correctly specified and the file has been downloaded and saved. Update the 'file_name' and 'location' values in the <a href="https://github.com/esaude/data-migration-system/blob/master/src/main/resources/config.xml" target="_blank">config.xml file</a> according to the file's name and location.
</p>

<h3> 5 - Linux Users Only</h3>

<ol>
    <li>Install the <a href="https://code.mariadb.com/connectors/java/" target="_blank">MariaDB Connector</a>. Alternatively you can copy the MariaDB connector.jar files to the appropriate /usr/lib/java*/jre/lib/ext/ directory.</li>
    <li>Modify the <a href="https://github.com/esaude/data-migration-system/blob/master/src/main/resources/config.xml" target="_blank">config.xml file</a> to match your database settings.</li>
</ol>

<h3> 6 - Run the DIT from OpenMRS </h3>

<ol>
    <li>Upload the module from the OpenMRS <a href="${pageContext.request.contextPath}/admin/modules/module.list">Manage Modules menu</a>.</li>
    <li>Click on the Start Migration link under the Data Import Tool menu in <a href="${pageContext.request.contextPath}/admin/">OpenMRS admin menu</a>.</li>
    <li>Modify the migration settings to match your database and click 'Start Migration' </li>
</ol>

<h3> Resources </h3>
<ol>
    <li><a href="https://docs.google.com/spreadsheets/d/1ljn2hyf9Qk3IFfQWYiCmuwgJxDWn2hnzX4m2dLhR0mk/edit#gid=1416522886" target="_blank">Google Sheets</a></li>
    <li><a href="https://code.mariadb.com/connectors/java/" target="_blank">MariaDB Java Connector</a></li>
    <li><a href="https://github.com/esaude/dmt-guide/tree/master/resources/config.xml" target="_blank">Sample Linux Config</a></li>
</ol>
<p>The docs can be downloaded from <a href="https://wiki.openmrs.org/pages/viewpageattachments.action?pageId=80379983" target="_blank">the OpenMRS DMT wiki page</a>.</p>
<p>OpenMRS mailing list: <a href="https://talk.openmrs.org" target="_blank">OpenMRS Talk</a>.</p>
<p> If you have any further questions send an email to ch3ck@openmrs.org</p>

<h3 class="light">Authors and Contributors </h3>
<ol>
    <li> Nyah Check (Ch3ck)</li>
    <li> Valerio Joao (vjoao)</li>
    <li> Pascal Brandt (psbrandt)</li>
</ol>
</div>
</body>
</html>

<%@ include file="/WEB-INF/template/footer.jsp"%>
