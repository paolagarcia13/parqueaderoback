package com.parqueadero.controller;

import java.util.List;

import com.parqueadero.service.PersonaService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.parqueadero.model.Persona;

@RestController
@RequestMapping(path ="personas")
@AllArgsConstructor
public class PersonaController {
	private final PersonaService servicio;

	@GetMapping
	public ResponseEntity<List<Persona>> listar(){
		return new ResponseEntity<>(servicio.listar(), HttpStatus.OK);
	}
	@PostMapping
	public ResponseEntity<Persona> insertar(@RequestBody Persona persona){
		return ResponseEntity.ok().body(servicio.insertar(persona));
	}
	@PutMapping
	public ResponseEntity<Persona> actualizar(@RequestBody Persona persona){
		return ResponseEntity.ok().body(servicio.actualizar(persona));
	}
	@DeleteMapping("{id}")
	public ResponseEntity<Void> eliminar(@PathVariable Integer id){
		servicio.eliminar(id);
		return ResponseEntity.ok().build();
	}
}
