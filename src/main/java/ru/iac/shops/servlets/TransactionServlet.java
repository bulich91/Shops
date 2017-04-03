package ru.iac.shops.servlets;

import ru.iac.shops.entity.Product;
import ru.iac.shops.entity.Shop;
import ru.iac.shops.entity.Transaction;
import ru.iac.shops.repository.postgress.PgProductDao;
import ru.iac.shops.repository.postgress.PgShopDao;
import ru.iac.shops.repository.postgress.PgTransactionsDao;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.sql.Time;

/**
 * Сервлет, обрабатывающий запросы связанные с Транзакцией
 * @author Bulich Artem
 */
@WebServlet(
        urlPatterns = "/transaction"
)
public class TransactionServlet  extends HttpServlet {

    private PgProductDao pgProductDao;
    private PgShopDao pgShopDao;
    private PgTransactionsDao pgTransactionsDao;

    public void init(ServletConfig config) throws ServletException {
        pgProductDao= new PgProductDao();
        pgShopDao = new PgShopDao();
        pgTransactionsDao = new PgTransactionsDao();
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, java.io.IOException {
        if(request.getParameter("action")!=null){
            String action = request.getParameter("action");
            if (action.equalsIgnoreCase("delete")){
                long id = Long.parseLong(request.getParameter("id"));
                pgTransactionsDao.delete(id);
            } else if (action.equalsIgnoreCase("edit")){
                long id = Long.parseLong(request.getParameter("id"));
                long shopId = Long.parseLong(request.getParameter("shop"));
                long productId = Long.parseLong(request.getParameter("product"));
                long count = Long.parseLong(request.getParameter("count"));
                double check = Double.parseDouble(request.getParameter("check"));
                Time time = Time.valueOf(request.getParameter("time"));
                Date date = Date.valueOf(request.getParameter("date"));
                Shop shop = pgShopDao.retrieve(shopId);
                Product product = pgProductDao.retrieve(productId);
                pgTransactionsDao
                        .update(new Transaction(id,shop,product,count,check,date,time));
            } else if (action.equalsIgnoreCase("add")){
                long shopId = Long.parseLong(request.getParameter("shop"));
                long productId = Long.parseLong(request.getParameter("product"));
                long count = Long.parseLong(request.getParameter("count"));
                double check = Double.parseDouble(request.getParameter("check"));
                Time time = Time.valueOf(request.getParameter("time"));
                Date date = Date.valueOf(request.getParameter("date"));
                Shop shop = pgShopDao.retrieve(shopId);
                Product product = pgProductDao.retrieve(productId);
                pgTransactionsDao.create(new Transaction()
                        .setShop(shop)
                        .setProduct(product)
                        .setProductCount(count)
                        .setCheck(check)
                        .setDate(date)
                        .setTime(time));
            }
        }
        request.setAttribute("transactions",pgTransactionsDao.getAll());
        request.getRequestDispatcher("/transactions.jsp").include(request, response);
    }

}
