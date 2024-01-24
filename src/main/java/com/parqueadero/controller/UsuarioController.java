package com.parqueadero.controller;

import com.parqueadero.model.Usuario;
import com.parqueadero.service.UsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping(path = "usuarios")
@AllArgsConstructor
public class UsuarioController {
    private final UsuarioService servicio;
    @GetMapping
    public ResponseEntity<List<Usuario>> listar(){
        return new ResponseEntity<>(servicio.listar(), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<Usuario> insertar(@RequestBody Usuario persona){
        return ResponseEntity.ok().body(servicio.insertar(persona));
    }
    @PutMapping
    public ResponseEntity<Usuario> actualizar(@RequestBody Usuario persona){
        return ResponseEntity.ok().body(servicio.actualizar(persona));
    }
    @DeleteMapping("{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id){
        servicio.eliminar(id);
        return ResponseEntity.ok().build();
    }
}
