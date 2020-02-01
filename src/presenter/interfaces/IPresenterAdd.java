/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presenter.interfaces;

import model.bean.Tarefa;
import view.interfaces.IViewAdd;

/**
 *
 * @author thiago
 */
public interface IPresenterAdd {
    void onSalve(String titulo, String descricao, String dataInicio,
            String dataTermino, int prioridade, boolean done);
    void onCancelar();
    void setView(IViewAdd view);
}
