package com.parqueadero.controller;

import java.util.List;

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

import com.parqueadero.model.Parqueadero;
import com.parqueadero.model.ParqueaderoPersona;
import com.parqueadero.service.IParqueaderoService;

@RestController
@RequestMapping(path="parqueadero")
public class ParqueaderoController {
	
	@Autowired
	private IParqueaderoService service;

	@GetMapping("/listar")
	public List<Parqueadero> listar(){
		return service.listar();
	}
	
	@PostMapping(path="/insertar")
	@CrossOrigin(origins = "http://localhost:4200")
	public void insertar(@RequestBody Parqueadero parqueadero) {
		service.insertar(parqueadero);
	}
	
	@GetMapping(path="/listar-por-estado")
	public List<Parqueadero> listarPorEstado(){
		return service.listarPorEstado();
	}
	
	@GetMapping(path="/listar-parqueadero-persona")
	public List<ParqueaderoPersona> listarParqueaderoConPersona(){
		return service.listarParqueaderoConPersona();
	}
	
	@PutMapping(path="/desocupar")
	@CrossOrigin(origins = "http://localhost:4200")
	public void desocuparParqueadero(@RequestBody int id) {
		service.desocuparParqueadero(id);
	}
	
	@PostMapping(path="/asignar")
	@CrossOrigin(origins = "http://localhost:4200")
	public String asignarParqueadero(@RequestBody ParqueaderoPersona parqueaderoPersona) {
		return service.asignarParqueadero(parqueaderoPersona);
	}
	
	@PutMapping(path="modificar")
	@CrossOrigin(origins = "http://localhost:4200")
	public void modificar(@RequestBody Parqueadero parqueadero) {
		service.actualizar(parqueadero);
	}
	
	@DeleteMapping(path="eliminar/{id}")
	@CrossOrigin(origins = "http://localhost:4200")
	public void eliminar(@PathVariable int id) {
		service.eliminar(id);
	}
}
