<%@ include file="/WEB-INF/template/include.jsp"%>
<%@ include file="/WEB-INF/template/header.jsp"%>
<%@ include file="template/localHeader.jsp"%>

<openmrs:htmlInclude file="/moduleResources/dataimporttool/css/statusPages.css"/>
 
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Migration Error!</title>
</head>
<body>
    <div class="container">
        <h1>Errors occured during Migration.</h1>
        <h3><a href="openmrs/admin/maintenance/serverLog.form">Check the logs</a></h3>
    </div>
</body>
</html>

<%@ include file="/WEB-INF/template/footer.jsp"%>
