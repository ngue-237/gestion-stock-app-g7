package com.logonedigital.gestion_stock_g7.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "categories_product")
public class CategoryProducts implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer categoryProductsId;
    private String name;
    @Column(columnDefinition = "text")
    private String description;
    private String slug;
    @Temporal(TemporalType.TIME)
    private Date createdAt;
    @Temporal(TemporalType.TIME)
    private Date updatedAt;
    private Boolean status;

    //association
    @OneToMany(mappedBy = "categoryProducts")
    private List<Product> products = new ArrayList<>();
}
