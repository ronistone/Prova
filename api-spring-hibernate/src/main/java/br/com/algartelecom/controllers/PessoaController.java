/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.algartelecom.controllers;

import br.com.algartelecom.models.Pessoa;
import br.com.algartelecom.repository.PessoaRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ronistone
 */
@RestController
@RequestMapping(value = "/api")
public class PessoaController {
    
    @Autowired
    PessoaRepository repository;
    
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Pessoa>> get(@RequestParam(value = "nome",required=false) String nome,
                            @RequestParam(value = "cpf",required=false) String cpf,
                            @RequestParam(value = "telefone",required=false) String telefone){
        try{
            List<Pessoa> p = new ArrayList<>();
            if(nome!=null){
                p = repository.findByNome(nome);
            }
            else if(cpf!=null){
                p = repository.findByCpf(cpf);
            }
            else if(telefone!=null){
                p = repository.findByTelefone(telefone);
            }
            return new ResponseEntity<>(p,HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> post(@RequestBody Pessoa p){
        
        try{
            repository.save(p);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Pessoa> put(@RequestBody Pessoa p, @PathVariable Long id){
        try{
            p.setId(id);
            repository.save(p);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
}