package br.com.algartelecom.models;

import br.com.algartelecom.SpringAPIApplication;

import java.io.Serializable;
import java.util.Set;
import java.util.logging.Logger;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ronistone
 */
@Entity
public class Pessoa implements Serializable {

    private static Logger LOGGER = Logger.getLogger(Pessoa.class.getName());


    @Id
    @GeneratedValue
    private Long id;
    private String nome;
    private String cpf;
    private String sexo;
    private Long idade;
    
    @ElementCollection
    private Set<String> telefone;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public Long getIdade() {
        return idade;
    }

    public void setIdade(Long idade) {
        this.idade = idade;
    }

    public Set<String> getTelefone() {
        return telefone;
    }

    public void setTelefone(Set<String> telefone) {
        this.telefone = telefone;
    }
    public String toString(){
        return "{" + "id:" + id + ", nome: "+ nome +", cpf: "+cpf+", sexo: "+ sexo+", telefone: "+telefone.toString()+"}";
    }
}
