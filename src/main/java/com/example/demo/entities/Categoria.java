package com.example.demo.entities;

import com.example.demo.entities.Producto;
import java.util.List;



import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;

import lombok.Data;

@Entity
@Data
public class Categoria {
	@Id
	@SequenceGenerator(name="categoria_id_seq", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="categoria_id_seq")
	private Integer id;
	
	private String descripcion;
	

	@JsonIgnore
	@OneToMany(mappedBy="categoria")
	private List<Producto> productos;
	
}
