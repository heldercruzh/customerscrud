package br.com.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import br.com.api.models.Pessoa;
import br.com.api.repository.PessoaRepository;

@Controller
@RequestMapping("/pessoa")
public class PessoaController extends GenericController<Pessoa, Long> {
  
	@Autowired
    public PessoaController(PessoaRepository repo) {
        super(repo);
    }
}