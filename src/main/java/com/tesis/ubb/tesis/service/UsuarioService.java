// package com.tesis.ubb.tesis.service;

// import java.util.List;
// import java.util.stream.Collectors;

// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.core.GrantedAuthority;
// import org.springframework.security.core.authority.SimpleGrantedAuthority;
// import org.springframework.security.core.userdetails.User;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.core.userdetails.UserDetailsService;
// import org.springframework.security.core.userdetails.UsernameNotFoundException;
// import org.springframework.stereotype.Service;
// import org.springframework.transaction.annotation.Transactional;

// import com.tesis.ubb.tesis.models.Usuario;
// import com.tesis.ubb.tesis.repository.UsuarioRepository;

// import org.springframework.security.core.userdetails.UserDetailsService;

// @Service
// public class UsuarioService implements UserDetailsService {

//     private Logger logger = LoggerFactory.getLogger(UsuarioService.class);

//     @Autowired
//     private UsuarioRepository usuarioRepository;

//     @Override
//     @Transactional(readOnly = true)
//     public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//         Usuario usuario = usuarioRepository.findByUsername(username);

//         if(usuario == null){
//             logger.error("Error en el login: No existe el usuario '"+username+"' en el sistema");
//             throw new UsernameNotFoundException("Error en el login: No existe el usuario '"+username+"' en el sistema");
//         }

//         List<GrantedAuthority> authorities = usuario.getRoles()
//                                 .stream()
//                                 .map(rol -> new SimpleGrantedAuthority(rol.getNombre())).collect(Collectors.toList());

//         return new User(usuario.getUsername(),usuario.getPassword(),usuario.getEnabled(),true,true,true,authorities);
//     }

// }