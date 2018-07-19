/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.shared;

import br.edu.ifpb.shared.Contato;
import java.util.List;

/**
 *
 * @author jose
 */
public interface ContatoService {

    void atualizar(Contato contato);

    Contato buscar(int id);

    List<Contato> buscarTodos();

    List<Contato> getAllFirstLettersAsc();

    List<Contato> getContatoPorNome(String nome);

    void remover(Contato contato);

    void salvar(Contato contato);
    
}
