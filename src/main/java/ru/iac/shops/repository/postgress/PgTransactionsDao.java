package ru.iac.shops.repository.postgress;

import ru.iac.shops.entity.Shop;
import ru.iac.shops.entity.Transaction;
import ru.iac.shops.repository.AbstractGenericDao;

import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Реализация ДАО для Транзакции
 * @author Bulich Artem
 */
public class PgTransactionsDao extends AbstractGenericDao<Transaction,Long>{
    public List<Transaction> getAll(){
        TypedQuery<Transaction> namedQuery = shopsEntityManager
                .createNamedQuery("transaction.getAll", Transaction.class);
        return namedQuery.getResultList();
    }
}
