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
    @NotNull
    private Long id;

    @Column
    @NotNull
    private String name;

    @Column
    @NotNull
    private String description;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn
    private Group group;

//    @OneToMany(targetEntity = Item.class, mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    @JoinColumn
//    List<OrderItem> orderItemList;

//    @OneToMany(targetEntity = Item.class, mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    @JoinColumn
//    List<CartItem> cartItemList;

    public Product(String name, String description) {
        this.name = name;
        this.description = description;
        /*orderItemList = new ArrayList<>();
        cartItemList = new ArrayList<>();*/
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setGroup(Group group) {
        this.group = group;
    }
}


