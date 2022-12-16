/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.controller;



import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Tienda;
import com.example.demo.repository.TiendaRepository;

@RestController
@RequestMapping("/tiendas")
public class TiendaController {
      	
	@Autowired
	TiendaRepository tiendaRepository;
	
	@GetMapping
	public List<Tienda> getUsuarioAll() {

		return tiendaRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public Tienda getUsuariosbyId(@PathVariable Integer id) {
		
		Optional<Tienda> tienda = tiendaRepository.findById(id);
		
		if (tienda.isPresent()) {
			return tienda.get();
		}
		
		return null;

	}
	
	@PostMapping
	public Tienda postUsuarios(@RequestBody Tienda tienda) {
		
		tiendaRepository.save(tienda);
		
		return tienda;
		

	}
	
	
	@PutMapping("/{id}")
	public Tienda putUsuariosbyId(@PathVariable Integer id, @RequestBody Tienda tienda) {
		
		Optional<Tienda> tiendaCurrent = tiendaRepository.findById(id);
		
		if (tiendaCurrent.isPresent()) {
			
			Tienda tiendaReturn = tiendaCurrent.get();
			
			tiendaReturn.setNombre(tienda.getNombre());
			
			
			
			
			tiendaRepository.save(tiendaReturn);
			
			return tiendaReturn;
		}
		
		return null;

	}
	
	@DeleteMapping("/{id}")
	public Tienda deleteUsuariosbyId(@PathVariable Integer id) {
		
		Optional<Tienda> tienda = tiendaRepository.findById(id);
		
		if (tienda.isPresent()) {
			
			Tienda tiendaReturn = tienda.get();
			
			tiendaRepository.deleteById(id);
			
			return tiendaReturn;
		}
		
		return null;

	}  
}
