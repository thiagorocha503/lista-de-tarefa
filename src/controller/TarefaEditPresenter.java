/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import controller.interfaces.IPresenterEdit;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.bean.Tarefa;
import model.bean.exception.TarefaDateException;
import model.bean.exception.TarefaPrioridadeException;
import model.dao.TarefaDAO;
import util.DateConversion;
import util.exception.DateConversionException;
import view.interfaces.IViewEdit;

/**
 *
 * @author thiago
 */
public class TarefaEditPresenter implements IPresenterEdit {

    private IViewEdit view;
    private Tarefa task;

    @Override
    public void alterar(int id, String titulo, String descricao, String dataInicio, String dataTermino, int prioridade, boolean done) {
        this.task = new Tarefa();
        this.task.setId(id);
        this.task.setTitle(titulo);
        this.task.setDescription(descricao);
        try {
            task.setDataInicio(DateConversion.dateFormtToCalendar(dataInicio));
            task.setDataTermino(DateConversion.dateFormtToCalendar(dataTermino));
            task.setPrioridade(prioridade);
            task.setDone(done);
            TarefaDAO dao = new TarefaDAO();
            dao.update(task);
            this.view.showMessageInfo("Alteração", "Tarefa atualizada com sucesso");
            this.view.closeWindows();
        } catch (DateConversionException ex) {
            //Logger.getLogger(TarefaController.class.getName()).log(Level.SEVERE, null, ex);
            this.view.showMessageErro("Data inválida", "Data inválida: " + ex.getDataInvalida());
        } catch (TarefaDateException ex) {
            //Logger.getLogger(TarefaController.class.getName()).log(Level.SEVERE, null, ex);
            this.view.showMessageErro("Data inválida", "Datas de termino menor que data de inicio");
        } catch (NullPointerException ex) {
            this.view.showMessageErro("Erro", "Erro: " + ex);
        } catch (SQLException ex) {
            this.view.showMessageErro("Banco de dados", "Erro no banco de dados: " + ex);
        } catch (TarefaPrioridadeException ex) {
            this.view.showMessageErro("Prioridade inválida", "Prioridade inserida inválida");
        } catch (Exception ex) {
            Logger.getLogger(TarefaEditPresenter.class.getName()).log(Level.SEVERE, null, ex);
            this.view.showMessageErro("Erro", "Erro inesperado: " + ex);
        }
    }

    @Override
    public void setView(IViewEdit view) {
        this.view = view;
    }

    @Override
    public void onCancelar() {
        this.view.closeWindows();
    }

}
