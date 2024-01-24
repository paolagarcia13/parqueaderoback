package com.parqueadero.repository;

import com.parqueadero.model.Role;
import com.parqueadero.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepositorio  extends JpaRepository<Role,Integer> {
}
