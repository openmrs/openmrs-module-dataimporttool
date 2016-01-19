<%@ include file="/WEB-INF/template/include.jsp"%>
<%@ include file="/WEB-INF/template/header.jsp"%>
<%@ include file="template/localHeader.jsp"%>
 
 
<html>
<head>
<openmrs:htmlInclude file="/moduleResources/dataimporttool/css/component.css"/>
<openmrs:htmlInclude file="/moduleResources/dataimporttool/css/font-awesome.min.css"/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
    <div align="center" class="dialog">
        <div class="dialog-header">
            <i class="fa fa-times"></i>
            <h3><spring:message code="dataimporttool.migrationError" /></h3>
        </div>
        <div class="dialog-content">
            <p><spring:message code="dataimporttool.errorLog" /></p>
            <br />
            <p><em><spring:message code="dataimporttool.errorRef" /></em></p>
            <br />        
            <table class='dialogTable'><tbody><tr>
                <td><a href="#" onclick="history.go(-1)" class="button back"><i class="fa fa-pencil"></i><spring:message code="dataimporttool.edit" /></a></td>
                <td><a href="${pageContext.request.contextPath}/admin/maintenance/serverLog.form" target="_blank" class="button logs"><i class="fa fa-list"></i><spring:message code="dataimporttool.logs" /></a></td>
                <td><a href="${pageContext.request.contextPath}/module/dataimporttool/help.form" target="_blank" class="button btnhelp"><i class="fa fa-info-circle"></i><spring:message code="dataimporttool.help" /></a></td>
            </tr></tbody></table>
        </div>
    </div>
</body>
</html>

<%@ include file="/WEB-INF/template/footer.jsp"%>
