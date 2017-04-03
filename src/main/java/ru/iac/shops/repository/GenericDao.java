package ru.iac.shops.repository;

import ru.iac.shops.entity.Product;

import javax.persistence.TypedQuery;
import java.io.Serializable;
import java.util.List;

/**
 * Интерфейс ДАО
 * @author Bulich Artem
 */
public interface GenericDao<T, PK extends Serializable> {
    void create(T t);
    T retrieve(PK id);
    void update(T t);
    void delete(PK id);
}
