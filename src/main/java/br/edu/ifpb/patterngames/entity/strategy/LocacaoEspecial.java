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
public class LocacaoEspecial implements LocacaoStrategy{

    final static BigDecimal PRECO = new BigDecimal(5);
    final static Integer DURACAO = 2;
    
    private final static BigDecimal MULTA_BASE = new BigDecimal(3);
    
    private final static BigDecimal MULTA_POR_DIAS_ATRASO = new BigDecimal(3);
    
    @Override
    public BigDecimal calcularValor(Locacao locacao) {
        return calcularValor(locacao, LocalDate.now());
    }

    @Override
    public BigDecimal calcularValor(Locacao locacao, LocalDate dataDevolucao) {

        int diasLocados = locacao.getDataLocacao().until(dataDevolucao).getDays();
                
        if (diasLocados <= DURACAO){
            return PRECO;
        }
        
        int diasAtraso = diasLocados - DURACAO;
        
        BigDecimal multa = MULTA_BASE.add(MULTA_POR_DIAS_ATRASO.multiply(new BigDecimal(diasAtraso)));
        
        return PRECO.add(multa);
    }
    
    @Override
    public Integer getDURACAO() {
        return DURACAO;
    }

        
}
