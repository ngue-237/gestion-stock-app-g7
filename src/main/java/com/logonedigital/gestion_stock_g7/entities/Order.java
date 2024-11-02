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
@Table(name = "orders")
public class Order implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderId;
    private String reference;
    private Date createdAt;
    private Date updatedAt;
    private Boolean status;

    //associations
    @ManyToOne
    private Customer customer;
    @OneToOne(mappedBy = "order")
    private Invoice invoice;
    @OneToOne(mappedBy = "order")
    private Delivery delivery;
    @ManyToMany
    private List<Product> products;
}
