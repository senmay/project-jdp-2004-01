package com.kodilla.ecommercee.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "product_groups")
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Long id;

    @Column
    private String groupName;
    @OneToMany(
            targetEntity = Product.class,
            mappedBy ="group",
            fetch = FetchType.EAGER,
            cascade = CascadeType.PERSIST
    )
    private List<Product> products;

    public Group(String groupName) {
        this.groupName = groupName;
    }
}
