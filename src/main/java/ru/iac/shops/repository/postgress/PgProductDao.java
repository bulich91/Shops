package ru.iac.shops.repository.postgress;

import ru.iac.shops.entity.Product;
import ru.iac.shops.repository.AbstractGenericDao;

import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Реализация ДАО для Продукта,
 * не требуются методы, так как они написаны в абстракном родительском классе
 * @author Bulich Artem
 */
public class PgProductDao extends AbstractGenericDao<Product,Long> {

    public List<Product> getAll(){
        TypedQuery<Product> namedQuery = shopsEntityManager
                .createNamedQuery("product.getAll", Product.class);
        return namedQuery.getResultList();
    }
}
