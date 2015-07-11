### Welcome to openmrs-module-dataimporttool.
The goal of this project is to improve ease of data migration for production settings by improving upon an existing data migration tool to make it more user friendly.
The student will develop an OpenMRS specific module that either (a) reuses components from or (b) integrates  the eSaude Data Migration Tool (eSaudeDMT) it in order to migrate data from SQL-based data source into OpenMRS. The completed module will provide a WUI to configure migration settings that are currently being performed using an XML file (config.xml), and a command button to ignite the execution. 

you can check out the new branch:

```
$ git clone https://github.com/Ch3ck/openmrs-module-dataimporttool.git
$ git checkout gh-pages
```
If you're using the GitHub for Mac, simply sync your repository and you'll see the new branch.


### Overview
The main architectural idea is to have a tool that takes a matching file as input, validates the matches, translates them into SQL SELECT, INSERT and/or UPDATE statements (mapping), and execute the resulting mapping.
The tool tries to accomplish the following: 

Reusability - Developed once and used when needed. The mapping created is based on input logic
Configurability - The migration logic is structured in a user friendly OpenMRS interface.
Auditability -  Provides user feedback with appropriate error messages.


### Development Environment Specs
For the development of data migration tool here are the minimum specs of the development environment:

Windows OS 7+ (currently being used by the majority)
Eclipse or Netbeans IDE 
Java 7+ 
Apache Maven 3 (http://maven.apache.org/download.cgi)
Git (http://git-scm.com/downloads)


### Test and Production environment Specs
In order to execute the system in test environment make sure you have the following tools installed:

Java 7 
Apache Maven 3 (http://maven.apache.org/download.cgi)
Git (http://git-scm.com/downloads)
Perform the following steps using command line:

1.) Clone the project repository from github git clone https://github.com/Ch3ck/openmrs-module-dataimporttool.git

2.) run 'mvn clean install'
...


### Authors and Contributors
In 2015 Nyah Check(Ch3ck), as a Google Summer of code student was inspired by the eSaude Data Migration System developed by Valerio Joao(@vjoao) to work on the Data Import Tool for OpenMRS.


### Support or Contact
Having trouble with our tool? Check out the documentation at https://wiki.openmrs.org/display/projects/Data+Import+using+eSaude+Data+Migration+Tool or contact us on the openmrs mailing list(dev@openmrs.org) and we’ll help you sort it out.

