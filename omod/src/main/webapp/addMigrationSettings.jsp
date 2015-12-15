<%@ include file="/WEB-INF/template/include.jsp"%>

<%@ include file="/WEB-INF/template/header.jsp"%>

<%@ include file="template/localHeader.jsp"%>
    
<openmrs:htmlInclude file="/moduleResources/dataimporttool/css/component.css"/>

<form:form commandName="dit" method="post">
<form:errors />
<td colspan="2"><h2>Add Migration Settings</h2></td>
	<fieldset>
        <legend>Matching File Data</legend>
		<div id="block">
            <td><form:label path="matchFile">Matching File Name</form:label></td>
            <td><form:input path="matchFile" /></td> 
            <form:errors path="matchFile" />
            <span class="appear">
            <p>Fill in a file name (without file type)</p>
            </span>
        </div>
		<div id="block">
            <td><form:label path="matchFormat">Match File Type</form:label></td>
            <td><form:input path="matchFormat" /></td> 
            <form:errors path="matchFormat" />
            <span class="appear">
            <p>Fill in a file type of the file (it should be xls).</p>
            </span>
        </div>
		<div id="block">
            <td><form:label path="matchLocation">Matching File Location</form:label></td>
            <td><form:input path="matchLocation" /></td> 
            <form:errors path="matchLocation" />
        </div>
	</fieldset>
	<fieldset>
        <legend>Target Database Data</legend>
		<div id="block">
            <td><form:label path="leftUserName">User Name</form:label></td>
            <td><form:input path="leftUserName" /></td>
        </div>
	
		<div id="block">
            <td><form:label path="leftPassword">Password</form:label></td>
            <td><form:input path="leftPassword" /></td>
        </div>
	
		<div id="block">
            <td><form:label path="leftDbDriver">Database Driver</form:label></td>
            <td><form:input path="leftDbDriver" /></td> 
            <form:errors path="leftDbDriver" />
        </div>
		
		<div id="block">
            <td><form:label path="leftDbName">Database Name</form:label></td>
            <td><form:input path="leftDbName" /></td> 	
            <form:errors path="leftDbName" /><br>
        </div>
		
		<div id="block">
            <td><form:label path="leftDbLocation">Database Location</form:label></td>
            <td><form:input path="leftDbLocation" /></td> 
            <form:errors path="leftDbLocation" />
        </div>
	</fieldset>
	<fieldset>
        <legend>Source Database</legend>		
		<div id="block">
            <td><form:label path="rightUserName">User Name</form:label></td>
            <td><form:input path="rightUserName" /></td> 
            <form:errors path="rightUserName" />
        </div>
		
		<div id="block">
            <td><form:label path="rightPassword">Password</form:label></td>
            <td><form:input path="rightPassword" /></td>
        </div>
		
		<div id="block">
            <td><form:label path="rightDbDriver">Database Driver</form:label></td>
            <td><form:input path="rightDbDriver" /></td>
            <form:errors path="rightDbDriver" />
        </div>
		
		<div id="block">
            <td><form:label path="rightDbName">Database Name</form:label></td>
            <td><form:input path="rightDbName" /></td> 
            <form:errors path="rightDbName" /><br>
            </div>
	
		<div id="block">
            <td><form:label path="rightDbLocation">Database Location</form:label></td>
            <td><form:input path="rightDbLocation" /></td> 
            <form:errors path="rightDbLocation" />
        </div>
	</fieldset>
    <fieldset>
        <legend>Migration Options</legend>
	 	<div id="box">
            <td><form:label path="allowCommit">Allow Commit</form:label></td>
            <form:checkbox path="allowCommit" value="true"/>
        </div>
  		<div id="box">
            <form:label path="resetProcess">Reset Process</form:label></td>
  		    <form:checkbox path="resetProcess" value="true"/>
        </div>  			
		<div id="block">
            <td><form:label path="treeLimit">Tree Limit</form:label></td>
            <td><form:input path="treeLimit" /></td>
        </div>
	</fieldset>
	<div id="button"><td colspan="2"><input type="submit" value="Start Migration" ></td></div>

</form:form>
</body>
</html>

<%@ include file="/WEB-INF/template/footer.jsp"%>
