package com.kodilla.ecommercee.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "carts")
public class Cart {
    @Id
    @GeneratedValue
    @Column
    private long id;

    @NotNull
    @Column
    private String name;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
   // @JoinColumn
    private User user;

    @OneToMany(
            targetEntity = CartItem.class,
            mappedBy = "cart",
            cascade = CascadeType.PERSIST,
            fetch = FetchType.EAGER
    )
    List<CartItem> cartItemList=new ArrayList<>();

    public Cart(@NotNull String name) {
        this.name = name;
    }
}
