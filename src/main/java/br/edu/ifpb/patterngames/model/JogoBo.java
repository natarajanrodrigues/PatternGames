/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.patterngames.model;

import br.edu.ifpb.patterngames.entity.Jogo;
import br.edu.ifpb.patterngames.persistencia.JogoBdDao;
import java.util.List;

/**
 *
 * @author Natarajan
 */
public class JogoBo {

    public Jogo buscarPorId(String idJogo) {
        return new JogoBdDao().buscar(idJogo);
    }

    public boolean addObserverJogo(int idJogo, String cpfCliente) {
        return new JogoBdDao().adicionarObservador(idJogo, cpfCliente);
    }

    public boolean removeObserverJogo(int idJogo, String cpfCliente) {
        return new JogoBdDao().removerObservador(idJogo, cpfCliente);
    }

    public List<Jogo> buscarTodos() {
        return new JogoBdDao().listarTodos();
    }
    
    public boolean adicionarObserver(Integer idJogo, String cpfCliente){
        return new JogoBdDao().adicionarObservador(idJogo, cpfCliente);
    }
    
    public boolean removerObserver(Integer idJogo, String cpfCliente){
        return new JogoBdDao().removerObservador(idJogo, cpfCliente);
    }
}
