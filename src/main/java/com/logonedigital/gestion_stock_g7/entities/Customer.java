package com.logonedigital.gestion_stock_g7.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

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
@Table(name = "customers")
public class Customer implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer customerId;
    @Column(nullable = false)
    @Length(min = 3, max = 50, message = "firstname must have min 3 caracters and max 50 caracters")
    @NotEmpty(message = "This field must be fill")
    private String firstname;
    private String lastname;

    @Email(message = "Your email address isn't correct")
    private String email;
    @Column(nullable = false, name = "phone_number")
    private String phone;
    @Temporal(TemporalType.TIME)
    private Date createdAt;
    @Temporal(TemporalType.TIME)
    private Date updatedAt;
    private Boolean status;

    //association
    @OneToMany(mappedBy = "customer")
    List<Order> orders = new ArrayList<>();
    @OneToOne( orphanRemoval = true)
    private Location location;
}
