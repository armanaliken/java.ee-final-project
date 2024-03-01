<%@ page import="java.util.ArrayList" %>
<%@ page import="model.Item" %>
<%@ page import="model.Comment" %>
<%@ page import="model.ItemCategory" %>
<%@ page import="java.util.Objects" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <%@include file="head.jsp" %>
</head>
<body>
<%@include file="navbar.jsp" %>
<div class="container mt-3">
    <div class="row mt-3">
        <div class="col-12">
            <%
                Item item = (Item) request.getAttribute("item");
                if (item != null) {

            %>
            <div class="p-5 mb-3" style="background-color: #dee1df;">

                <h3><%=item.getTitle()%>
                </h3>

                <p><%=item.getContent()%>
                </p>
                <p>
                    Category: <strong><%=item.getItemCategory().getName()%>
                </strong>
                    At <strong><%=item.getPostDate()%>
                </strong>
                </p>
                <%
                    if (currentUser != null && currentUser.getRoleId() == 1 ) {
                %>
                <div>
                    <!-- Button trigger modal -->
                    <button type="button" class="btn btn-primary btn-sm" data-bs-toggle="modal"
                            data-bs-target="#editItem">
                        EDIT
                    </button>

                    <button type="button" class="btn btn-danger btn-sm ms-2" data-bs-toggle="modal"
                            data-bs-target="#deleteBook">
                        Delete Book
                    </button>
                </div>
            </div>
            <!-- Modal -->
            <div class="modal fade" id="deleteBook" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1"
                 aria-labelledby="staticBackdropLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <form action="/delete-item" method="post">
                            <input type="hidden" name="id" value="<%=item.getId()%>">
                            <div class="modal-header">
                                <h1 class="modal-title fs-5">Confirm Delete</h1>
                                <button type="button" class="btn-close" data-bs-dismiss="modal"
                                        aria-label="Close"></button>
                            </div>
                            <div class="modal-body">
                                <h5 class="text-center">Are you sure?</h5>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">NO</button>
                                <button class="btn btn-danger">YES</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>

            <div class="modal fade" id="editItem" data-bs-backdrop="static" data-bs-keyboard="false"
                 tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
                <div class="modal-dialog modal-lg">
                    <div class="modal-content">
                        <form action="/save-item" method="post">
                            <input type="hidden" name="id" value="<%=item.getId()%>">
                            <div class="modal-header">
                                <h1 class="modal-title fs-5" id="staticBackdropLabel">Edit News</h1>
                                <button type="button" class="btn-close" data-bs-dismiss="modal"
                                        aria-label="Close"></button>
                            </div>
                            <div class="modal-body">
                                <div class="row">
                                    <div class="col-12">
                                        <label>
                                            TITLE :
                                        </label>
                                    </div>
                                </div>
                                <div class="row mt-2">
                                    <div class="col-12">
                                        <input type="text" class="form-control" name="title" required
                                               placeholder="Insert title:" value="<%=item.getTitle()%>">
                                    </div>
                                </div>

                                <div class="row mt-3">
                                    <div class="col-12">
                                        <label>
                                            CATEGORY 2:
                                        </label>
                                    </div>
                                </div>
                                <div class="row mt-2">
                                    <div class="col-12">
                                        <select class="form-select" name="category">
                                            <%
                                                ArrayList<ItemCategory> categories = (ArrayList<ItemCategory>) request.getAttribute("cats");
                                                if (categories != null) {
                                                    for (ItemCategory c : categories) {
                                            %>
                                            <option <%=(Objects.equals(c.getId(), item.getItemCategory().getId()) ? "selected" : " ")%>
                                                    value="<%=c.getId()%>"><%=c.getName() %>
                                            </option>
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
                                                <textarea class="form-control" name="content"
                                                          placeholder="Insert content:" required
                                                          rows="10"><%=item.getContent()%></textarea>
                                    </div>
                                </div>


                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel
                                </button>
                                <button class="btn btn-success">Update</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <%
            }
        %>
    </div>
    <%
        if (currentUser != null) {
    %>
    <div>
        <form action="/add-comment" method="post">
            <input type="hidden" name="item_id" value="<%=item.getId()%>">
            <div class="row">
                <div class="col-12">
                    <textarea class="form-control" name="comment"></textarea>
                </div>
            </div>
            <div class="row mt-3">
                <div class="col-12">
                    <button class="btn btn-dark btn-sm">ADD COMMENT</button>
                </div>
            </div>
        </form>
    </div>
    <%
        }
    %>
    <div class="row">
        <div class="col-12">
            <%
                ArrayList<Comment> comments = (ArrayList<Comment>) request.getAttribute("comments");
                if (comments != null){
                    for(Comment comment : comments){
            %>
            <div class="list-group">
                <a href="JavaScript:void(0)" class="list-group-item list-group-item-action">
                    <div class="d-flex w-100 justify-content-between">
                        <h5 class="mb-1"><%=comment.getUser().getFullName()%></h5>
                        <small class="text-body-secondary"><%=comment.getPostDate()%></small>
                    </div>
                    <p class="mb-1"><%=comment.getComment()%></p>
                </a>
            </div>
            <%
                    }
                }
            %>
        </div>
    </div>
    <%

        }
    %>
</div>
</div>
</div>
</body>
</html>