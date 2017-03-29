package ru.iac.shops.repository.postgress;

import ru.iac.shops.entity.Product;
import ru.iac.shops.entity.Transaction;
import ru.iac.shops.repository.TransactionsDao;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * @author Bulich Artem
 */
public class PgTransactionsDao implements TransactionsDao {

    private EntityManager shopsEntityManager;

    public PgTransactionsDao(){
        shopsEntityManager = Persistence
                .createEntityManagerFactory("shopsEntityManager")
                .createEntityManager();
    }

    public void create(Transaction transaction1){
        shopsEntityManager.getTransaction().begin();
        shopsEntityManager.persist(transaction1);
        shopsEntityManager.getTransaction().commit();
    }

    public Transaction retrieve(long id){
        return shopsEntityManager.find(Transaction.class, id);
    }

    public void update(Transaction transaction){
        shopsEntityManager.getTransaction().begin();
        shopsEntityManager.merge(transaction);
        shopsEntityManager.getTransaction().commit();
    }

    public void delete(long id){
        shopsEntityManager.getTransaction().begin();
        shopsEntityManager.remove(retrieve(id));
        shopsEntityManager.getTransaction().commit();
    }

    public List<Transaction> getAll(){
        TypedQuery<Transaction> namedQuery = shopsEntityManager
                .createNamedQuery("transaction.getAll", Transaction.class);
        return namedQuery.getResultList();
    }
}
