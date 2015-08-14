<%@ include file="/WEB-INF/template/include.jsp"%>

<%@ include file="/WEB-INF/template/header.jsp"%>

<%@ include file="template/localHeader.jsp"%>


<h2>Welcome to Data Import Tool </h2>

<p>The module aims ease data migration for production settings by improving upon an existing data migration tool to make it more user friendly.
This OpenMRS specific module integrates  the eSaude Data Migration Tool (eSaudeDMT) in order to migrate data from SQL-based data source into OpenMRS.
<br>It provides a WUI which takes Migration settings on the startMigration page replacing the old config.xml file of the DMT tool. 
<br>
<br>you can check out the new branch:
<br>
<i>```</i>
<br><i>$ git clone https://github.com/Ch3ck/openmrs-module-dataimporttool.git</i>
<br><i>$ git checkout gh-pages</i>
<br><i>```</i>
<br>If you're using the GitHub for Mac, simply sync your repository and you'll see the new branch.
</p>

<h2> Overview</h2>

<p>In order to execute the system in test environment make sure you have the following tools installed:
<br>
<br>Java 7 
<br>Apache Maven 3 (http://maven.apache.org/download.cgi)
<br>Git (http://git-scm.com/downloads)
<br>Perform the following steps using command line:
<i>
<br>1.) Clone the project repository from github git clone https://github.com/Ch3ck/openmrs-module-dataimporttool.git
<br>
<br>2.) run 'mvn clean install'
</i>
<br>...


<h2>Authors and Contributors</h2>

 Nyah Check(Ch3ck), as a Google Summer of code student was inspired by the eSaude Data Migration System developed by Valerio Joao(@vjoao) and Pascal Brandt to work on the Data Import Tool for OpenMRS.

<h2> Documentation and Support</h2>

<p>Having trouble with our tool? Check out the documentation at <i>https://wiki.openmrs.org/display/projects/Data+Import+using+eSaude+Data+Migration+Tool</i> or contact us on the openmrs mailing list(dev@openmrs.org) and we can help you sort it out.
<br>Main documentation page for the Data Import Tool is on Github: https://github.com/esaude/dmt-guide<br>

</p>

<%@ include file="/WEB-INF/template/footer.jsp"%>
