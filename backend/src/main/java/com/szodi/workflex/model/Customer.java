package com.szodi.workflex.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "customer")
@SequenceGenerator(name = "seq_customer", allocationSize = 5)
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_customer")
    @ToString.Include
    Long id;

    @Column(length = 50)
    String email;

    @Column(length = 50)
    String name;

    @Column
    String city;

    @Column
    String addressLine1;

    @Column
    String addressLine2;

    @Column
    String zipCode;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "customer_movie_relation",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "movie_id")
    )
    Set<Movie> movies;
}
