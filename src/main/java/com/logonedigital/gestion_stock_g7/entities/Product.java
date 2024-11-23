package com.logonedigital.gestion_stock_g7.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "products")
public class Product implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer productId;
    private String name;
    @Column(columnDefinition = "text")
    private String description;
    private String slug;
    private Double price;
    @Temporal(TemporalType.TIME)
    private Date createdAt;
    @Temporal(TemporalType.TIME)
    private Date updatedAt;
    private Boolean status;

    //associations
    @ManyToMany(mappedBy = "products")
    private List<Order> orders;
    @ManyToOne
    private CategoryProducts categoryProducts;
    @OneToOne(mappedBy = "product", cascade = CascadeType.ALL)
    private ProductsStock productsStock;
}
