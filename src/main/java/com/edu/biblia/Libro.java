package com.edu.biblia;

import lombok.*;

import javax.persistence.*;
import java.util.List;

//@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
@ToString
@Entity
public class Libro {
    @Id
    Integer id;
    String libro, code;
    Integer capitulos;

    /*@OneToMany(mappedBy="libro", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    List<Versiculo> versiculos;*/
}
