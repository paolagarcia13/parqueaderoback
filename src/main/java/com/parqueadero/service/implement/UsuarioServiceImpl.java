package com.parqueadero.service.implement;

import com.parqueadero.model.Role;
import com.parqueadero.model.Usuario;
import com.parqueadero.model.UsuarioRole;
import com.parqueadero.repository.UsuarioRepositorio;
import com.parqueadero.repository.UsuarioRoleRepositorio;
import com.parqueadero.service.UsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {
    private final UsuarioRepositorio repo;
    private final BCryptPasswordEncoder encoder;
    private final UsuarioRoleRepositorio usuarioRoleRepositorio;
    @Override
    public Usuario insertar(Usuario usuario) {
        usuario.setClave(encoder.encode(usuario.getClave()));
        Usuario usuarioRta= repo.save(usuario);
        var usuarioRole= new UsuarioRole();
        usuarioRole.setUsuario(usuario);
        usuarioRole.setRole(new Role(1,"usuario"));
        usuarioRoleRepositorio.save(usuarioRole);
        usuario.setClave("");
        return usuario;
    }


    @Override
    public List<Usuario> listar() {
        return repo.findAll();
    }

    @Override
    public Usuario actualizar(Usuario usuario) {
        usuario.setClave(encoder.encode(usuario.getClave()));
        return repo.save(usuario);
    }

    @Override
    public void eliminar(Integer id) {
        repo.deleteById(id);
    }
}
