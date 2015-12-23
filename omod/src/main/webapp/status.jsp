<%@ include file="/WEB-INF/template/include.jsp"%>
<%@ include file="/WEB-INF/template/header.jsp"%>
<%@ include file="template/localHeader.jsp"%>

<html>
<style type="text/css">
    @import url(http://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,700italic,300,400,700);
    body {
        font-family: "Open Sans", sans-serif;
        font-size: 20px;
    }
    .container {
        width: 800px;
        margin: 5em auto;
        padding: 50px;
        text-align: center;
    }
</style>
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
