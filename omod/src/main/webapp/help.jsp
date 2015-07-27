<%@ include file="/WEB-INF/template/include.jsp"%>

<%@ include file="/WEB-INF/template/header.jsp"%>

<%@ include file="localHeader.jsp"%>

<openmrs:require anyPrivilege="Manage Data Migration" otherwise="/login.htm" redirect="/module/dataimportool/help.form" />

<h2><spring:message code="dataimporttool.help.title" /></h2>

Main documentation page for the Data Import Tool is on Github: 
<a href="https://github.com/esaude/dmt-guide>Data Migration Tool Guide</a>


<%@ include file="/WEB-INF/template/footer.jsp"%>
