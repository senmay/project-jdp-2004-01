package com.kodilla.ecommercee.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.ArrayList;

@NoArgsConstructor
@Getter
@Setter
@Entity(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true)
    private Long id;

    @Column
    @NotNull
    private String name;

    @Column
    @NotNull
    private String description;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn
    private Group group;

//    @OneToMany(targetEntity = Item.class, mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    @JoinColumn
//    List<OrderItem> orderItemList;

//    @OneToMany(targetEntity = Item.class, mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    @JoinColumn
//    List<CartItem> cartItemList;


}


