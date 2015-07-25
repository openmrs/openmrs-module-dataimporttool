<form method="POST">

	<!-- fieldsets -->
	<fieldset>
		<h2 class="fs-title">Matching File Data</h2>
		<h3 class="fs-subtitle">Please fill in the details</h3>
		<spring:bind path="dit.matchFile">
			<input type="text" name="MatchFile" value="${status.value}" placeholder="Matching File Name" />
			<c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
            	</spring:bind>
		<spring:bind path="dit.matchFormat">
			<input type="text" name="matchFormat" value="xls" placeholder="Match File Type" />
		</spring:bind>
		<spring:bind path="dit.matchLocation">
			<input type="text" name="matchLocation" value="${status.value}" placeholder="Matching File Location" />
			<c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
            	</spring:bind>
	</fieldset>	
	<fieldset>
		<h2 class="fs-title">Target Database Data</h2>
		<h3 class="fs-subtitle">Please fill in the left database details</h3>
		<spring:bind path="dit.leftUserName">
			<input type="text" name="leftUserName" value="${status.value}" placeholder="User Name" />
			<c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
            	</spring:bind>
		<spring:bind path="dit.leftPassword">
			<input type="password" name="leftPassword" value="${status.value}" placeholder="Password" />
			<c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
            	</spring:bind>
		<spring:bind path="dit.leftDbDriver">
			<input type="text" name="leftDbDriver" value="${status.value}" placeholder="Database Driver" value="com.mysql.jdbc.Driver"/>
			<c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
            	</spring:bind>
		<spring:bind path="dit.leftDbDriver">
			<input type="text" name="leftDbName" value="${status.value}" placeholder="Database Name" value="openmrs"/>
			<c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
            	</spring:bind>
		<spring:bind path="dit.leftDbLocation">
			<input type="text" name="rightDbLocation" value="${status.value}" placeholder="Database Location" />
			<c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
            	</spring:bind>
	</fieldset>
 	<fieldset>
		<h2 class="fs-title">Source Database</h2>
		<h3 class="fs-subtitle">Please fill in the right database details</h3>
		<spring:bind path="dit.rightUserName">
			<input type="text" name="rightUserName" value="${status.value}" placeholder="User Name" />
			<c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
            	</spring:bind>
		<spring:bind path="dit.rightPassword">
			<input type="password" name="rightPassword" value="${status.value}" placeholder="Password" />
			<c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
            	</spring:bind>
		<spring:bind path="dit.rightDbDriver">
			<input type="text" name="rightDbDriver" value="${status.value}" placeholder="Database Driver" value="com.mysql.jdbc.Driver"/>
			<c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
            	</spring:bind>
		<spring:bind path="dit.rightDbDriver">
			<input type="text" name="rightDbName" value="${status.value}" placeholder="Database Name" value="openmrs"/>
			<c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
            	</spring:bind>
		<spring:bind path="dit.rightDbLocation">
			<input type="text" name="rightDbLocation" value="${status.value}" placeholder="Database Location" />
			<c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
            	</spring:bind>
	</fieldset>
	<fieldset>
		<h2 class="fs-title">Migration Options</h2>
		<h3 class="fs-subtitle">Configuration</h3>
    		<spring:bind path="dit.allowCommit">
			<input type="checkbox" name="allowCommit" value="True"checked> Yes<br>
  			<input type="checkbox" name="allowCommit" value="False" > No<br>
            	</spring:bind>
		<spring:bind path="dit.resetProcess">
			<input type="checkbox" name="resetProcess" value="True"> Yes<br>
  			<input type="checkbox" name="resetProcess" value="False" checked> No<br>
            	</spring:bind>   
		<spring:bind path="dit.treeLimit">
		<input type="number" name="treeLimit" 
    			placeholder="Tree Limit" value="0" />
		<c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
            	</spring:bind>
	</fieldset>
	<input type="submit"value="<openmrs:message code="dit.save" name ="startMigration"class="submit action-button" value="Start Migration" />
</form>

<%@ include file="/WEB-INF/template/footer.jsp"%>
























