package com.parqueadero.repository;

import com.parqueadero.model.Usuario;
import com.parqueadero.model.UsuarioRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsuarioRoleRepositorio  extends JpaRepository<UsuarioRole,Integer> {
    List<UsuarioRole> findByUsuario(Usuario usuario);
}
