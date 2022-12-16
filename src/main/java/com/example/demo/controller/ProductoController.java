package com.example.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Producto;
import com.example.demo.repository.ProductoRepository;

import java.util.List;
import java.util.Optional;

//@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/productos")
public class ProductoController {
	
	@Autowired
	ProductoRepository productoRepository;
	
	@GetMapping
	public List<Producto> getProductoAll() {

		return productoRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public Producto getProductosbyId(@PathVariable Integer id) {
		
		Optional<Producto> producto = productoRepository.findById(id);
		
		if (producto.isPresent()) {
			return producto.get();
		}
		
		return null;

	}
	
	@PostMapping
	public Producto postProductos(@RequestBody Producto producto) {
		
		productoRepository.save(producto);
		
		return producto;
		

	}
	
	
	@PutMapping("/{id}")
	public Producto putProductosbyId(@PathVariable Integer id, @RequestBody Producto producto) {
		 
		Optional<Producto> productoCurrent = productoRepository.findById(id);
		
		if (productoCurrent.isPresent()) {
			
			Producto productoReturn = productoCurrent.get();
			
		
			productoReturn.setNombre(producto.getNombre());
			productoReturn.setColor(producto.getColor());
			productoReturn.setTalla(producto.getTalla());   
			productoReturn.setPrecio(producto.getPrecio());
			
			
			productoRepository.save(productoReturn);
			
			return productoReturn;
		}
		
		return null;

	}
	
	@DeleteMapping("/{id}")
	public Producto deleteProductosbyId(@PathVariable Integer id) {
		
		Optional<Producto> producto = productoRepository.findById(id);
		
		if (producto.isPresent()) {
			
			Producto productoReturn = producto.get();
			
			productoRepository.deleteById(id);
			
			return productoReturn;
		}
		
		return null;

	}
	
	
	
}
