package ru.iac.shops.servlets;

import ru.iac.shops.repository.postgress.PgShopDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Bulich Artem
 */

@WebServlet(
        urlPatterns = "/shop"
)
public class ShopServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, java.io.IOException {

        PgShopDao pgShopDao = new PgShopDao();
        request.setAttribute("shops", pgShopDao.getAll());
        request.getRequestDispatcher("/shops.jsp").include(request, response);
    }
}
