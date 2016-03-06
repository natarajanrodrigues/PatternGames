/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.patterngames.entity.state;

import br.edu.ifpb.patterngames.exceptions.JogoAlugadoException;
import java.time.LocalDate;

/**
 *
 * @author Natarajan
 */
public interface JogoState {
    
//    public JogoState alugar(LocalDate dataDevolucao);
    public JogoState alugar() throws JogoAlugadoException;
    
    public JogoState devolver();
    
}
