/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.patterngames.entity.strategy;

import br.edu.ifpb.patterngames.entity.Locacao;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 *
 * @author Natarajan
 */
public interface LocacaoStrategy {
    
    BigDecimal calcularValor(Locacao locacao);
    
    BigDecimal calcularValor(Locacao locacao, LocalDate dataDevolucao);
    
    Integer getDURACAO();
    
}
