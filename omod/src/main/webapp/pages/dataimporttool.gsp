<%
 
%>

<openmrs:require privilege="Register Categories" otherwise="/login.htm"
	redirect="/module/dataimporttool/addMigrationSettings.form" />

<hr />
<% ui.includeJavascript("dataimporttool", "classie.js") %>
<% ui.includeJavascript("dataimporttool", "jquery.easing.min.js") %>
<% ui.includeJavascript("dataimporttool", "jquery-1.9.1.min.js") %>
<% ui.inlcudeCss("dataimportool", "component.css" %>
${ ui.includeFragment("dataimportool", "component") }




