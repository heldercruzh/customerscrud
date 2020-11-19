package br.com.api.repository;

/*
 * https://blog.algaworks.com/spring-data-jpa/
 */

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.api.models.Pessoa;
import br.com.api.models.Usuario;

public interface PessoaRepository extends JpaRepository<Pessoa, Long>{

	Pessoa findByUsuario(Usuario usuario);
	
}
