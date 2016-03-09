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

    public boolean realizarLocacao(String idJogo, String cpfCliente) throws JogoAlugadoException {
        LocalDate dtPrevista = null;
        Jogo jogo = new JogoBdDao().buscar(idJogo);

        boolean retorno = false;
        try {
            jogo.alugar();
            //chamar JogoBdDao para setar o jogo como alugado no banco
            boolean salvouAluguelJogo = new JogoBdDao().alugar(jogo);
            if (salvouAluguelJogo) {
                LocacaoBdDao locacaoBdDao = new LocacaoBdDao();
                if (locacaoBdDao.salvar(new Locacao(cpfCliente, idJogo))) {
                    return true;
                }
            }
        } catch (JogoAlugadoException ex) {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            Locacao ultimaLocacaoDoJogo = new LocacaoBdDao().procurarUltimaLocacao(jogo.getId());
            if (ultimaLocacaoDoJogo != null)
                dtPrevista = LocalDate.now().plusDays(ultimaLocacaoDoJogo.getStrategy().getDURACAO());
            throw new JogoAlugadoException("No momento o jogo já está alugado. Deverá retornar em: " + dtPrevista.format(dtf));
        }
        return false;
    }

    public boolean finalizarLocacao(Integer idLocacao) throws JogoDisponivelException {
        Locacao loc = new LocacaoBdDao().buscarPorId(idLocacao);
        Jogo jogo = new JogoBdDao().buscar(loc.getIdJogo());

        if (new JogoBdDao().devolver(jogo)) {
            try {
                jogo.devolver();

            } catch (JogoDisponivelException ex) {
                throw new JogoDisponivelException();
            }

            return new LocacaoBdDao().finalizar(idLocacao);
        }
        return false;
    }

    public List<Locacao> buscarPorCliente(String cpfCliente) {
        return new LocacaoBdDao().buscarPorCliente(cpfCliente);
    }
}
