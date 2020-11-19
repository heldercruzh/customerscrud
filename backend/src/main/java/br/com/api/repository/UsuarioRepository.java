package br.com.api.repository;

import java.util.List;

/*
 * https://blog.algaworks.com/spring-data-jpa/
 */

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.api.models.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{

	Boolean existsByEmail(String email);
	List<Usuario> findByEmail(String email);
	
}
