<%@ include file="/WEB-INF/template/include.jsp"%>

<%@ include file="/WEB-INF/template/header.jsp"%>

<%@ include file="template/localHeader.jsp"%>

<html>
<body>
<form:form method="post">
<form:errors />
<b><i>Please Fill in the Migration Settings</i></b>
	<h3>Matching File Data</h3>
	<p>
		Matching File Name: <input type="text" name="MatchFile" value="${status.value}" />
		<form:input path="matchFile" />
		<form:errors path="matchFile" />
		Match File Type: <input path="matchFormat" type="text" name="matchFormat" value="xls" />
		<form:errors path="matchFormat" />
		<form:input path="matchLocation"/>
		Matching File Location: <input type="text" name="matchLocation" value="${status.value}" />
		<form:errors path="matchLocation" />
	</p>
	<h3>Target Database Data</h3>
	<p>
		<form:input path="leftUserName" />
		User Name: <input type="text" name="leftUserName" value="${status.value}" />
		<form:input path="leftPassword" />
		Password: <input type="password" name="leftPassword" value="${status.value}" />	
		<form:input path="leftDbDriver" />
		<form:errors path="leftDbDriver" />
		Database Driver: <input type="text" name="leftDbDriver" value="${status.value}" value="com.mysql.jdbc.Driver"/>	
		<form:input path="leftDbName"/>
		<form:errors path="leftDbName" />
		Database Name: <input type="text" name="leftDbName" value="${status.value}" value="openmrs"/>	
		<form:input path="leftDbLocation"/>
		<form:errors path="leftDbLocation" />
		Database Location<input type="text" name="leftDbLocation" value="${status.value}" />
	</p>	
	<h3>Source Database</h3>
	<p>
		<form:input path="rightUserName"/>
		User Name: <input type="text" name="rightUserName" value="${status.value}" /> 	
		<form:input path="rightPassword"/>
		Password: <input type="password" name="rightPassword" value="${status.value}" />
		<form:input path="rightDbDriver"/>
		<form:errors path="rightDbDriver" />
		Database Driver: <input type="text" name="rightDbDriver" value="${status.value}" value="com.mysql.jdbc.Driver"/>
		<form:input path="rightDbName"/>
		<form:errors path="rightDbName" />
		Database Name: <input type="text" name="rightDbName" value="${status.value}" value="openmrs"/>
		<form:input path="rightDbLocation"/>
		<form:errors path="rightDbLocation" />
		Database Location: <input type="text" name="rightDbLocation" value="${status.value}" />
	</p>
	<h3>Migration Options</h3>
	 <p>
    	<form:select path="allowCommit"/>
		Allow Commit
		<input type="checkbox" name="allowCommit" value="True"checked>True
  		<input type="checkbox" name="allowCommit" value="False" >False
		<form:select path="resetProcess"/>
		Reset Process
			<input type="checkbox" name="resetProcess" value="True">True
  			<input type="checkbox" name="resetProcess" value="False" checked>False
		<form:input path="treeLimit"/>
		Tree Limit<input type="number" name="treeLimit" value="0" />
	</p>
</form:form>
<a href="continueMigration.jsp">Proceed with Migration</a>
</body>
</html>

<%@ include file="/WEB-INF/template/footer.jsp"%>
