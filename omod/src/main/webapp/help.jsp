<%@ include file="/WEB-INF/template/include.jsp"%>

<%@ include file="/WEB-INF/template/header.jsp"%>

<%@ include file="template/localHeader.jsp"%>


<h1>Welcome to Data Import Tool </h1>
<p>The module aims ease data migration for production settings by improving upon an existing data migration tool to make it more user friendly.
This OpenMRS specific module integrates  the eSaude Data Migration Tool (eSaudeDMT) in order to migrate data from SQL-based data source into OpenMRS.
It provides a WUI which takes Migration settings on the startMigration page replacing the old config.xml file of the DMT tool. 

you can check out the new branch:

<i>```</i>
<i>$ git clone https://github.com/Ch3ck/openmrs-module-dataimporttool.git</i>
<i>$ git checkout gh-pages</i>
<i>```</i>
If you're using the GitHub for Mac, simply sync your repository and you'll see the new branch.
</p>

<h1> Overview</h1>
<p>The main architectural idea is to have a tool that takes a matching file as input, validates the matches, translates them into SQL SELECT, INSERT and/or UPDATE statements (mapping), and execute the resulting mapping.
The tool tries to accomplish the following: 

<b><i>Reusability</i></b> - Developed once and used when needed. The mapping created is based on input logic
<b><i>Configurability</i></b> - The migration logic is structured in a user friendly OpenMRS interface.
<b><i>Auditability</i></b> -  Provides user feedback with appropriate error messages.
</p>

<h1> Development Environment Specs</h1>
<p>For the development of data migration tool here are the minimum specs of the development environment:

Windows OS 7+ (currently being used by the majority)
Eclipse or Netbeans IDE 
Java 7+ 
Apache Maven 3 (http://maven.apache.org/download.cgi)
Git (http://git-scm.com/downloads)
</p>

<h1>Test and Production environment Specs</h1>
<p>In order to execute the system in test environment make sure you have the following tools installed:

Java 7 
Apache Maven 3 (http://maven.apache.org/download.cgi)
Git (http://git-scm.com/downloads)
Perform the following steps using command line:
<i>
1.) Clone the project repository from github git clone https://github.com/Ch3ck/openmrs-module-dataimporttool.git

2.) run 'mvn clean install'
</i>
...

<h1>Authors and Contributors</h1>
 Nyah Check(Ch3ck), as a Google Summer of code student was inspired by the eSaude Data Migration System developed by Valerio Joao(@vjoao) and Pascal Brandt to work on the Data Import Tool for OpenMRS.

<h1> Documentation and Support</h1>
<p>Having trouble with our tool? Check out the documentation at https://wiki.openmrs.org/display/projects/Data+Import+using+eSaude+Data+Migration+Tool or contact us on the openmrs mailing list(dev@openmrs.org) and we can help you sort it out.
Main documentation page for the Data Import Tool is on Github: https://github.com/esaude/dmt-guide
</p>
<%@ include file="/WEB-INF/template/footer.jsp"%>
