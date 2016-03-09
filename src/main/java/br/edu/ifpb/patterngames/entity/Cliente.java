/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.patterngames.entity;

import br.edu.ifpb.patterngames.entity.observer.Observer;
import br.edu.ifpb.patterngames.entity.observer.NotificacaoPorEmail;

/**
 *
 * @author Natarajan
 */
public class Cliente implements Observer<Jogo> {

    private String nome, cpf, email;

    public Cliente(String nome, String cpf, String email) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
    }

    public Cliente() {
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public void update(Jogo object) {
        System.out.println("Enviando email:");
        //System.out.println("Caro Sr(a). " + getNome() + ", o jogo " + object.getNome() + " est́a dispońıvel para loca̧c̃ao! Corra agora para a Pattern Games para garantir sua jogatina!");
        NotificacaoPorEmail.sendEmail(this.email, "Caro Sr(a). " + getNome() + ", o jogo " + object.getNome() + " está disponível para locação! Corra agora para a Pattern Games para garantir sua jogatina!");
        

    }

}
