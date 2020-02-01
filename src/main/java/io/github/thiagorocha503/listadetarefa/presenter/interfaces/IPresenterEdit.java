/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.thiagorocha503.listadetarefa.presenter.interfaces;

import io.github.thiagorocha503.listadetarefa.view.interfaces.IViewEdit;

/**
 *
 * @author thiago
 */
public interface IPresenterEdit {
    
    void alterar(int id, String titulo, String descricao, String dataInicio,
            String dataTermino, int prioridade, boolean done
    );
    
    void setView(IViewEdit view);
    void onCancelar();
}
