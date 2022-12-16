package com.example.demo.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;


@Entity
@Data
@Table(name = "usuario")
public class Usuario {

    @Id
    @SequenceGenerator(name = "usuario_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usuario_id_seq")
    private Integer id;
    private String nombre;
    private String email;
    private String username;
    private String password;
    
	@JsonIgnore
	@OneToMany(mappedBy="usuario")
	private List<Tienda> tiendas;
}
