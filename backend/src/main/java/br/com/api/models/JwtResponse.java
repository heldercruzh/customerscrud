package br.com.api.models;

import java.io.Serializable;

/*
 * https://medium.com/rapaduratech/criando-um-token-jwt-para-autentica%C3%A7%C3%A3o-em-spring-boot-371ccb54a093
 * 
 * Model para guardar o token jwt que vai ser enviado para o client
 */
public class JwtResponse implements Serializable {

	private static final long serialVersionUID = -8091879091924046844L;
	private final String jwttoken;
	
	public JwtResponse(String jwttoken) {
		this.jwttoken = jwttoken;
	}
	
	public String getToken() {
		return this.jwttoken;
	}

}
