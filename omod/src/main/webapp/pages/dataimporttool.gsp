<%
    ui.decorateWith("appui", "standardEmrPage")
%>

<openmrs:require privilege="Register Categories" otherwise="/login.htm"
	redirect="/module/dataimporttool/addMigrationSettings.form" />

<hr />
<% ui.includeJavascript("chartsearch", "doT.js") %>
<% ui.includeJavascript("chartsearch", "jquery.flot.js") %>
<% ui.includeJavascript("chartsearch", "jquery.flot.time.js") %>
<% ui.includeJavascript("chartsearch", "jquery.flot.autoMarkings.js") %>
<% ui.includeJavascript("chartsearch", "jquery.flot.tickrotor.js") %>
<% ui.includeJavascript("chartsearch", "jquery.flot.axislabels.js") %>
<% ui.includeJavascript("chartsearch", "jquery.sparkline.js") %>
<% ui.includeJavascript("chartsearch", "views_factory.js") %>
${ ui.includeFragment("chartsearch", "cssIncludes") }




