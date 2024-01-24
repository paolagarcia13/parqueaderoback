package com.parqueadero.service.implement;

import com.parqueadero.model.Persona;
import com.parqueadero.repository.PersonaRepositorio;
import com.parqueadero.service.PersonaService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PersonaServiceImpl implements PersonaService {
    private final PersonaRepositorio repo;

    @Override
    public Persona insertar(Persona persona) {
        return repo.save(persona);
    }

    @Override
    public List<Persona> listar() {
        return repo.findAll();
    }

    @Override
    public Persona actualizar(Persona persona) {
        return repo.save(persona);
    }

    @Override
    public void eliminar(Integer id) {
        repo.deleteById(id);
    }
}
