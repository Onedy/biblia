package com.edu.biblia;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
@ToString
@Entity
public class Traduccion {
    @Id
    Integer id;
    String version, code;

    /*@OneToMany(mappedBy="traduccion", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    List<Versiculo> versiculos;*/
}
