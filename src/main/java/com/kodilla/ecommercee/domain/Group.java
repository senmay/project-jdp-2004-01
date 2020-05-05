package com.kodilla.ecommercee.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity(name = "groups")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    private Long id;

    private String groupName;
    /*@OneToMany(
            targetEntity = Product.class,
            mappedBy ="group",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL
    )
    private List<Product> products;*/
}
