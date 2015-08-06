<%@ include file="/WEB-INF/template/include.jsp"%>

<%@ include file="/WEB-INF/template/header.jsp"%>

<%@ include file="template/localHeader.jsp"%>

<html>
<body>
<form:form method="post">
<form:errors />
<br><b><i>Please Fill in the Migration Settings</i></b><br>
	<h2>Matching File Data</h2>
	<p>
		<form:input path="matchFile" type="text" name="MatchFile" value="${status.value}" placeholder="Matching File Name" />
		<form:errors path="matchFile" />
		<input path="matchFormat" type="text" name="matchFormat" value="xls" placeholder="Match File Type" />
		<form:errors path="matchFormat" />
		<form:input path="matchLocation"/>
		<input type="text" name="matchLocation" value="${status.value}" placeholder="Matching File Location" />
		<form:errors path="matchLocation" />
	
	</p>
	<h2>Target Database Data</h2>
	<p>
		<form:input path="leftUserName" />
		<input type="text" name="leftUserName" value="${status.value}" placeholder="User Name" />
		<form:input path="leftPassword" />
		<input type="password" name="leftPassword" value="${status.value}" placeholder="Password" />	
		<form:input path="leftDbDriver" />
		<form:errors path="leftDbDriver" />
		<input type="text" name="leftDbDriver" value="${status.value}" placeholder="Database Driver" value="com.mysql.jdbc.Driver"/>	
		<form:input path="leftDbName"/>
		<form:errors path="leftDbName" />
		<input type="text" name="leftDbName" value="${status.value}" placeholder="Database Name" value="openmrs"/>	
		<form:input path="leftDbLocation"/>
		<form:errors path="leftDbLocation" />
		<input type="text" name="leftDbLocation" value="${status.value}" placeholder="Database Location" />
	</p><br>	
	<h2>Source Database</h2>
	<p>
		<form:input path="rightUserName"/>
		<input type="text" name="rightUserName" value="${status.value}" placeholder="User Name" /> 	
		<form:input path="rightPassword"/>
		<input type="password" name="rightPassword" value="${status.value}" placeholder="Password" />
		<form:input path="rightDbDriver"/>
		<form:errors path="rightDbDriver" />
		<input type="text" name="rightDbDriver" value="${status.value}" placeholder="Database Driver" value="com.mysql.jdbc.Driver"/>
		<form:input path="rightDbName"/>
		<form:errors path="rightDbName" />
		<input type="text" name="rightDbName" value="${status.value}" placeholder="Database Name" value="openmrs"/>
		<form:input path="rightDbLocation"/>
		<form:errors path="rightDbLocation" />
		<input type="text" name="rightDbLocation" value="${status.value}" placeholder="Database Location" />
	</p>
	<h2>Migration Options</h2>
	 <p>
		<h3>Configuration</h3>
    	<form:select path="allowCommit"/>
		<input type="checkbox" name="allowCommit" value="True"checked> Yes<br>
  		<input type="checkbox" name="allowCommit" value="False" > No<br>
		<form:select path="resetProcess"/>
			<input type="checkbox" name="resetProcess" value="True"> Yes<br>
  			<input type="checkbox" name="resetProcess" value="False" checked> No<br>  
		<form:input path="treeLimit"/>
		<input type="number" name="treeLimit" placeholder="Tree Limit" value="0" />
	</p>
</form:form>
<a href="continueMigration.jsp">Proceed with Migration</a>
</body>
</html>

<%@ include file="/WEB-INF/template/footer.jsp"%>
