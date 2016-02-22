/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.patterngames.entity;

import br.edu.ifpb.patterngames.entity.observer.Observable;
import br.edu.ifpb.patterngames.entity.observer.Observer;
import br.edu.ifpb.patterngames.entity.state.JogoAlugado;
import br.edu.ifpb.patterngames.entity.state.JogoDisponivel;
import br.edu.ifpb.patterngames.entity.state.JogoState;
import br.edu.ifpb.patterngames.exceptions.JogoAlugadoException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Natarajan
 */
public class Jogo {

    protected JogoState estado;
    private Integer id;
    private String nome;
    private List<Observer<Jogo>> observers;

    public Jogo(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
        this.estado = new JogoDisponivel();
        this.observers = new ArrayList<>();
    }

    public Jogo() {
        this.estado = new JogoDisponivel();
    }

    public Integer getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void alugar(LocalDate dataDevolucao) throws JogoAlugadoException {
        this.estado = this.estado.alugar(dataDevolucao);
    }

    public void devolver() throws JogoAlugadoException {
        this.estado = this.estado.devolver();
    
    }

    

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Jogo: ").append(getNome()).append("\n");
        sb.append("Status: ");
        if (estado instanceof JogoAlugado) {
            sb.append("alugado");
        } else {
            sb.append("disponível");
        }
        return sb.toString();
    }

}
