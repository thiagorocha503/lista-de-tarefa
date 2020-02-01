/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.thiagorocha503.listadetarefa.view.interfaces;

/**
 *
 * @author thiago
 */
public interface IViewAdd {
    
    void onSalve();
    void onCancelar();
    void cleanField();
    void showMessageInfo(String info, String title);
    void showMessageErro(String erro, String title);
    void closeWindons();
    
}
