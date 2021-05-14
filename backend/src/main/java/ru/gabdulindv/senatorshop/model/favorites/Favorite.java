package ru.gabdulindv.senatorshop.model.favorites;


import ru.gabdulindv.senatorshop.model.User;
import ru.gabdulindv.senatorshop.model.product.Product;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "favorite")
public class Favorite implements Serializable {

    private static final long serialVersionUID = -5053710116186699578L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long favoriteId;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "id")
    private User user;

    @OneToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "product_id")
    private Product product;

    public Favorite() {
    }

    public long getFavoriteId() {
        return favoriteId;
    }

    public void setFavoriteId(long favoriteId) {
        this.favoriteId = favoriteId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}