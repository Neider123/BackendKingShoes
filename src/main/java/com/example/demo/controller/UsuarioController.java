
package com.example.demo.controller;


import com.example.demo.entities.Usuario;
import com.example.demo.repository.UsuarioRepository;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    	
	@Autowired
	UsuarioRepository usuarioRepository;
	
	@Autowired
    private JavaMailSender emailSender;
	
	@GetMapping
	public List<Usuario> getUsuarioAll() {
		return usuarioRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public Usuario getUsuariosbyId(@PathVariable Integer id) {
		
		Optional<Usuario> usuario = usuarioRepository.findById(id);
		
		if (usuario.isPresent()) {
			return usuario.get();
		}
		
		return null;

	}
	
	@PostMapping("/save")
	public Usuario postUsuarios(@RequestBody Usuario usuario) {
		
		usuarioRepository.save(usuario);
		
		return usuario;

	}
	
	
	@PutMapping("/{id}")
	public Usuario putUsuariosbyId(@PathVariable Integer id, @RequestBody Usuario usuario) {
		
		Optional<Usuario> usuarioCurrent = usuarioRepository.findById(id);
		
		if (usuarioCurrent.isPresent()) {
			
			Usuario usuarioReturn = usuarioCurrent.get();
			
			usuarioReturn.setNombre(usuario.getNombre());
			usuarioReturn.setEmail(usuario.getEmail());
			
			
			
			usuarioRepository.save(usuarioReturn);
			
			return usuarioReturn;
		}
		
		return null;

	}
	
	@DeleteMapping("/{id}")
	public Usuario deleteUsuariosbyId(@PathVariable Integer id) {
		
		Optional<Usuario> usuario = usuarioRepository.findById(id);
		
		if (usuario.isPresent()) {
			
			Usuario usuarioReturn = usuario.get();
			
			usuarioRepository.deleteById(id);
			
			return usuarioReturn;
		}
		
		return null;

	}
	
	//metodo enviar mensaje de confirmacion.
	// se envia el correo buscando el id debe coincidir con el id de la bd.
	//si el correo es invalido el email no es enviado
	//para que el correo sea enviado se debe oprimir dos veces el boton registrar.
	
	@PostMapping("enviarCorreo/{id}")
	public Usuario enviarCorreobyId(@PathVariable Integer id) throws MessagingException {
		
		Optional<Usuario> usuario = usuarioRepository.findById(id);
		if (usuario.isPresent()) {
			
			Usuario usuarioReturn = usuario.get();
			
			MimeMessage mimeMessage = emailSender.createMimeMessage();
			MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
			
			String bodyMessage = (usuario.get().getNombre() + " Felicitaciones su registro ha sido exitoso, sigue viendo nuestros productos en kingshoes");
			SimpleMailMessage message = new SimpleMailMessage();
			message.setTo(usuario.get().getEmail());
			message.setFrom("neidersimarra3@gmail.com");
			message.setSubject("" + usuario.get().getNombre() + " Bienvenido a King shoes" );
			message.setText(bodyMessage);
			
			emailSender.send(message);
			
			
		}
		
		return null;

	}
	
	
	/*
	@PostMapping("/enviarCorreo")
	public Usuario enviarCorreobyId() {
		
		List<Usuario> usuario = usuarioRepository.findByCorreo(email);
		
		if(usuario.isEmpty()) {
			String bodyMessage = "Registro exitoso ID=" + UUID.randomUUID().toString();
			SimpleMailMessage message = new SimpleMailMessage();
			message.setTo(usuario.get(0).getEmail());
			message.setFrom("neidersimarra3@gmail.com");
			message.setSubject("Felicitaciones su registro ha sido exitoso, sigue viendo nuestros productos en kingshoes");
			message.setText(bodyMessage);
			
			emailSender.send(message);
			
			MimeMessage mimeMessage = emailSender.createM
			MimeMessageHelper mimeMessageHelper = new MimwMessageHelper(mimeMessage , multipart true, encoding "UTF-8");
			message.setTo(usuario.get(0).getEmail());
			message.setFrom("neidersimarra3@gmail.com");
			message.setSubject("Felicitaciones su registro ha sido exitoso, sigue viendo nuestros productos en kingshoes");
			message.setText(text);
			
			
		}
		
		return null;
		
	}
	*/
	
	/*
	//Metodo enviar correo de confirmacion;
	@PostMapping("/enviarCorreo")
	public void enviarMensajeConfirmacion(){
		String bodyMessage = "Registro exitoso ID=" + UUID.randomUUID().toString();
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo("neiderjoansesc@ufps.edu.co");
		message.setFrom("neidersimarra3@gmail.com");
		message.setSubject("Felicitaciones su registro ha sido exitoso, sigue viendo nuestros productos en kingshoes");
		message.setText(bodyMessage);
		
		emailSender.send(message);
		
	}
	*/
	
	
}
