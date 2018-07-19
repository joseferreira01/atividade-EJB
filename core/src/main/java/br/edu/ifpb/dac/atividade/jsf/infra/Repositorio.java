/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.dac.atividade.jsf.infra;

import br.edu.ifpb.shared.Contato;
import br.edu.ifpb.shared.ContatoPersiste;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Local;
import javax.ejb.Stateless;

/**
 *
 * @author jose
 */
@Stateless
@Local(ContatoPersiste.class)
public class Repositorio implements ContatoPersiste {

    private Conexao em;

    public void salvar(Contato contato) {

        boolean resultado = false;
        String sql = "INSERT INTO contato (nome,email,dataDeNascimento,telefone) VALUES(?,?,?,?)";
        PreparedStatement statement = null;
        try {
            statement = em.init().prepareStatement(sql);

            statement.setString(1, contato.getNome());
            statement.setString(2, contato.getEmail());
            statement.setDate(3, Date.valueOf(contato.getDataNascimento()));
            statement.setString(4, contato.getTelefone());

            if (statement.executeUpdate() > 0) {
                resultado = true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(Repositorio.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void atualizar(Contato contato) {
        //  em.merge(contato);
    }

    public void remover(Contato contato) {
        System.err.println("remove " + contato);

    }

    @Override
    public Contato buscar(int id) {
        try {
            ResultSet rs;
            PreparedStatement st = em.init().prepareStatement("select * from contato where id=?");
            st.setInt(1, id);
            rs = st.executeQuery();

            Contato c = criarContato(rs);

            return c;

        } catch (SQLException ex) {
            Logger.getLogger(Repositorio.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public List<Contato> buscarTodos() {
        List<Contato> contatos = new ArrayList<>();
        try {
            ResultSet rs;
            PreparedStatement st = em.init().prepareStatement("select * from contato");
            rs = st.executeQuery();

            Contato c = criarContato(rs);
            contatos.add(c);

            return contatos;

        } catch (SQLException ex) {
            Logger.getLogger(Repositorio.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Collections.unmodifiableList(contatos);
    }

    public List<Contato> getAllFirstLettersAsc() {
        return Collections.EMPTY_LIST;
        //em.createQuery("select c from Contato c order by c.nome", Contato.class).getResultList();

    }

    public List<Contato> getContatoPorNome(String nome) {
        StringBuffer sql = new StringBuffer("SELECT c FROM Contato c "
        );
        sql.append(" WHERE UPPER(c.nome) LIKE");
        sql.append(nome);
        sql.append("'%'");
        // TypedQuery<Contato> query = em.createQuery(sql.toString(), Contato.class);
        List<Contato> contatos = new ArrayList<>();
        try {
            ResultSet rs;
            PreparedStatement st = em.init().prepareStatement(sql.toString());
            rs = st.executeQuery();

            Contato c = criarContato(rs);
            contatos.add(c);

            return contatos;

        } catch (SQLException ex) {
            Logger.getLogger(Repositorio.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Collections.unmodifiableList(contatos);
        //query.getResultList();
    }

    private Contato criarContato(ResultSet rs) {
        try {
            rs.next();
            Contato c = new Contato(rs.getString("nome"),
                    rs.getString("email"),
                    rs.getString("telefone"),
                    rs.getDate("dataDeNascimento").
                            toLocalDate());
            c.setId(rs.getInt("id"));
            return c;
        } catch (SQLException ex) {
            Logger.getLogger(Repositorio.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Contato("none", "none", "", LocalDate.now());
    }

}
