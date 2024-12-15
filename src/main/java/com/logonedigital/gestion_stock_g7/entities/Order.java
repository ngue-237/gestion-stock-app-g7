package com.logonedigital.gestion_stock_g7.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "orders")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "orderId")
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
    @OneToMany(mappedBy = "order", fetch = FetchType.EAGER)
    List<OrderItem> orderItems = new ArrayList<>();


}
