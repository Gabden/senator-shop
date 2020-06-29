package ru.gabdulindv.senatorshop.model.product;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
public class Product implements Serializable {
    private static final long serialVersionUID = -1530468945225710892L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long productId;

    private String productName;

    private String productCategory;

    @Column(length = 5000)
    private String productDescription;

    private String productPrice;

    private int discount;

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "id")
    private ProductDetails productDetails;


    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<ProductImage> productImageSet;

}
