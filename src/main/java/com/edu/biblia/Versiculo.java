package com.edu.biblia;

import lombok.*;

import javax.persistence.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
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

    public String toString(){
        StringBuilder s = new StringBuilder();
        s.append(texto).append("<br />").append(libro.getLibro())
                .append(" ").append(capitulo).append(":").append(versiculo);
        return s.toString();
    }
}
