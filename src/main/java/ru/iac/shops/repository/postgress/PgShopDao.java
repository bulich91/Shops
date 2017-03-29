package ru.iac.shops.repository.postgress;

import ru.iac.shops.entity.Product;
import ru.iac.shops.entity.Shop;
import ru.iac.shops.repository.ShopDAO;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * @author Bulich Artem
 */
public class PgShopDao implements ShopDAO{private EntityManager shopsEntityManager;

    public PgShopDao(){
        shopsEntityManager = Persistence
                .createEntityManagerFactory("shopsEntityManager")
                .createEntityManager();
    }

    public void create(Shop shop1){
        shopsEntityManager.getTransaction().begin();
        shopsEntityManager.persist(shop1);
        shopsEntityManager.getTransaction().commit();
    }

    public Shop retrieve(long id){
        return shopsEntityManager.find(Shop.class, id);
    }

    public void update(Shop shop){
        shopsEntityManager.getTransaction().begin();
        shopsEntityManager.merge(shop);
        shopsEntityManager.getTransaction().commit();
    }

    public void delete(long id){
        shopsEntityManager.getTransaction().begin();
        shopsEntityManager.remove(retrieve(id));
        shopsEntityManager.getTransaction().commit();
    }

    public List<Shop> getAll(){
        TypedQuery<Shop> namedQuery = shopsEntityManager
                .createNamedQuery("shop.getAll", Shop.class);
        return namedQuery.getResultList();
    }
}
