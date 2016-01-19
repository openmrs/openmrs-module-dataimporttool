<%@ include file="/WEB-INF/template/include.jsp"%>
<%@ include file="/WEB-INF/template/header.jsp"%>
<%@ include file="template/localHeader.jsp"%>


<html>
<head>
<openmrs:htmlInclude file="/moduleResources/dataimporttool/css/component.css"/>
<openmrs:htmlInclude file="/moduleResources/dataimporttool/css/font-awesome.min.css"/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Migration Success!</title>
</head>
<body>
    <div align="center" class="dialog">
        <div class="dialog-header">
            <i class="fa fa-check"></i>
            <h3><spring:message code="dataimporttool.migrationSuccess" /></h3>
        </div>
        <div class="dialog-content">
            <p><spring:message code="dataimporttool.successLog" /></p>
            <br />
            <a href="${pageContext.request.contextPath}/" class="button back"><i class="fa fa-home"></i><spring:message code="dataimporttool.home" /></a>
            <a href="${pageContext.request.contextPath}/module/dataimporttool/help.form" class="button btnhelp"><i class="fa fa-list"></i><spring:message code="dataimporttool.logs" /></a>
        </div>
    </div>
</body>
</html>

<%@ include file="/WEB-INF/template/footer.jsp"%>
