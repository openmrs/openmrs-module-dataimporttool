<%@ include file="/WEB-INF/template/include.jsp"%>
<%@ include file="/WEB-INF/template/header.jsp"%>
<%@ include file="template/localHeader.jsp"%>

<openmrs:htmlInclude file="/moduleResources/dataimporttool/css/statusPages.css"/>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Migration Success!</title>
</head>
<body>
    <div class="container">
        <h1>Data Migration Successful.</h1>
        <h3><a href="openmrs/admin/maintenance/serverLog.form">View the Logs for Complete details!</a></h3>
    </div>
</body>
</html>

<%@ include file="/WEB-INF/template/footer.jsp"%>
