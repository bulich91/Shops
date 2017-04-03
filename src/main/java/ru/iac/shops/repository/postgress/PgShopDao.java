package ru.iac.shops.repository.postgress;

import ru.iac.shops.entity.Product;
import ru.iac.shops.entity.Shop;
import ru.iac.shops.repository.AbstractGenericDao;

import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Реализация ДАО для Магазина, не требуется определение методов,
 * так как они уже реализованы в абстрактном ДАО
 * @author Bulich Artem
 */
public class PgShopDao extends AbstractGenericDao<Shop,Long> {
    public List<Shop> getAll(){
        TypedQuery<Shop> namedQuery = shopsEntityManager
                .createNamedQuery("shop.getAll", Shop.class);
        return namedQuery.getResultList();
    }
}
