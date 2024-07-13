package com.projetoLA.demo.repository;

import com.projetoLA.demo.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AutorRepository extends JpaRepository<Autor, Long> {
        Autor findByNameContainingIgnoreCase(String name);
        @Query("SELECT a FROM Autor a WHERE a.death_year >= :year AND :year >= a.birth_year")
        List<Autor> autoresVivosPorPeriodo(String year);
}
