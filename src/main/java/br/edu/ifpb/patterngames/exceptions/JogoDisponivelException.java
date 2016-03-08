/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.patterngames.exceptions;

/**
 *
 * @author Natarajan
 */
public class JogoDisponivelException extends RuntimeException {

    public JogoDisponivelException() {
        super("O jogo já está disponível. Não precisa executar operação de disponibilizar neste estado.");
    }

    
    
}
