package ru.iac.shops.servlets;

import ru.iac.shops.entity.Product;
import ru.iac.shops.repository.GenericDao;
import ru.iac.shops.repository.postgress.PgProductDao;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Сервлет обрабатывающий запросы связанные с Продуктом
 * @author Bulich Artem
 */

@WebServlet(
        urlPatterns = "/product"
)
public class ProductServlet extends HttpServlet {

    private PgProductDao pgProductDao;

    public void init(ServletConfig config) throws ServletException {
        pgProductDao= new PgProductDao();
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, java.io.IOException {
        if(request.getParameter("action")!=null){
            String action = request.getParameter("action");
            if (action.equalsIgnoreCase("delete")){
                long productId = Long.parseLong(request.getParameter("id"));
                pgProductDao.delete(productId);
            } else if (action.equalsIgnoreCase("edit")){
                long productId = Long.parseLong(request.getParameter("id"));
                String name = request.getParameter("name");
                pgProductDao.update(new Product(productId,name));
            } else if (action.equalsIgnoreCase("add")){
                String name = request.getParameter("name");
                pgProductDao.create(new Product()
                        .setName(name));
            }
        }
        request.setAttribute("products", pgProductDao.getAll());
        request.getRequestDispatcher("/products.jsp").include(request, response);
    }
}
