package com.edu.biblia;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
@ToString
@Entity
@Table(name = "bible_bibles")
public class Version {
    @Id
    @Column(name="idBible")
    Integer idBible;
    String name, abreviatioin, comment, fuente;
    Integer apocrifa, fuertes;

    @OneToMany(mappedBy="version", cascade = CascadeType.ALL)
    List<Verse> verses;
}
