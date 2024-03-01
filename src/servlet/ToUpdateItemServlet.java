package servlet;

import db.DbItem;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Item;
import model.ItemCategory;
import model.User;

import java.io.IOException;

@WebServlet("/save-item")
public class ToUpdateItemServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        User user = (User) request.getSession().getAttribute("currentUser");
        if(user!=null) {

            String title = request.getParameter("title");
            String content = request.getParameter("content");
            int id = Integer.parseInt(request.getParameter("id"));
            int category_id = Integer.parseInt(request.getParameter("category"));

            Item item = DbItem.getItemById(id);
            ItemCategory c = DbItem.getCategory(category_id);

            if(item!=null) {
                item.setTitle(title);
                item.setContent(content);
                item.setItemCategory(c);

                DbItem.updateItem(item);

                response.sendRedirect("/item-details?id="+id);

            }else{
                response.sendRedirect("/");
            }

        }else{
            response.sendRedirect("/login");
        }
    }
}