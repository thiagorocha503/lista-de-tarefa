/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.thiagorocha503.listadetarefa.presenter.interfaces;

import io.github.thiagorocha503.listadetarefa.view.interfaces.IViewLIst;

/**
 *
 * @author thiago
 */
public interface IPresenterList {
    
    void excluir(int row);
    void alterar(int row);
    void adicionar();
    void find(String title, int filtro);
    void sair();
    void setView(IViewLIst view);
    void findByTitleAndIsDone(String title, boolean done);
    void findByTitle(String title);
    
  
    
}
