package it.beije.makemake.ecommerce.web;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.HashMap;

@WebServlet(name = "ServletCart", value = "/cartservlet")
public class ServletCart extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession httpSession = request.getSession();
        HashMap<Integer, Integer> cart = (HashMap<Integer, Integer>)httpSession.getAttribute("cart");
        if (cart == null) {
            cart = new HashMap<>();

        }
        Integer id = Integer.parseInt(request.getParameter("productId"));
        int amount = Integer.parseInt(request.getParameter("amount"));
        cart.put(id, amount);
        httpSession.setAttribute("cart", cart);
        response.sendRedirect("ecommerce/products.jsp");
    }
}
