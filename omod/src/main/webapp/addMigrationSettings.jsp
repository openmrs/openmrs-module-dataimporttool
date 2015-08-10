<%@ include file="/WEB-INF/template/include.jsp"%>

<%@ include file="/WEB-INF/template/header.jsp"%>

<%@ include file="template/localHeader.jsp"%>

<form:form modelAttribute="dit" method="post">
<form:errors />
<br><b><i>Please Fill in the Migration Settings</i></b><br>
	<h3>Matching File Data</h3>
	<p>
		Matching File Name: <input type="text" name="MatchFile" value="${status.value}" />
		<form:errors path="MatchFile" />
		Match File Type: <input path="matchFormat" type="text" name="matchFormat" value="xls" />
		<form:errors path="matchFormat" />
		Matching File Location: <input type="text" name="matchLocation" value="${status.value}" />
		<form:errors path="matchLocation" />
	</p>
	<h3>Target Database Data</h3>
	<p>
		
		User Name: <input type="text" name="leftUserName" value="${status.value}" />
		
		Password: <input type="password" name="leftPassword" value="${status.value}" />	
		
		Database Driver: <input type="text" name="leftDbDriver" value="${status.value}" value="com.mysql.jdbc.Driver"/>	
		<form:errors path="leftDbDriver" />
		Database Name: <input type="text" name="leftDbName" value="${status.value}" value="openmrs"/>	
		<form:errors path="leftDbName" />
		Database Location<input type="text" name="leftDbLocation" value="${status.value}" />
		<form:errors path="leftDbLocation" />
	</p>	
	<h3>Source Database</h3>
	<p>
		
		User Name: <input type="text" name="rightUserName" value="${status.value}" /> 	

		Password: <input type="password" name="rightPassword" value="${status.value}" />
		
		Database Driver: <input type="text" name="rightDbDriver" value="${status.value}" value="com.mysql.jdbc.Driver"/>
		<form:errors path="rightDbDriver" />
		Database Name: <input type="text" name="rightDbName" value="${status.value}" value="openmrs"/>
		<form:errors path="rightDbName" />
		Database Location: <input type="text" name="rightDbLocation" value="${status.value}" />
		<form:errors path="rightDbLocation" />
	</p>
	<h3>Migration Options</h3>
	 <p>
		<b>Allow Commit<br>
		<input type="checkbox" name="allowCommit" value="True"checked>True
  		<input type="checkbox" name="allowCommit" value="False" >False
		<br><b>Reset Process</b><br>
			<input type="checkbox" name="resetProcess" value="True">True
  			<input type="checkbox" name="resetProcess" value="False" checked>False
		<form:input path="treeLimit"/>
		<br><b>Tree Limit</b><br>
		<input type="number" name="treeLimit" value="0" />
	</p>
	<td><input type="submit" value="Start Migration" <a href="continueMigration.jsp" />></td>

</form:form>
</body>
</html>

<%@ include file="/WEB-INF/template/footer.jsp"%>
