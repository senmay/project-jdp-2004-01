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
    @NotNull
    @Column(unique = true)
    private long id;

    @Column
    private String name;

    /*
    @OneToOne
    @MapsId
    @JoinColumn
    private User user;
    */

    @OneToMany(
            targetEntity = CartItem.class,
            mappedBy = ("order"),
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    List<CartItem> itemCartList;
}
