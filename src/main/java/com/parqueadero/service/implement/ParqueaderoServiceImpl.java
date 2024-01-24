package com.parqueadero.service.implement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.parqueadero.model.Parqueadero;
import com.parqueadero.model.ParqueaderoPersona;
import com.parqueadero.repository.ParqueaderoRepository;
import com.parqueadero.repository.PersonaRepository;
import com.parqueadero.service.IParqueaderoService;

@Service
public class ParqueaderoServiceImpl implements IParqueaderoService{

	@Autowired
	private ParqueaderoRepository repo;
	
	@Autowired
	private PersonaRepository repoPersona;
	
	@Override
	public List<Parqueadero> listar() {
		return repo.listar();
	}

	@Override
	public void insertar(Parqueadero parqueadero) {
		
		repo.insertar(parqueadero);
		
	}

	@Override
	public List<Parqueadero> listarPorEstado() {
		
		return repo.listarPorEstado();
	}

	@Override
	public List<ParqueaderoPersona> listarParqueaderoConPersona() {
		
		return repo.listarParqueaderoConPersona();
	}

	@Override
	public int libres() {
		return repo.listarPorEstado().size();
	}

	@Override
	public void desocuparParqueadero(int id) {
		
		System.out.println(id);
		repo.desocupar(id);
		repo.habilitarParqueadero(id);
		
	}

	@Override
	public String asignarParqueadero(ParqueaderoPersona parqueaderoPersona) {
		
		if(repoPersona.listarPorIdentificacion(parqueaderoPersona.getPersona().getIdentificacion()).isEmpty()) {
			repoPersona.insertar(parqueaderoPersona.getPersona());
		}
			parqueaderoPersona.getPersona().setId(repoPersona.listarPorIdentificacion(parqueaderoPersona.getPersona().getIdentificacion()).get(0).getId());
			
			if(repo.validar(parqueaderoPersona.getPersona().getId()).isEmpty()) {
				repo.asignar(parqueaderoPersona);
				repo.deshabilitarParqueadero(parqueaderoPersona.getParqueadero().getId());
				return "Satisfactorio";
			}else {
				return "La persona ya esta ocupando un parqueadero";
			}
		
	}

	@Override
	public void actualizar(Parqueadero parqueadero) {
		repo.modificar(parqueadero);
		
	}

	@Override
	public void eliminar(int id) {
		repo.eliminar(id);
		
	}

}
