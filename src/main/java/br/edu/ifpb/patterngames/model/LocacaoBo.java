/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.patterngames.model;

import br.edu.ifpb.patterngames.entity.Jogo;
import br.edu.ifpb.patterngames.entity.Locacao;
import br.edu.ifpb.patterngames.exceptions.JogoAlugadoException;
import br.edu.ifpb.patterngames.persistencia.JogoBdDao;
import br.edu.ifpb.patterngames.persistencia.LocacaoBdDao;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author Natarajan
 */
public class LocacaoBo {
    
    public boolean realizarLocacao(String idJogo, String cpfCliente) throws JogoAlugadoException{
        LocalDate dtPrevista;
        Jogo jogo = new JogoBdDao().buscar(idJogo);
        
        boolean retorno = false;
        try {
            jogo.alugar();
            //chamar JogoBdDao para setar o jogo como alugado no banco
            boolean salvouAluguelJogo = new JogoBdDao().alugar(jogo);
            if (salvouAluguelJogo) {
                LocacaoBdDao locacaoBdDao = new LocacaoBdDao();
                if (locacaoBdDao.salvar(new Locacao(cpfCliente, idJogo)))
                    return true;
            }
            
        } catch (JogoAlugadoException ex) {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//            try {
                Locacao ultimaLocacaoDoJogo = new LocacaoBdDao().procurarUltimaLocacao(jogo.getId());
                dtPrevista = LocalDate.now().plusDays(ultimaLocacaoDoJogo.getStrategy().getDURACAO() + 1);
                throw new JogoAlugadoException("No momento o jogo já está alugado. Deverá retornar em: " + dtPrevista.format(dtf));
//            } catch (JogoAlugadoException ex1) {
//                Logger.getLogger(LocacaoBdDao.class.getName()).log(Level.SEVERE, null, ex1);
//            }
        }
        return false;
    }
}
