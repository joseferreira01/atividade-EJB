/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.dac.atividade.jsf.infra;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PreDestroy;
import javax.ejb.PostActivate;
import javax.ejb.Singleton;
import org.primefaces.expression.impl.ThisExpressionResolver;

/**
 *
 * @author jose
 */
@Singleton
public class Conexao {

    private Connection connection;
    @PostActivate
public void cria(){
     try {
            Class.forName("org.postgresql.Driver");
             connection = DriverManager.
                     getConnection("jdbc:postgresql://localhost:5432/CONTATOS", "postgres", "12345");
                         
        } catch (SQLException ex) {
            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
        }
}
    public Connection init() {
       
        return connection;
    }
   @PreDestroy
    public  void fecharConexao() throws SQLException {
        connection.close();
    }
}
