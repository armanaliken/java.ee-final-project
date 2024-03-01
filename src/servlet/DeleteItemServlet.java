package servlet;

import db.DbItem;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.User;

import java.io.IOException;
@WebServlet(value = "/delete-item")
public class DeleteItemServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        User user = (User) request.getSession().getAttribute("currentUser");
        if (user != null && user.getRoleId() == 1) {
            int id = Integer.parseInt(request.getParameter("id"));
            DbItem.deleteItem(id);
            response.sendRedirect("/");
        } else {
            response.sendRedirect("/login");
        }
    }

}