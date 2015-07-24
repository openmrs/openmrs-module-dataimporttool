Welcome to <b> The OpenMRS</b> <b><i>Data Import Tool</i></b>.


This System enables Data Migration from SQL-based databases into OpenMRS 
Please fill the form below with your desired Migration Settings


${ ui.includeFragment("uiframework", "helloUser") }
${ ui.includeFragment("dataimporttool", "migrationSetting") }




The matching file in XLS info goes here:
	
	File name:
	Format(xls):
	Location: 
	

The Left side database info goes here(Target Database Information)

	Driver name(com.mysql.jdbc.Driver): 
	UserName: 
	Password: 
	Database Name: 
	Database Location(jdbc:mysql://localhost:3306/dbname):
	
The Right side database info goes here(Source Database Information)
	
	Driver name(sun.jdbc.odbc.JdbcOdbcDriver):
	UserName: 
	Password:
	Database Name:
	Database Location(jdbc:odbc:Driver={Microsoft Access Driver()};dblocation):

Limit on number of Trees to be processed(0, -x(unlimited):

whether or not results will be committed.
	Allow commits(T/F):

whether the process should start from zero or last stopped point
	Reset Process(T/F): 
	
