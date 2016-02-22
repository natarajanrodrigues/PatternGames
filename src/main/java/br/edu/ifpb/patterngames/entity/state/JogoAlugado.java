/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.patterngames.entity.state;

import br.edu.ifpb.patterngames.exceptions.JogoAlugadoException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Natarajan
 */
public class JogoAlugado implements JogoState{

    private LocalDate dataDevolucao;

    public JogoAlugado(LocalDate dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }
    
    @Override
    public JogoState alugar(LocalDate dataDevolucao){
        try {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            throw new JogoAlugadoException("No momento o jogo já está alugado. Deverá retornar em: " + this.dataDevolucao.format(dtf));
            
        } catch (JogoAlugadoException ex) {
            Logger.getLogger(JogoAlugado.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this;
    }

    @Override
    public JogoState devolver() {
        System.out.println("O jogo agora encontra-se disponível.");
        return new JogoDisponivel();
    }

        
}
