<%@ include file="/WEB-INF/template/include.jsp"%>
<%@ include file="/WEB-INF/template/header.jsp"%>
<%@ include file="template/localHeader.jsp"%>
 
 
<html>
<head>
<openmrs:htmlInclude file="/moduleResources/dataimporttool/css/component.css"/>
<openmrs:htmlInclude file="/moduleResources/dataimporttool/css/font-awesome.min.css"/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Migration Error!</title>
</head>
<body>
    <div align="center" class="dialog">
        <div class="dialog-header">
            <i class="fa fa-times"></i>
            <h3>Migration Error</h3>
        </div>
        <div class="dialog-content">
            <p>Errors occured during Migration. Check the Logs.</p>
            <br />
            <p><em>Edit your migration settings or look on the guide.</em></p>
            <br />
            <a href="#" onclick="history.go(-1)" class="button back"><i class="fa fa-pencil"></i> Edit </a>
            <a href="${pageContext.request.contextPath}/admin/maintenance/serverLog.form" class="button logs"><i class="fa fa-list"></i> Logs </a>
            <a href="${pageContext.request.contextPath}/module/dataimporttool/help.form" class="button help"><i class="fa fa-info-circle"></i> Help </a>
        </div>
    </div>
</body>
</html>

<%@ include file="/WEB-INF/template/footer.jsp"%>
