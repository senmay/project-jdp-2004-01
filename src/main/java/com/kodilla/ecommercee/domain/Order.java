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
@Entity(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true)
    private long id;

    @NotNull
    @Column
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private User user;

    @OneToMany(
            targetEntity = OrderItem.class,
            mappedBy = "order",
            cascade = CascadeType.PERSIST,
            fetch = FetchType.LAZY)
    List<OrderItem> orderItemList = new ArrayList<>();

    public Order(@NotNull String name) {
        this.name = name;
    }
}
