package br.com.api.controller;

import br.com.api.repository.UsuarioRepository;
import br.com.api.models.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import java.util.Map;


@Controller
@RequestMapping("/usuario")
public class UsuarioController extends GenericController<Usuario, Integer> {
  
    
	private Logger logger = LoggerFactory.getLogger(UsuarioController.class);
	
	@Autowired
    public UsuarioController(UsuarioRepository repo) {
        super(repo);
    }
    
	
	/*
	 * Override for update with encrypted password before to save 
	 * @author HelderSilvaCruz
	 * @version 1.0
	 */
	
	@Override
	public @ResponseBody Map<String, Object> update (@PathVariable Integer id, @RequestBody Usuario json){
    	
		Map<String, Object> m = Maps.newHashMap();
		
    	this.logger.debug("update() of id#{} with body {}", id, json);
    	this.logger.debug("T json is of type {}", json.getClass());
    	
        Usuario entity = super.repo.findById(id).get();
          
        if(!entity.getEmail().equals(json.getEmail())) {
        	if (((UsuarioRepository)this.repo).findByEmail(json.getEmail()).size() < 0){

            	m.put("success", false);
                m.put("message", "Email já existe");
                m.put("created", null);
                return m;

            } 
        }
        
        String encodedPassword;
        
        if(entity.getSenha().equals(json.getSenha())) {
        	encodedPassword = entity.getSenha(); 
        } else {
        	encodedPassword = new BCryptPasswordEncoder().encode(json.getSenha());
        }
                
        this.logger.debug("merged entity: {}", entity);

        entity.setEmail(json.getEmail());
        entity.setSenha(encodedPassword);
        entity.setPerfil(json.getPerfil());
                
        Usuario updated = super.repo.save(entity);
        logger.debug("updated enitity: {}", updated);

        
        m.put("success", true);
        m.put("id", id);
        m.put("updated", updated);
        return m;       
    }
	
	/*
	 * Override for create with encrypted password before to save 
	 * @author HelderSilvaCruz
	 * @version 1.0
	 */
	
	@Override
	public @ResponseBody Map<String, Object> create(@RequestBody Usuario json) {
        this.logger.debug("create() with body {} of type {}", json, json.getClass());
                     
        Map<String, Object> m = Maps.newHashMap();
        
        if (((UsuarioRepository)this.repo).existsByEmail(json.getEmail())){

        	m.put("success", false);
            m.put("message", "Email já existe");
            m.put("created", null);
            return m;

        }
        
        String encodedPassword = new BCryptPasswordEncoder().encode(json.getSenha());
        
        Usuario created = this.repo.save(new Usuario(json.getEmail(), encodedPassword, null));
        
        m.put("success", true);
        m.put("message", "Created with success");
        m.put("created", created);
        return m;
    }
}