package it.beije.makemake.ecommerce.web;

import it.beije.makemake.database.JPAManager;
import it.beije.makemake.ecommerce.User;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ServletHandleLogin", value = "/servletlogin")
public class ServletHandleLogin extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        HttpSession httpSession = request.getSession();


        JPAManager jpaManager = JPAManager.getJPAManager();
        EntityManager entityManager = jpaManager.getEntityManager();
        Query query = entityManager.createQuery("SELECT u from User as u " +
                "WHERE u.username = :username and u.password = :password");
        query.setParameter("username", username);
        query.setParameter("password", password);
        List<User> userList = query.getResultList();
        if (userList.isEmpty()) {
            httpSession.setAttribute("error", "invalid user data");
            response.sendRedirect("ecommerce/login.jsp");
        } else {
            httpSession.setAttribute("loggedUser" , userList.get(0));
            response.sendRedirect("ecommerce/home.jsp");
        }


    }
}
