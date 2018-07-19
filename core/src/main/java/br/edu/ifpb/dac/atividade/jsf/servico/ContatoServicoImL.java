/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.dac.atividade.jsf.servico;



import br.edu.ifpb.dac.atividade.jsf.infra.Repositorio;
import br.edu.ifpb.shared.Contato;
import br.edu.ifpb.shared.ContatoPersiste;
import br.edu.ifpb.shared.ContatoService;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

/**
 *
 * @author jose
 */
@Stateless
@Remote(ContatoService.class)
public class ContatoServicoImL implements ContatoService{

    @EJB
    private ContatoPersiste repositorio;

    @Override
    public void salvar(Contato contato) {
        System.err.println("sando "+contato);
        repositorio.salvar(contato);
        
    }

    @Override
    public void atualizar(Contato contato) {
        System.err.println("atu" +contato);
        repositorio.atualizar(contato);
    }

    @Override
    public void remover(Contato contato) {
        this.repositorio.remover(contato);
    }

    @Override
    public Contato buscar(int id) {
        return repositorio.buscar(id);
    }

    @Override
    public List<Contato> buscarTodos() {
        return repositorio.buscarTodos();
    }

    @Override
    public List<Contato> getAllFirstLettersAsc() {
        return repositorio.getAllFirstLettersAsc();

    }

    @Override
    public List<Contato> getContatoPorNome(String nome) {
        return repositorio.getContatoPorNome(nome);
    }
}
