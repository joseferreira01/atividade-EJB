/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifpb.dac.view;

import br.edu.ifpb.shared.Calculadora;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;


/**
 *
 * @author jose
 */
@Named
@RequestScoped
public class Controlador {
    private int x;
    private int y;
    private int resul;
    private Calculadora calculadora;
    private String RECURSO="";
    @PostConstruct
    public void init(){
        ServiceLocator lookup = new ServiceLocator();
        this.calculadora =lookup.lookup(RECURSO,Calculadora.class);
    }
    public String somar(){
        resul= calculadora.soma(x, y);
        return null;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getResul() {
        return resul;
    }

    public void setResul(int resul) {
        this.resul = resul;
    }
    
}

