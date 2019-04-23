package com.edu.biblia;

import lombok.*;

import javax.persistence.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
@ToString
@Entity(name = "bible_verses")
@Table(name = "bible_verses")
public class Verse {
    @Id
    @Column(name="idVerse")
    Integer idVerse;
    Integer chapter, verse;
    String text;

    @ManyToOne
    @JoinColumn(name ="idBible")
    private Version version;

    @ManyToOne
    @JoinColumn(name ="idVersion")
    private Book book;
}
