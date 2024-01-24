package com.parqueadero.service;

import java.util.List;

public interface Generico <T,D>{
    T insertar(T t);
    List<T> listar();
    T actualizar(T t);
    void eliminar(D id);

}
