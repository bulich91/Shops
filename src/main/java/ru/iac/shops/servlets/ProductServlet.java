package ru.iac.shops.servlets;

import ru.iac.shops.entity.Product;
import ru.iac.shops.repository.postgress.PgProductDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Bulich Artem
 */

@WebServlet(
        urlPatterns = "/product"
)
public class ProductServlet extends HttpServlet {

    private PgProductDao pgProductDao = new PgProductDao();

    @Override
    public void doGet (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, java.io.IOException {

        request.setAttribute("products", pgProductDao.getAll());
        request.getRequestDispatcher("/products.jsp").include(request, response);
        try {
            if (request.getServletPath().equals("/delete")) {
                doDelete(request, response);
            }
        }
        catch (IOException e){
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    public void doDelete (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, java.io.IOException{

        long id = Long.parseLong(request.getParameter("id"));
        pgProductDao.delete(id);
        request.getRequestDispatcher("/products.jsp").include(request, response);
    }
}
