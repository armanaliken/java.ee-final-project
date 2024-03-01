<%@ page import="model.Item" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Title</title>
    <%@include file="head.jsp"%>
</head>
<body>
<%@include file="navbar.jsp"%>
<div class="container mt-3">
    <div class="row mt-3">
        <div class="col-12">
            <%
                ArrayList<Item> item = (ArrayList<Item>) request.getAttribute("item");
                if(item!=null){
                    for(Item n : item){
            %>
            <div class="p-5 mb-3" style="background-color: #dee1df;">
                <a href="/item-details?id=<%=n.getId()%>">
                    <h3><%=n.getTitle()%></h3>
                </a>
                <p><%=n.getContent()%></p>
                <p>
                    Category: <strong><%=n.getItemCategory().getName()%></strong> <br>
                    At <strong><%=n.getPostDate()%></strong>
                </p>
            </div>
            <%
                    }
                }
            %>
        </div>
    </div>
</div>
</body>
</html>