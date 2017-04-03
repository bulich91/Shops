package ru.iac.shops.servlets;

import ru.iac.shops.entity.Product;
import ru.iac.shops.entity.Shop;
import ru.iac.shops.repository.GenericDao;
import ru.iac.shops.repository.postgress.PgProductDao;
import ru.iac.shops.repository.postgress.PgShopDao;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Сервлет обрабатывающий запросы связанные с магазином
 * @author Bulich Artem
 */
@WebServlet(
        urlPatterns = "/shop"
)
public class ShopServlet extends HttpServlet {

    private PgShopDao pgShopDao;

    public void init(ServletConfig config) throws ServletException {
        pgShopDao = new PgShopDao();
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, java.io.IOException {
        if(request.getParameter("action")!=null){
            String action = request.getParameter("action");
            if (action.equalsIgnoreCase("delete")){
                long shopId = Long.parseLong(request.getParameter("id"));
                pgShopDao.delete(shopId);
            } else if (action.equalsIgnoreCase("edit")){
                long shopId = Long.parseLong(request.getParameter("id"));
                String name = request.getParameter("name");
                String address= request.getParameter("address");
                pgShopDao.update(new Shop(shopId,name,address));
            } else if (action.equalsIgnoreCase("add")){
                String name = request.getParameter("name");
                String address= request.getParameter("address");
                pgShopDao.create(new Shop()
                        .setName(name)
                        .setAddress(address));
            }
        }
        request.setAttribute("shops", pgShopDao.getAll());
        request.getRequestDispatcher("/shops.jsp").include(request, response);
    }
}
