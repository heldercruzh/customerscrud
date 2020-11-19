package br.com.api.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import br.com.api.models.Usuario;
import br.com.api.repository.UsuarioRepository;

/*
 * https://medium.com/rapaduratech/criando-um-token-jwt-para-autentica%C3%A7%C3%A3o-em-spring-boot-371ccb54a093
 * 
 * JwtUserDetailsService que implementa a UserDetailsService do spring security 
 * e checar o login do usuario
 * 
 * username = "springuser", 
 * password = passwort -> "$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6"
 * 
 */

@Service
public class JwtUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UsuarioRepository repository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Usuario usuario = this.repository.findByEmail(username).get(0);
				
		if (usuario != null) {
			return new User(usuario.getEmail(), usuario.getSenha(),
					new ArrayList<>());
		} else {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
	}
}
