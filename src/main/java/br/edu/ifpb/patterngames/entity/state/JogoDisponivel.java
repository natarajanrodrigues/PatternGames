/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.patterngames.entity.state;

import br.edu.ifpb.patterngames.exceptions.JogoDisponivelException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Natarajan
 */
public class JogoDisponivel implements JogoState{
    
    
    @Override
//    public JogoState alugar(LocalDate dataDevolucao) {
    public JogoState alugar() {
        
        System.out.println("O jogo está alugado agora. ");
//        return new JogoAlugado(dataDevolucao);
        return new JogoAlugado();
    }

    @Override
    public JogoState devolver() {
        System.out.println("O jogo já está disponível");
        try {
            throw new JogoDisponivelException();
        } catch (JogoDisponivelException ex) {
            Logger.getLogger(JogoDisponivel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this;
    }
    
}
