package com.edu.biblia;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface VersiculoRepository extends CrudRepository<Versiculo, Integer> {
    List<Versiculo> findByLibroLibro(String libro);
    Versiculo findByLibroLibroAndCapituloAndVersiculo(String libro, Integer capitulo, Integer versiculo);
}
