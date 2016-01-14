<%@ include file="/WEB-INF/template/include.jsp"%>

<%@ include file="/WEB-INF/template/header.jsp"%>

<%@ include file="template/localHeader.jsp"%>
    
<openmrs:htmlInclude file="/moduleResources/dataimporttool/css/component.css"/>

<form:form commandName="dit" method="post">
<form:errors />
<td colspan="2"><h2><spring:message code="dataimporttool.configureMigrationSettings" /></h2></td>
	<fieldset>
        <legend><spring:message code="dataimporttool.matchFileData" /></legend>
		<div id="block">
            <td><form:label path="matchFile"><spring:message code="dataimporttool.fileName" /></form:label></td>
            <td><form:input path="matchFile" /></td> 
            <form:errors path="matchFile" />
            <span class="appear">
            <p><spring:message code="dataimporttool.fileNamePopup" /></p>
            </span>
        </div>
		<div id="block">
            <td><form:label path="matchFormat"><spring:message code="dataimporttool.fileType" /></form:label></td>
            <td><form:input path="matchFormat" /></td> 
            <form:errors path="matchFormat" />
            <span class="appear">
            <p><spring:message code="dataimporttool.fileTypePopup" /></p>
            </span>
        </div>
		<div id="block">
            <td><form:label path="matchLocation"><spring:message code="dataimporttool.fileLocation" /></form:label></td>
            <td><form:input path="matchLocation" /></td> 
            <form:errors path="matchLocation" />
            <span class="appear">
            <p><spring:message code="dataimporttool.fileLocationPopup" /></p>
            </span>
        </div>
	</fieldset>
	<fieldset>
        <legend><spring:message code="dataimporttool.targetDatabase" /></legend>
		<div id="block">
            <td><form:label path="leftUserName"><spring:message code="dataimporttool.username" /></form:label></td>
            <td><form:input path="leftUserName" /></td>
            <span class="appear">
            <p><spring:message code="dataimporttool.usernamePopup" /></p>
            </span>
        </div>
	
		<div id="block">
            <td><form:label path="leftPassword"><spring:message code="dataimporttool.password" /></form:label></td>
            <td><form:input path="leftPassword" /></td>
            <span class="appear">
            <p><spring:message code="dataimporttool.passwordPopup" /></p>
            </span>
        </div>
	
		<div id="block">
            <td><form:label path="leftDbDriver"><spring:message code="dataimporttool.databaseDriver" /></form:label></td>
            <td><form:input path="leftDbDriver" /></td> 
            <form:errors path="leftDbDriver" />
            <span class="appear">
            <p><spring:message code="dataimporttool.databaseDriverPopup" /></p>
            </span>
        </div>
		
		<div id="block">
            <td><form:label path="leftDbName"><spring:message code="dataimporttool.databaseName" /></form:label></td>
            <td><form:input path="leftDbName" /></td> 	
            <form:errors path="leftDbName" /><br>
            <span class="appear">
            <p><spring:message code="dataimporttool.databaseNamePopup" /></p>
            </span>
        </div>
		
		<div id="block">
            <td><form:label path="leftDbLocation"><spring:message code="dataimporttool.databaseLocation" /></form:label></td>
            <td><form:input path="leftDbLocation" /></td> 
            <form:errors path="leftDbLocation" />
            <span class="appear">
            <p><spring:message code="dataimporttool.databaseLocationPopup" /></p>
            </span>
        </div>
	</fieldset>
	<fieldset>
        <legend><spring:message code="dataimporttool.sourceDatabase" /></legend>		
		<div id="block">
            <td><form:label path="rightUserName"><spring:message code="dataimporttool.username" /></form:label></td>
            <td><form:input path="rightUserName" /></td> 
            <form:errors path="rightUserName" />
            <span class="appear">
            <p><spring:message code="dataimporttool.usernamePopup" /></p>
            </span>
        </div>
		
		<div id="block">
            <td><form:label path="rightPassword"><spring:message code="dataimporttool.password" /></form:label></td>
            <td><form:input path="rightPassword" /></td>
            <span class="appear">
            <p><spring:message code="dataimporttool.passwordPopup" /></p>
            </span>
        </div>
		
		<div id="block">
            <td><form:label path="rightDbDriver"><spring:message code="dataimporttool.databaseDriver" /></form:label></td>
            <td><form:input path="rightDbDriver" /></td>
            <form:errors path="rightDbDriver" />
            <span class="appear">
            <p><spring:message code="dataimporttool.databaseDriverPopup" /></p>
            </span>
        </div>
		
		<div id="block">
            <td><form:label path="rightDbName"><spring:message code="dataimporttool.databaseName" /></form:label></td>
            <td><form:input path="rightDbName" /></td> 
            <form:errors path="rightDbName" /><br>
            <span class="appear">
            <p><spring:message code="dataimporttool.databaseNamePopup" /></p>
            </span>
        </div>
	
		<div id="block">
            <td><form:label path="rightDbLocation"><spring:message code="dataimporttool.databaseLocation" /></form:label></td>
            <td><form:input path="rightDbLocation" /></td> 
            <form:errors path="rightDbLocation" />
            <span class="appear">
            <p><spring:message code="dataimporttool.databaseLocationPopup" /></p>
            </span>
        </div>
	</fieldset>
    <fieldset>
        <legend><spring:message code="dataimporttool.migrationOptions" /></legend>
	 	<div id="box">
            <td><form:label path="allowCommit"><spring:message code="dataimporttool.allowCommit" /></form:label></td>
            <form:checkbox path="allowCommit" value="true"/>
        </div>
  		<div id="box">
            <form:label path="resetProcess"><spring:message code="dataimporttool.resetProcess" /></form:label></td>
  		    <form:checkbox path="resetProcess" value="true"/>
        </div>  			
		<div id="block">
            <td><form:label path="treeLimit"><spring:message code="dataimporttool.treeLimit" /></form:label></td>
            <td><form:input path="treeLimit" /></td>
        </div>
	</fieldset>
    <spring:message code="dataimporttool.startMigration" var="startMigration" />
    <div id="button">
	<td colspan="2"><input type="submit" value="${startMigration}"></td>
    </div>
</form:form>
</body>
</html>

<%@ include file="/WEB-INF/template/footer.jsp"%>
