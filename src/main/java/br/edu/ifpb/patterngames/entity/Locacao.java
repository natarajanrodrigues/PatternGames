/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.patterngames.entity;

import br.edu.ifpb.patterngames.entity.observer.Observable;
import br.edu.ifpb.patterngames.entity.observer.Observer;
import br.edu.ifpb.patterngames.entity.strategy.LocacaoComum;
import br.edu.ifpb.patterngames.entity.strategy.LocacaoEspecial;
import br.edu.ifpb.patterngames.entity.strategy.LocacaoStrategy;
import br.edu.ifpb.patterngames.exceptions.JogoAlugadoException;
import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Natarajan
 */
public class Locacao  implements Observable<Jogo>{
    private Cliente cliente;
    private LocalDate dataLocacao;
    private LocalDate dataDevolucao;
    private Jogo jogo;
    private LocacaoStrategy strategy;
    private BigDecimal valorPago;
    
    private List<Observer<Jogo>> observers;
        
    public Locacao(Cliente cliente, LocalDate dataLocacao, Jogo jogo) throws JogoAlugadoException{
        this.observers = new ArrayList<>();
        this.cliente = cliente;
        this.dataLocacao = dataLocacao;
        this.jogo = jogo;
        if (dataLocacao.getDayOfWeek().equals(DayOfWeek.SUNDAY) || dataLocacao.getDayOfWeek().equals(DayOfWeek.SATURDAY)) {
            this.strategy = new LocacaoEspecial();
        } else {
            this.strategy = new LocacaoComum();
        }
        LocalDate dtDevolucao = LocalDate.now().plusDays(strategy.getDURACAO() + 1);
        this.jogo.alugar(dtDevolucao);
        
    }

    public Cliente getCliente() {
        return cliente;
    }

    public LocalDate getDataLocacao() {
        return dataLocacao;
    }

    public LocalDate getDataDevolucao() {
        return dataDevolucao;
    }

    public Jogo getJogo() {
        return jogo;
    }

    public LocacaoStrategy getStrategy() {
        return strategy;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setDataLocacao(LocalDate dataLocacao) {
        this.dataLocacao = dataLocacao;
    }

    public void setDataDevolucao(LocalDate dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public void setJogo(Jogo jogo) {
        this.jogo = jogo;
    }

    public void setStrategy(LocacaoStrategy strategy) {
        this.strategy = strategy;
    }
    
    public BigDecimal calcularValor(){
        return this.strategy.calcularValor(this);
    }
    
    public BigDecimal calcularValor(LocalDate date){
        return this.strategy.calcularValor(this, date);
    }

    public void finalizar() throws JogoAlugadoException{
        this.dataDevolucao = LocalDate.now();
        this.valorPago = calcularValor();
        this.jogo.devolver();
    }
    
    @Override
    public void addObserver(Observer newObserver) {
        observers.add(newObserver);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(Jogo object) {
        for (Observer o : observers) {
            o.update(object);
        }
    }

    public void devolver(){
        try {
            this.jogo.devolver();
            for (Observer o : observers) {
                notifyObservers(this.jogo);
            }
        } catch (JogoAlugadoException ex) {
            Logger.getLogger(Locacao.class.getName()).log(Level.SEVERE, null, ex);
        }
        

    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        
        sb.append("Cliente: ").append(getCliente().getNome()).append("\n");
        sb.append("Jogo: ").append(getJogo().getNome()).append("\n");
        sb.append("Data da Locacao: ").append(dataLocacao.toString()).append("\n");
        sb.append("Tipo Locacao: ").append(getStrategy().getClass());
        return sb.toString();
    }
    
    
    
    
}
