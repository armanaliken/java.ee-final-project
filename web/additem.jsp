<%@ page import="model.ItemCategory" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <%@include file="head.jsp"%>
</head>
<body>
<%@include file="navbar.jsp"%>
<div class="container mt-5">
    <div class="row">
        <div class="col-6 mx-auto">
            <form action="${pageContext.request.contextPath}/add-item" method="post">
                <div class="row">
                    <div class="col-12">
                        <label>
                            TITLE :
                        </label>
                    </div>
                </div>
                <div class="row mt-2">
                    <div class="col-12">
                        <input type="text" class="form-control" name="title" required placeholder="Insert title:">
                    </div>
                </div>
                <div class="row">
                    <div class="col-12">
                        <label>
                            CATEGORIES :
                        </label>
                    </div>
                </div>
                <div class="row mt-2">
                    <div class="col-12">
                        <select class="form-select" name="category">
                            <%
                                ArrayList<ItemCategory> categories = (ArrayList<ItemCategory>) request.getAttribute("cats");
                                if (categories != null) {
                                    for (ItemCategory category : categories) {
                            %>
                            <option value="<%=category.getId()%>"><%=category.getName()%></option>
                            <%
                                    }
                                }
                            %>
                        </select>
                    </div>
                </div>
                <div class="row mt-3">
                    <div class="col-12">
                        <label>
                            CONTENT :
                        </label>
                    </div>
                </div>
                <div class="row mt-2">
                    <div class="col-12">
                        <textarea class="form-control" name="content" placeholder="Insert content:" required rows="10"></textarea>
                    </div>
                </div>
                <div class="row mt-3">
                    <div class="col-12">
                        <button class="btn btn-success">ADD POST</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>