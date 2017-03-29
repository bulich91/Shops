package ru.iac.shops.servlets;

import ru.iac.shops.repository.postgress.PgTransactionsDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Bulich Artem
 */
@WebServlet(
        urlPatterns = "/transaction"
)
public class TransactionServlet  extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, java.io.IOException {

        PgTransactionsDao pgTransactionsDao = new PgTransactionsDao();
        request.setAttribute("transactions", pgTransactionsDao.getAll());
        request.getRequestDispatcher("/transactions.jsp").include(request, response);
    }
}
