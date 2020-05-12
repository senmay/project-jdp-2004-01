package com.kodilla.ecommercee.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "USERS")
public class User {

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO )
        @Column (unique = true)
        private Long id;

        @NotNull
        private String name;

        @NotNull
        private boolean isActive;

        @NotNull
        private String apiKey;


        @OneToMany(targetEntity = Order.class,
                mappedBy = "user",
                cascade = CascadeType.ALL,
                fetch = FetchType.LAZY)
        List<Order> orders;


        @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
        @JoinColumn
        Cart cart;
}
