package ru.iac.shops.repository.postgress;

import ru.iac.shops.entity.Product;
import ru.iac.shops.repository.ProductDAO;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * @author Bulich Artem
 */
public class PgProductDao implements ProductDAO {

    private EntityManager shopsEntityManager;

    public PgProductDao(){
        shopsEntityManager = Persistence
                .createEntityManagerFactory("shopsEntityManager")
                .createEntityManager();
    }

    public void create(Product product1){
        shopsEntityManager.getTransaction().begin();
        shopsEntityManager.persist(product1);
        shopsEntityManager.getTransaction().commit();
    }

    public Product retrieve(long id){
        return shopsEntityManager.find(Product.class, id);
    }

    public void update(Product product){
        shopsEntityManager.getTransaction().begin();
        shopsEntityManager.merge(product);
        shopsEntityManager.getTransaction().commit();
    }

    public void delete(long id){
        shopsEntityManager.getTransaction().begin();
        shopsEntityManager.remove(retrieve(id));
        shopsEntityManager.getTransaction().commit();
    }

    public List<Product> getAll(){
        TypedQuery<Product> namedQuery = shopsEntityManager
                .createNamedQuery("product.getAll", Product.class);
        return namedQuery.getResultList();
    }
}
