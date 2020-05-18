package com.kodilla.ecommercee.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "product_groups")
@Getter
@Setter
@NoArgsConstructor
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
    private List<Product> products;

    public Group(Long id, String groupName) {
        this.id = id;
        this.groupName = groupName;
        this.products = new ArrayList<>();
    }

    public Group(Long id, String groupName) {
        this.id = id;
        this.groupName = groupName;
        this.products = new ArrayList<>();
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
}