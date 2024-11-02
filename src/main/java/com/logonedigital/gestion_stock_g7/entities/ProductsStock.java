package com.logonedigital.gestion_stock_g7.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "products_stocks")
public class ProductsStock implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer productsStockId;
    private Double quantity;
    @Temporal(TemporalType.TIME)
    private Date createdAt;
    @Temporal(TemporalType.TIME)
    private Date updatedAt;
    private Boolean status;

    //association
    @OneToOne
    private Product product;
}
