package servlet;

import db.DbComment;
import db.DbItem;
import model.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(value = "/add-comment")
public class ToAddCommentServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        User user = (User) request.getSession().getAttribute("currentUser");

        if (user != null) {

            String commentText = request.getParameter("comment");
            int itemId = Integer.parseInt(request.getParameter("item_id"));

            Item item = DbItem.getItemById(itemId);

            if (item != null) {

                Comment comment = new Comment();
                comment.setItem(item);
                comment.setUser(user);
                comment.setComment(commentText);

                DbComment.addComment(comment);

                response.sendRedirect("/item-details?id="+itemId);

            }else{
                response.sendRedirect("/");
            }

        } else {
            response.sendRedirect("/login");
        }
    }
}