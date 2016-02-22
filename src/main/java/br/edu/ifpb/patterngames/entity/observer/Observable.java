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
public interface Observable<T> {
    
    void addObserver(Observer newObserver);
    
    void removeObserver(Observer observer);
    
    void notifyObservers(T object);
    
}
