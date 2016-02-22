/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.patterngames.entity.observer;

/**
 *
 * @author Natarajan
 * @param <T>
 */
public interface Observer<T> {
    
    void update (T object);
    
}
