<%@ taglib prefix="form uri="https://www.springframework.org/tags/form" %>
<%@ page session="false"%>
<%@ taglib prefix="c" uri="https://java.sun.com/jsp/jst1/core" %>
<%@ taglib prefix="fmt" uri="https://java.sun.com/jsp/jst1/fmt" %>

<html>
<head>
    <title></title>
    <style>
     .error { color: red; }
    </style>
</head>
<body>
<form:form method="post" commandName="Proceed">
<form:errors />
	<!-- fieldsets -->
	<fieldset>
		<h2 class="fs-title">Matching File Data</h2>
		<h3 class="fs-subtitle">Please fill in the details</h3>
			<input type="text" name="MatchFile" value="${status.value}" placeholder="Matching File Name" />
			<form:input path="matchFile"/>
			<form:errors path="matchFile" />
			<input type="text" name="matchFormat" value="xls" placeholder="Match File Type" />
			<form:input path="matchFormat"/>
			<form:errors path="matchFormat" />
			<input type="text" name="matchLocation" value="${status.value}" placeholder="Matching File Location" />
			<form:input path="matchLocation"/>
			<form:errors path="matchLocation" />
	</fieldset>	
	<fieldset>
		<h2 class="fs-title">Target Database Data</h2>
		<h3 class="fs-subtitle">Please fill in the left database details</h3>
		<form:input path="leftUserName">
			<input type="text" name="leftUserName" value="${status.value}" placeholder="User Name" />
			
		<form:input path="leftPassword">
			<input type="password" name="leftPassword" value="${status.value}" placeholder="Password" />
			
		<form:input path="leftDbDriver">
		<form:errors path="leftDbDriver" />
			<input type="text" name="leftDbDriver" value="${status.value}" placeholder="Database Driver" value="com.mysql.jdbc.Driver"/>
			
		<form:input path="leftDbName">
		<form:errors path="leftDbName" />
			<input type="text" name="leftDbName" value="${status.value}" placeholder="Database Name" value="openmrs"/>
			
		<form:input path="leftDbLocation">
		<form:errors path="leftDbLocation" />
			<input type="text" name="leftDbLocation" value="${status.value}" placeholder="Database Location" />
			
	</fieldset>
 	<fieldset>
		<h2 class="fs-title">Source Database</h2>
		<h3 class="fs-subtitle">Please fill in the right database details</h3>
		<form:input path="rightUserName">
			<input type="text" name="rightUserName" value="${status.value}" placeholder="User Name" />
            	
		<form:input path="rightPassword">
			<input type="password" name="rightPassword" value="${status.value}" placeholder="Password" />
		<form:input path="rightDbDriver">
		<form:errors path="rightDbDriver" />
			<input type="text" name="rightDbDriver" value="${status.value}" placeholder="Database Driver" value="com.mysql.jdbc.Driver"/>
			
		<form:input path="rightDbName">
		<form:errors path="rightDbName" />
			<input type="text" name="rightDbName" value="${status.value}" placeholder="Database Name" value="openmrs"/>
			
		<form:input path="rightDbLocation">
		<form:errors path="rightDbLocation" />
			<input type="text" name="rightDbLocation" value="${status.value}" placeholder="Database Location" />
	</fieldset>
	<fieldset>
		<h2 class="fs-title">Migration Options</h2>
		<h3 class="fs-subtitle">Configuration</h3>
    		<form:select path="allowCommit">
			<input type="checkbox" name="allowCommit" value="True"checked> Yes<br>
  			<input type="checkbox" name="allowCommit" value="False" > No<br>
		<form:select path="resetProcess">
			<input type="checkbox" name="resetProcess" value="True"> Yes<br>
  			<input type="checkbox" name="resetProcess" value="False" checked> No<br>  
		<form:input path="treeLimit">
		<input type="number" name="treeLimit" placeholder="Tree Limit" value="0" />
	</fieldset>
	<input type="submit" name="startMigration" class="submit action-button" value="Start Migration" />
</form:form>
<a <c:redirect url="continueMigration.jsp"/>" Proceed with Migration"</a>
</body>
</html>
