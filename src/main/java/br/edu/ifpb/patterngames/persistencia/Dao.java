/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.patterngames.persistencia;

import java.util.List;
import java.util.Map;

/**
 * Interface generica de um objeto DAO
 *
 * @author victor
 * @param <T> um tipo T de objeto a ser persistido
 * @param <I> um tipo de chave I para mapear na objetos na persistência.
 */
public interface Dao<T, I> {

    /**
     * Método usado para persistir o conteúdo de um entidade.
     *
     * @param objeto Objeto a ser persistido
     * @return retorna <code>true</code> caso o método consiga realizar o
     * salvamento. Caso contrário retorna <code>false</code>.
     */
    boolean salvar(T objeto);

    /**
     * Método usado para apagar da persistênia dados de uma determinada instância de entidade.
     * @param objeto Objeto a ser apagado 
     * @return retorna <code>true</code> caso o método consiga realizar o deleção. Caso contrário retorna <code>false</code>.
     */
    boolean apagar(T objeto);

    /**
     * Método usado para alterar o estado da instância de uma entidade nos dados da persistência.
     * @param objeto Objeto a ser modificado/alterado
     * @return retorna <code>true</code> caso o método consiga realizar a alteração. Caso contrário retorna <code>false</code>.
     */
    boolean alterar(T objeto);
    
    /**
     * Método que retorna uma entidade cujos dados estão na persistência 
     * @param key
     * @return
     */
    T buscar(I key);
    
    /**
     * Lista todas as entidades de determinado tipo
     *
     * @return Lista contendo todas as entidades de determinado tipo
     */
    List<T> listarTodos();
}
