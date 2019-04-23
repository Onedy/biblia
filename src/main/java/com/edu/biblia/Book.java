package com.edu.biblia;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

//@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
@ToString
@Entity
@Table(name = "bible_books")
public class Book {
    @Id
    @Column(name="idBook")
    Integer idBook;
    String name, testament;

    @OneToMany(mappedBy="book", cascade = CascadeType.ALL)
    List<Verse> verses;
}
