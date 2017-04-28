/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.algartelecom.repository;

import br.com.algartelecom.models.Pessoa;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author ronistone
 */
public interface PessoaRepository extends JpaRepository<Pessoa,Long>{
    
    public List<Pessoa> findByNome(String nome);
    public List<Pessoa> findByCpf(String cpf);
    public List<Pessoa> findByTelefone(String telefone);
    
}
