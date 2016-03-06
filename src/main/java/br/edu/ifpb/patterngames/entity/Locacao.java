/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.patterngames.entity;

import br.edu.ifpb.patterngames.entity.strategy.LocacaoComum;
import br.edu.ifpb.patterngames.entity.strategy.LocacaoEspecial;
import br.edu.ifpb.patterngames.entity.strategy.LocacaoStrategy;
import br.edu.ifpb.patterngames.exceptions.JogoAlugadoException;
import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;

/**
 *
 * @author Natarajan
 */
public class Locacao {
    private String cpfCliente;
    private String idJogo;
    private LocalDate dataLocacao;
    private LocalDate dataDevolucao;
    private LocacaoStrategy strategy;
    private BigDecimal valorPago;
    private boolean isFinalizada;
    
    public Locacao(){
    }
    
    public Locacao(String cpfCliente, String idJogo) throws JogoAlugadoException{
        
        this.cpfCliente = cpfCliente;
        this.idJogo = idJogo;
        this.dataLocacao = LocalDate.now();
        
        if (dataLocacao.getDayOfWeek().equals(DayOfWeek.SUNDAY) || dataLocacao.getDayOfWeek().equals(DayOfWeek.SATURDAY)) {
            this.strategy = new LocacaoEspecial();
        } else {
            this.strategy = new LocacaoComum();
        }
        this.isFinalizada = false;
//        LocalDate dtPrevista = LocalDate.now().plusDays(strategy.getDURACAO() + 1);
//        this.jogo.alugar(dtPrevista);
        
    }

    public LocacaoStrategy getStrategy() {
        return strategy;
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
    
    

//    public void finalizar() throws JogoDisponivelException{
//        this.dataPrevistaDevolucao = LocalDate.now();
//        this.valorPago = calcularValor();
//        this.jogo.devolver();
//    }
//    
//    public void devolver() throws JogoDisponivelException{
//        this.jogo.devolver();
//    }
//
//    
//    @Override
//    public String toString() {
//        StringBuilder sb = new StringBuilder();
//        
//        sb.append("Cliente: ").append(getCliente().getNome()).append("\n");
//        sb.append("Jogo: ").append(getJogo().getNome()).append("\n");
//        sb.append("Data da Locacao: ").append(dataLocacao.toString()).append("\n");
//        sb.append("Tipo Locacao: ").append(getStrategy().getClass());
//        return sb.toString();
//    }

    public String getCpfCliente() {
        return cpfCliente;
    }

    public void setCpfCliente(String cpfCliente) {
        this.cpfCliente = cpfCliente;
    }

    public String getIdJogo() {
        return idJogo;
    }

    public void setIdJogo(String idJogo) {
        this.idJogo = idJogo;
    }

    public LocalDate getDataLocacao() {
        return dataLocacao;
    }

    public void setDataLocacao(LocalDate dataLocacao) {
        this.dataLocacao = dataLocacao;
    }

    public LocalDate getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(LocalDate dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public BigDecimal getValorPago() {
        return valorPago;
    }

    public void setValorPago(BigDecimal valorPago) {
        this.valorPago = valorPago;
    }

    public boolean isIsFinalizada() {
        return isFinalizada;
    }

    public void setIsFinalizada(boolean isFinalizada) {
        this.isFinalizada = isFinalizada;
    }
    
}
