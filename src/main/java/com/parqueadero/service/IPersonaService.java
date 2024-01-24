package com.parqueadero.service;

import java.util.List;

import com.parqueadero.model.Persona;

public interface IPersonaService {
	
	List<Persona> listar();
	void insertar(Persona persona);
	void actualizar(Persona persona);
	void eliminar(int id);
	List<Persona> listarPorIdentificacion(String identificacion);
	
}
