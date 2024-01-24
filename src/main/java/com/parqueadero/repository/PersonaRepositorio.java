package com.parqueadero.repository;

import com.parqueadero.model.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonaRepositorio extends JpaRepository<Persona,Integer>{
}
