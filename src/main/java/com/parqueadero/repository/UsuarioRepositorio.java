package com.parqueadero.repository;

import com.parqueadero.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepositorio extends JpaRepository<Usuario,Integer>{
    Optional<Usuario> findByPersona_Identificacion(String identificacion);
}
