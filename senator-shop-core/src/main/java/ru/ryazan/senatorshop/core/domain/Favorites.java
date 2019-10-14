package ru.ryazan.senatorshop.core.domain;

import javax.persistence.*;
import java.io.Serializable;
@Entity
public class Favorites implements Serializable {

    private static final long serialVersionUID = -5053710116186699578L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long favoriteId;

    @ManyToOne
    @JoinColumn(name = "customerId")
    private Customer customer;

    @OneToOne
    @JoinColumn(name = "id")
    private Product product;

    public Favorites() {
    }

    public long getFavoriteId() {
        return favoriteId;
    }

    public void setFavoriteId(long favoriteId) {
        this.favoriteId = favoriteId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
