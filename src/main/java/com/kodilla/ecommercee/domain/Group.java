package com.kodilla.ecommercee.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "product_groups")
@Getter
@NoArgsConstructor
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true)
    @NotNull
    private Long id;

    @NotNull
    @Column
    private String groupName;


    @OneToMany(
            targetEntity = Product.class,
            mappedBy ="group",
            fetch = FetchType.EAGER,
            cascade = CascadeType.PERSIST
    )
    private List<Product> products = new ArrayList<>();

    public Group(String groupName) {
        this.groupName = groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
}