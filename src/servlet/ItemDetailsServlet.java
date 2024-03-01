package servlet;

import db.DbComment;
import db.DbItem;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Comment;
import model.Item;
import model.ItemCategory;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(value = "/item-details")
public class ItemDetailsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Item item = DbItem.getItemById(id);
        request.setAttribute("item", item);

        ArrayList<ItemCategory> categories = DbItem.getCategories();
        request.setAttribute("cats", categories);

        if (item != null) {
            ArrayList<Comment> comments = DbComment.getComments(Math.toIntExact(item.getId()));
            request.setAttribute("comments", comments);
        }
        request.getRequestDispatcher("itemdetails.jsp").forward(request, response);
    }
}