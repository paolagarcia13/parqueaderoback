package com.parqueadero.repository;

import com.parqueadero.model.Parqueadero;
import org.springframework.data.jpa.repository.JpaRepository;
public interface ParqueaderoRepositorio extends JpaRepository<Parqueadero,Integer>{
}
