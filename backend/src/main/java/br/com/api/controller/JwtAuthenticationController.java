package br.com.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import br.com.api.config.JwtTokenUtil;
import br.com.api.models.JwtRequest;
import br.com.api.models.Usuario;
import br.com.api.repository.UsuarioRepository;
import br.com.api.service.JwtUserDetailsService;

/*
 * https://medium.com/rapaduratech/criando-um-token-jwt-para-autentica%C3%A7%C3%A3o-em-spring-boot-371ccb54a093
 * 
 * Gerencia a autenticação da nossa aplicação. Usando o Spring Authentication 
 * Manager, autenticar o nome de usuário e a senha. Se as credenciais forem 
 * válidas, um token JWT será criado usando o JWTTokenUtil e será fornecido ao 
 * cliente
 * 
 * https://dev.to/cuongld2/create-apis-with-jwt-authorization-using-spring-boot-24f9
 * 
 * Para definir a API para autenticar as credenciais do usuário e o token jwt de 
 * resposta, se correto
 */

@RestController
@CrossOrigin
public class JwtAuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@Autowired
	private UsuarioRepository repo;
	
	@Autowired
	private JwtUserDetailsService userDetailsService;
	
	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
		authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
		
		System.out.println("teste1");
		
		final UserDetails userDetails = userDetailsService
				.loadUserByUsername(authenticationRequest.getUsername());
		final String token = jwtTokenUtil.generateToken(userDetails);
		
		Usuario currentuser = repo.findByEmail(authenticationRequest.getUsername()).get(0);
		currentuser.setSenha(null);
		currentuser.setToken(token);
		
		return ResponseEntity.ok(currentuser);
	}
	
	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
	
	
	
}
