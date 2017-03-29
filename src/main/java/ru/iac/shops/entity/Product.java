package ru.iac.shops.entity;

import javax.persistence.*;

/**
 * @author Bulich Artem
 */

@Entity
@Table(name = "product")
@NamedQuery(name = "product.getAll", query = "SELECT p from Product p")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "name", nullable = false)
    private String name;

    public Product() {    }

    public Product(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public Product setId(long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Product setName(String name) {
        this.name = name;
        return this;
    }
}
