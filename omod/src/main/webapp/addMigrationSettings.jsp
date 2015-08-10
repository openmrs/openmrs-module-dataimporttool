<%@ include file="/WEB-INF/template/include.jsp"%>

<%@ include file="/WEB-INF/template/header.jsp"%>

<%@ include file="template/localHeader.jsp"%>

<form:form action="register" modelAttribute="dit" method="post">
<form:errors />
<td colspan="2" align="center"><h2>Add Migration Settings</h2></td><br>
	<h3>Matching File Data</h3>
	<p>
		<td><form:label path="matchFile">Matching File Name</form:label></td>
        <td><form:input path="matchFile" /></td> 
		<form:errors path="matchFile" />
		<td><form:label path="matchFormat">Match File Type</form:label></td>
        <td><form:input path="matchFormat" /></td> 
		<form:errors path="matchFormat" />
		<td><form:label path="matchLocation">Matching File Location</form:label></td>
        <td><form:input path="matchLocation" /></td> 
		<form:errors path="matchLocation" />
	</p>
	<h3>Target Database Data</h3>
	<p>
		
		<td><form:label path="leftUserName">User Name</form:label></td>
        <td><form:input path="leftUserName" /></td> 
	
		<td><form:label path="leftPassword">Password</form:label></td>
        <td><form:input path="leftPassword" /></td> 
	
		<td><form:label path="leftDbDriver">Database Driver</form:label></td>
        <td><form:input path="leftDbDriver" /></td> 
		<form:errors path="leftDbDriver" />
		
		<td><form:label path="leftDbName">Database Name</form:label></td>
        <td><form:input path="leftDbName" /></td> 	
		<form:errors path="leftDbName" />
		
		<td><form:label path="leftDbLocation">Database Location</form:label></td>
        <td><form:input path="leftDbLocation" /></td> 
		<form:errors path="leftDbLocation" />
	</p>	
	<h3>Source Database</h3>
	<p>
		
		<td><form:label path="rightUserName">User Name</form:label></td>
        <td><form:input path="rightUserName" /></td> 
		 	
		<td><form:label path="rightPassword">Password</form:label></td>
        <td><form:input path="rightPassword" /></td> 
		
		<td><form:label path="rightDbDriver">Database Driver</form:label></td>
        <td><form:input path="rightDbDriver" /></td>
		<form:errors path="rightDbDriver" />
		
		<td><form:label path="rightDbName">Database Name</form:label></td>
        <td><form:input path="rightDbName" /></td> 
		<form:errors path="rightDbName" />
	
		<td><form:label path="rightDbLocation">Database Location</form:label></td>
        <td><form:input path="rightDbLocation" /></td> 
		<form:errors path="rightDbLocation" />
	</p>
	<h3>Migration Options</h3>
	 <p>
	 	<form:input path="allowCommit"/>
		<b>Allow Commit<br>
		<input type="checkbox" name="allowCommit" value="True"checked>True
  		<input type="checkbox" name="allowCommit" value="False" >False
  		
  		<form:input path="resetProcess"/>
		<br><b>Reset Process</b><br>
			<input type="checkbox" name="resetProcess" value="True">True
  			<input type="checkbox" name="resetProcess" value="False" checked>False
  			
		<form:input path="treeLimit"/>
		<br><b>Tree Limit</b><br>
		<input type="number" name="treeLimit" value="0" />
	</p>
	<td colspan="2" align="center"><input type="submit" value="Start Migration" <a href="continueMigration.jsp" />></td>

</form:form>
</body>
</html>

<%@ include file="/WEB-INF/template/footer.jsp"%>
