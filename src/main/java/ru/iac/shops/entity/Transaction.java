package ru.iac.shops.entity;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;

/**
 * @author Bulich Artem
 */
@Entity
@Table(name = "transaction")
@NamedQuery(name = "transaction.getAll", query = "SELECT t from Transaction t")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @OneToOne
    @JoinColumn(name = "id_shop")
    private Shop shop;
    @OneToOne
    @JoinColumn(name = "id_product")
    private Product product;
    @Column(name = "product_count", nullable = false)
    private long productCount;
    @Column(name = "check", nullable = false)
    private double check;
    @Column(name = "date", nullable = false)
    private Date date;
    @Column(name = "time", nullable = false)
    private Time time;

    public Transaction() {
    }

    public Transaction(long id, Shop shop, Product product, long productCount, double check, Date date, Time time) {
        this.id = id;
        this.shop = shop;
        this.product = product;
        this.productCount = productCount;
        this.check = check;
        this.date = date;
        this.time = time;
    }

    public long getId() {
        return id;
    }

    public Transaction setId(long id) {
        this.id = id;
        return this;
    }

    public Shop getShop() {
        return shop;
    }

    public Transaction setShop(Shop shop) {
        this.shop = shop;
        return this;
    }

    public Product getProduct() {
        return product;
    }

    public Transaction setProduct(Product product) {
        this.product = product;
        return this;
    }

    public long getProductCount() {
        return productCount;
    }

    public Transaction setProductCount(long productCount) {
        this.productCount = productCount;
        return this;
    }

    public double getCheck() {
        return check;
    }

    public Transaction setCheck(double check) {
        this.check = check;
        return this;
    }

    public Date getDate() {
        return date;
    }

    public Transaction setDate(Date date) {
        this.date = date;
        return this;
    }

    public Time getTime() {
        return time;
    }

    public Transaction setTime(Time time) {
        this.time = time;
        return this;
    }
}
