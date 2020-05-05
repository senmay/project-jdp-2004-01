package com.kodilla.ecommercee.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "carts")
public class Cart {

    @Id
    @Column(unique = true)
    private long id;

    @NotNull
    @Column
    private String name;

    /*
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @MapsId
    @JoinColumn
    private User user;
    */

    @OneToMany(
            targetEntity = CartItem.class,
            mappedBy = ("cart"),
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    List<CartItem> cartItemList;
}
