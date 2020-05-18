package com.kodilla.ecommercee.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "USERS")
public class User {

    @Id
    @GeneratedValue
    @Column
    private Long id;

    @NotNull
    @Column
    private String name;

    @NotNull
    @Column
    private boolean isActive;

    @NotNull
    @Column
    private String apiKey;


    @OneToMany(targetEntity = Order.class,
            mappedBy = "user",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private List<Order> orders = new ArrayList<>();


    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn
    Cart cart;

    public User(Long id, String name, boolean isActive, String apiKey) {
        this.name = name;
        this.isActive = isActive;
        this.apiKey = apiKey;
    }

}
