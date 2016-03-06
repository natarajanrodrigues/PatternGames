/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.patterngames.entity.state;

import br.edu.ifpb.patterngames.exceptions.JogoAlugadoException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Natarajan
 */
public class JogoAlugado implements JogoState {

//    private LocalDate dataDevolucao;
//
//    public JogoAlugado(LocalDate dataDevolucao) {
//        this.dataDevolucao = dataDevolucao;
//    }
    
    public JogoAlugado() {
        
    }

    /**
     *
     * @return
     * @throws JogoAlugadoException
     */
    @Override
    public JogoState alugar() throws JogoAlugadoException{
        throw new JogoAlugadoException();
    }

    @Override
    public JogoState devolver() {
        System.out.println("O jogo agora encontra-se disponível.");
        return new JogoDisponivel();
    }

}
