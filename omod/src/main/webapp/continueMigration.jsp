<%@ include file="/WEB-INF/template/include.jsp"%>

<%@ include file="/WEB-INF/template/header.jsp"%>

<%@ include file="template/localHeader.jsp"%>

<% session.removeAttribute("ditService"); %>

<jsp:useBean id="ditService" scope="session"
    class="org.openmrs.module.dataimporttool.api.DataImportToolService;"/>

<% task.setRunning(true); %>

<% new Thread(ditService).start(); %>

html>

<head>
    <title> </title>
    <% if (ditService.isRunning()) { %>
        <SCRIPT LANGUAGE="JavaScript">
            setTimeout("location='continueMigration.jsp'", 1000);
        </SCRIPT>
    <% } %>
    
<h1 align="center">Data Validation and Translation...</h1>

<h2 align="center">
    Result: <%= ditService.getResult() %><br />
    <% int percent = ditService.getPercent(); %>
    <%= percent %>%
</h2>

<table width="60%" align="center"
        border="1" cellpadding="0" cellspacing="2">
    <TR>
        <% for (int i = 10; i <= percent; i += 10) { %>
            <td width="10%" bgcolor="#000080">&nbsp;</td>
        <% } %>
        <% for (int i = 100; i > percent; i -= 10) { %>
            <td width="10%">&nbsp;</TD>
        <% } %>
    </tr>
</table>

<table width="100%" border="0" cellpadding="0" cellspacing="0">
    <tr>
        <td align="center">
            <% if (ditService.isRunning()) { %>
                Running
            <% } else { %>
                <% if (ditService.isCompleted()) { %>
                   <jsp:redirect page="status.jsp"/>
                <% } else if (!ditService.isStarted()) { %>
                     Data Migration Not Started
                <% } else { %>
                    <jsp:redirect page="status.jsp"/>
                <% } %>
            <% } %>
        </td>
    </tr>
</head>

<body>



<%@ include file="/WEB-INF/template/footer.jsp"%>
