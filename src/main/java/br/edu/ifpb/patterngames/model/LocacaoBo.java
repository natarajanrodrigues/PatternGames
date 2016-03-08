/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.patterngames.model;

import br.edu.ifpb.patterngames.entity.Jogo;
import br.edu.ifpb.patterngames.entity.Locacao;
import br.edu.ifpb.patterngames.exceptions.JogoAlugadoException;
import br.edu.ifpb.patterngames.exceptions.JogoDisponivelException;
import br.edu.ifpb.patterngames.persistencia.JogoBdDao;
import br.edu.ifpb.patterngames.persistencia.LocacaoBdDao;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Natarajan
 */
public class LocacaoBo {

    private JogoBdDao       daoJogo     = new JogoBdDao();
    private LocacaoBdDao    daoLocacao  = new LocacaoBdDao();
    
    public boolean realizarLocacao(String idJogo, String cpfCliente) throws JogoAlugadoException {
        LocalDate dtPrevista = null;
        Jogo jogo = daoJogo.buscar(idJogo);

        boolean retorno = false;
        try {
            jogo.alugar();
            //chamar JogoBdDao para setar o jogo como alugado no banco
            if (daoJogo.alugar(jogo)) {
                return daoLocacao.salvar(new Locacao(cpfCliente, idJogo));
            }
        } catch (JogoAlugadoException ex) {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            Locacao ultimaLocacaoDoJogo = daoLocacao.procurarUltimaLocacao(jogo.getId());
            if (ultimaLocacaoDoJogo != null)
                dtPrevista = LocalDate.now().plusDays(ultimaLocacaoDoJogo.getStrategy().getDURACAO());
            throw new JogoAlugadoException("No momento o jogo já está alugado. Deverá retornar em: " + dtPrevista.format(dtf));
        }
        return false;
    }

    public boolean finalizarLocacao(Integer idLocacao) throws JogoDisponivelException {
        Locacao loc = daoLocacao.buscarPorId(idLocacao);
        Jogo jogo   = daoJogo.buscar(loc.getIdJogo());

        if (daoJogo.devolver(jogo)) {
            jogo.devolver();
            return daoLocacao.finalizar(idLocacao);
        }
        return false;
    }

    public List<Locacao> buscarPorCliente(String cpfCliente) {
        return daoLocacao.buscarPorCliente(cpfCliente);
    }
}
