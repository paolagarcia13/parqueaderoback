package com.parqueadero.service.implement;

import com.parqueadero.model.Usuario;
import com.parqueadero.model.UsuarioRole;
import com.parqueadero.repository.UsuarioRepositorio;
import com.parqueadero.repository.UsuarioRoleRepositorio;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class LoginServiceImpl implements UserDetailsService{

    private final UsuarioRepositorio usuarioRepository;
    private final UsuarioRoleRepositorio usuarioRoleRepositorio;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Usuario usuario = usuarioRepository.findByPersona_Identificacion(username).orElse(null);

        List<GrantedAuthority> lstRole = buscarRolePorUsuario(usuario);

        return new User(username, usuario.getClave(), true, true, true, true, lstRole);
    }

    private List<GrantedAuthority> buscarRolePorUsuario(Usuario usuario) {
        return usuarioRoleRepositorio.findByUsuario(usuario).stream()
                .map(u->u.getRole().getRole()).map(r->new SimpleGrantedAuthority("ROLE_"+r))
                .collect(Collectors.toList());
    }

}
