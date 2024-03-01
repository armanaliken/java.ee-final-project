package servlet;

import db.DbItem;
import model.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(value = "/add-item")
public class ToAddItemServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        User user = (User) request.getSession().getAttribute("currentUser");
        if(user!=null) {

            String title = request.getParameter("title");
            String content = request.getParameter("content");
            String category = request.getParameter("category");

            Item item = new Item();
            item.setTitle(title);
            item.setContent(content);

            ItemCategory c = new ItemCategory();
            c.setName(category);

            item.setItemCategory(c);

            DbItem.addItem(item);

            response.sendRedirect("/add-item-page");

        }else{
            response.sendRedirect("/login");
        }
    }
}