package com.edu.biblia;

import lombok.*;

import javax.persistence.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
@ToString
@Entity
public class Versiculo {
    @Id
    Integer id;
    Integer capitulo, versiculo;
    String texto;

    @ManyToOne
    @JoinColumn(name ="id_version")
    private Traduccion traduccion;

    @ManyToOne
    @JoinColumn(name ="id_libro")
    private Libro libro;
}
