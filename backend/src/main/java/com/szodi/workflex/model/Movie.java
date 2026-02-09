package com.szodi.workflex.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "movie")
@SequenceGenerator(name = "seq_movie", allocationSize = 5)
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_movie")
    @ToString.Include
    Long id;

    @Column(length = 50)
    String title;

    @Column(length = 50)
    String genre;

    @Column
    String imdbRating;
}
