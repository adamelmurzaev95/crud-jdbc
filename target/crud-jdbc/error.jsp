<%--
  Created by IntelliJ IDEA.
  User: Адам
  Date: 10.01.2021
  Time: 3:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% Exception exception = pageContext.getException(); %>
<html>
<head>
    <title>Error</title>
</head>
<body>
    <center>
        <h1>Error</h1>
        <h2><%=exception.getMessage()%><br></h2>
    </center>
</body>
</html>
