package servlet;

import manager.ItemManager;
import model.Item;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/items")
public class ItemServlet extends HttpServlet {

    private ItemManager itemManager = new ItemManager();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Item> items = itemManager.getAll();
        request.setAttribute("item",items);
        request.getRequestDispatcher("/WEB-INF/items.jsp").forward(request,response);
    }

}
