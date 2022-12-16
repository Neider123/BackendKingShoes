package com.example.demo.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.Producto;



public interface ProductoRepository extends JpaRepository<Producto, Integer> {

}
