package com.parqueadero.service;

import java.util.List;

import com.parqueadero.model.Parqueadero;
import com.parqueadero.model.ParqueaderoPersona;

public interface IParqueaderoService {

	List<Parqueadero> listar();
	void insertar(Parqueadero parqueadero);
	List<Parqueadero> listarPorEstado();
	List<ParqueaderoPersona> listarParqueaderoConPersona();
	int libres();
	void desocuparParqueadero(int id);
	String asignarParqueadero(ParqueaderoPersona parqueaderoPersona);
	void actualizar(Parqueadero parqueadero);
	void eliminar(int id);
	
}
