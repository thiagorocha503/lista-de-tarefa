/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.thiagorocha503.listadetarefa.util;
/**
 *
 * @author thiago
 */
public interface SubjectTabel {

    public void addObserver(Observer o);
    
    public void removeObserver(Observer o);
        
    public void notifyObserver(int row, int column);
    
}
