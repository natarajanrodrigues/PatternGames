/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.patterngames.model;

import br.edu.ifpb.patterngames.entity.Cliente;
import br.edu.ifpb.patterngames.entity.Jogo;
import br.edu.ifpb.patterngames.persistencia.JogoBdDao;
import java.util.List;

/**
 *
 * @author Natarajan
 */
public class JogoBo {
    
    private JogoBdDao dao = new JogoBdDao();

    public Jogo buscarPorId(String idJogo) {
        return dao.buscar(idJogo);
    }

    public List<Jogo> buscarTodos() {
        return dao.listarTodos();
    }
    
    public boolean adicionarObserver(Integer idJogo, String cpfCliente){
        List<Jogo> jogosCliente = buscarJogosObservados(cpfCliente);
        if (jogosCliente.contains(buscarPorId(idJogo.toString())))
            return true;
        return dao.adicionarObservador(idJogo, cpfCliente);
    }
    
    public boolean removerObserver(Integer idJogo, String cpfCliente){
        return dao.removerObservador(idJogo, cpfCliente);
    }
    
    public List<Cliente> buscarObservers(Integer idJogo) {
        return dao.buscarObservadores(idJogo);
    }
    
    public List<Jogo> buscarJogosObservados(String cpfCliente) {
        return dao.buscarJogosObservados(cpfCliente);
    }
}
