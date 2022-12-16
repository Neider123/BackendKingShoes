package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Categoria;
import com.example.demo.entities.Producto;
import com.example.demo.repository.CategoriaRepository;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/categorias")
public class CategoriaController {
	
	@Autowired
	CategoriaRepository categoriaRepository;
	
	@GetMapping
	public List<Categoria> getCategoriaAll() {

		return categoriaRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public Categoria getCategoriasbyId(@PathVariable Integer id) {
		
		Optional<Categoria> categoria = categoriaRepository.findById(id);
		
		if (categoria.isPresent()) {
			return categoria.get();
		}
		
		return null;

	}
	
	@PostMapping
	public Categoria postCategorias(@RequestBody Categoria categoria) {
		
		categoriaRepository.save(categoria);
		
		return categoria;
		

	}
	
	
	@PutMapping("/{id}")
	public Categoria putCategoriasbyId(@PathVariable Integer id, @RequestBody Categoria categoria) {
		
		Optional<Categoria> categoriaCurrent = categoriaRepository.findById(id);
		
		if (categoriaCurrent.isPresent()) {
			
			Categoria categoriaReturn = categoriaCurrent.get();
			
			
			categoriaReturn.setDescripcion(categoria.getDescripcion());
			
			
			
			categoriaRepository.save(categoriaReturn);
			
			return categoriaReturn;
		}
		
		return null;

	}
	
	@DeleteMapping("/{id}")
	public Categoria deleteCategoriasbyId(@PathVariable Integer id) {
		
		Optional<Categoria> categoria = categoriaRepository.findById(id);
		
		if (categoria.isPresent()) {
			
			Categoria categoriaReturn = categoria.get();
			
			categoriaRepository.deleteById(id);
			
			return categoriaReturn;
		}
		
		return null;

	}
	
	
	@GetMapping("/{descripcion}/productos")
	public List<Producto> getProductosbyDescripcion(@PathVariable String descripcion) {
		
		List<Categoria> categoria = categoriaRepository.findByDescripcion(descripcion);
		
		if (!categoria.isEmpty()) {
			return categoria.get(0).getProductos();
		}
		
		return null;

	}
	
	
	
}
