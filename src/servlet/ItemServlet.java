package servlet;


import db.DbItem;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Item;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(value = "/item")
public class ItemServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        ArrayList<Item> items = DbItem.getItem();
        request.setAttribute("item", items);
        request.getRequestDispatcher("/item.jsp").forward(request, response);
    }

}