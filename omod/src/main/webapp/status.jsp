<%@ include file="/WEB-INF/template/include.jsp"%>
<%@ include file="/WEB-INF/template/header.jsp"%>

<%@ include file="template/localHeader.jsp"%>
<openmrs:require anyPrivilege="Start Migration" otherwise="/login.htm" redirect="/module/dataimporttool/startMigration.form" />

<%@ include file="addMigrationSettings.jsp" %>

<%@ include file="/WEB-INF/template/footer.jsp"%>
