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
public class JogoAlugadoException extends RuntimeException{

    public JogoAlugadoException(){
        
    }
    
    public JogoAlugadoException(String message) {
        super(message);
    }

    

//    public JogoAlugadoException(Locacao locacao) {
//        super();
//        int diasLocacao = locacao.getStrategy().getDURACAO();
//        LocalDate dataDevolucao = locacao.getDataLocacao().plusDays(diasLocacao + 1);
//        String mensagem = "O jogo " + locacao.getJogo().getNome() + " deverá estar disponível para locação em " + dataDevolucao.format(DateTimeFormatter.ISO_LOCAL_TIME);
//    
//    }
    
    
}
