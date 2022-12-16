
package com.example.demo.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import lombok.Data;

@Data
@Entity
public class Tienda {
    
    
    @Id
    @SequenceGenerator(name = "tienda_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tienda_id_seq")
    private int id;
    @Column(name="nombre")
    private String nombre;
    
    @ManyToOne
	@JoinColumn(name="id_usuario")
	private Usuario usuario;
    
    
}
