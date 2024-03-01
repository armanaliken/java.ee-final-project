package servlet;

import db.DbManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.User;

import java.io.IOException;

@WebServlet(value = "/signup")
public class SignupServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/signup.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String rePassword = req.getParameter("re_password");
        String fullName = req.getParameter("full_name");

        User user = DbManager.getUser(email);

        if(user==null){
            if(password.equals(rePassword)){

                User createUser = new User();
                createUser.setEmail(email);
                createUser.setPassword(password);
                createUser.setFullName(fullName);
                createUser.setRoleId(2);

                DbManager.addUser(createUser);
                resp.sendRedirect("/signup?success");

            }else{
                resp.sendRedirect("/signup?passworderror");
            }

        }else{
            resp.sendRedirect("/signup?emailerror");
        }
    }

}