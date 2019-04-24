package com.edu.biblia;

import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.List;

public interface VersiculoRepository extends CrudRepository<Versiculo, Integer> {
    List<Versiculo> findByLibroLibro(String libro);
    List<Versiculo> findByLibroLibroAndCapituloAndVersiculoIn(String libro, Integer capitulo, Collection<Integer> versiculo);
}
