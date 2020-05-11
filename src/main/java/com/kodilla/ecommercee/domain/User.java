package com.kodilla.ecommercee.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO )
        private Long id;
        private String name;
        private boolean isActive;
        private String apiKey;
//        private Cart cart;
//        private List<Order> orders;
//
//        @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//        public List<Order> getOrders(){
//                return orders;
//        }
//
//        @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//        public Cart getCart(){
//                return cart;
//        }
}
