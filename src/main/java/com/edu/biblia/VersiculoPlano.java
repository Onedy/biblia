package com.edu.biblia;

import lombok.*;

import java.util.List;

@Getter
@AllArgsConstructor
@ToString
public class VersiculoPlano {
    final String libro;
    final int capitulo;
    final List<Integer> versiculos;
}
