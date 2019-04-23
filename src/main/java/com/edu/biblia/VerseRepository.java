package com.edu.biblia;

import org.springframework.data.repository.CrudRepository;

public interface VerseRepository extends CrudRepository<Verse, Integer> {
    public Verse findByIdVerse(Integer idVerse);
}
