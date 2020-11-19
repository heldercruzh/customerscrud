package br.com.api.controller;



import br.com.api.exceptions.ValidationException;
import br.com.api.models.Usuario;
import br.com.api.repository.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.api.models.JwtRequest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

//import javax.inject.Named;

//@Named
@RequestMapping("/usuariocvc")
@RestController
public class UsuarioControllerold {
	
	@Autowired
    final private UsuarioRepository repository;

	    public UsuarioControllerold(UsuarioRepository repository) {
	        this.repository = repository;
	    }


	    @PostMapping()
	    public Boolean create(@RequestBody Map<String, String> body) throws NoSuchAlgorithmException {
	        String email = body.get("email");
	        if (repository.existsByEmail(email)){

	            throw new ValidationException("Username already existed");

	        }

	        String senha = body.get("senha");
	        String encodedPassword = new BCryptPasswordEncoder().encode(senha);
	        repository.save(new Usuario(email, encodedPassword, null));
	        return true;
	    }
	    
	
	/*private Logger log = LoggerFactory.getLogger(this.getClass());
	private ReturnJson returnJson = new ReturnJson();
	
	
	@Inject
	private UsuarioService service;
		
			
	@PostMapping()
	public @ResponseBody ReturnJson create(@RequestBody Usuario model,
			BindingResult result, SessionStatus status) {		
			
		if (result.hasErrors()) {
			returnJson.setSuccess(false);
			returnJson.setMessage("Errors were identified in data validation.");
			
			System.out.println(returnJson.getMessage());
			
			return returnJson;
			
		} else {
			
			try {
				//returnJson.setSuccess(this.service.isAutenticated(model));
			
				if(!returnJson.isSuccess()) {
					returnJson.setMessage("Email and password invalided");				
				}
			 } catch (Exception e) {
					returnJson.setMessage("Database connection failure, contact your administrator.");
			 }
					
			System.out.println(returnJson.getMessage());
			
			
			
			status.setComplete();
			return returnJson;
		}
		
	}*/
}
	
