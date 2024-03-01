
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css
">
    <%@include file="head.jsp"%>

</head>
<body>
<%@include file="navbar.jsp"%>

<div class="container mt-3">
    <div class="row">
        <div class="col-12 mx-auto">
            <h1>Hello <%=currentUser.getFullName()%></h1>

        </div>
    </div>
</div>


</body>
</html>