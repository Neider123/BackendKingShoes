package com.example.demo.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {

    public List<Categoria> findByDescripcion(String descripcion);
}
