/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifpb.dac.model;

import br.edu.ifpb.shared.Calculadora;
import javax.ejb.Remote;
import javax.ejb.Stateless;

/**
 *
 * @author jose
 */
@Remote(Calculadora.class)
@Stateless
public class CalculadoraImpl implements Calculadora{

    @Override
    public int soma(int x, int y) {
        return x+y;
    }
    
}
