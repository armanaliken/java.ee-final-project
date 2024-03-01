package servlet;

import db.DbManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.User;


import java.io.IOException;

@WebServlet("/update-profile")
public class UpdateProfileServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("updateprofile.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("currentUser");
        if (user != null) {
            String newPassword = request.getParameter("newPassword");
            String newFullName = request.getParameter("newFullName");

            // Update user's password and full name
            user.setPassword(newPassword);
            user.setFullName(newFullName);

            DbManager.updateUser(user); // Assuming there's a method to update user data in the database

            response.sendRedirect("/profile"); // Redirect to profile page after update
        } else {
            response.sendRedirect("/login"); // Redirect to login page if user is not logged in
        }
    }
}
