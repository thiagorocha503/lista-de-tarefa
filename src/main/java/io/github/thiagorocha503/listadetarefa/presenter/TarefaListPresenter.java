/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.thiagorocha503.listadetarefa.presenter;

import io.github.thiagorocha503.listadetarefa.model.bean.exception.TarefaDateException;
import io.github.thiagorocha503.listadetarefa.model.bean.exception.TarefaPrioridadeException;
import io.github.thiagorocha503.listadetarefa.model.tabelModel.TarefaTabelModel;
import io.github.thiagorocha503.listadetarefa.model.bean.Tarefa;
import io.github.thiagorocha503.listadetarefa.presenter.interfaces.IPresenterList;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import io.github.thiagorocha503.listadetarefa.service.TarefaService;
import io.github.thiagorocha503.listadetarefa.util.Observer;
import io.github.thiagorocha503.listadetarefa.util.exception.DateConversionException;
import io.github.thiagorocha503.listadetarefa.view.interfaces.IViewLIst;

/**
 *
 * @author thiago
 */
public class TarefaListPresenter implements IPresenterList, Observer {

    private final TarefaTabelModel tarefaTabelModel;
    private IViewLIst view;

    public TarefaListPresenter(TarefaTabelModel tabelModel) {
        this.tarefaTabelModel = tabelModel;
        this.tarefaTabelModel.addObserver(this);

    }

    @Override
    public void excluir(int row) {
        if (row == -1) {
            this.view.showMessageInfo("Seleção", "Selecione uma tarefa");
            return;
        }
        if(! this.view.showConfirmDialogExcluir()){// caso cancelar
            return;
        }
        // Service DAO
        TarefaService serviceDAO = new TarefaService();
        try {
            int id = Integer.parseInt(this.tarefaTabelModel.getValueAt(row, 0).toString());
            serviceDAO.removeById(id);
            this.view.showMessageInfo("Sucesso", "Tarefa removida com sucesso");
        } catch (SQLException ex) {
            Logger.getLogger(TarefaListPresenter.class.getName()).log(Level.SEVERE, null, ex);
            this.view.showMessageErro("Erro", "Erro ao excluir: " + ex.toString());
        } catch (NumberFormatException ex) {
            this.view.showMessageErro("Erro", "Id inválido " + this.tarefaTabelModel.getValueAt(row, 0).toString());
        } catch (Exception ex) {
            this.view.showMessageErro("Erro", "Erro inesperado: " + ex.toString());
        }

    }

    @Override
    public void adicionar() {
        this.view.openDialogAdd();

    }

    @Override
    public void alterar(int row) {
        if (row == -1) {
            this.view.showMessageInfo("Seleção", "Selecione uma tarefa");
            return;
        }
        this.view.openDialogEdit();

    }

    @Override
    public void find(String title, int filtroSelecionado) {
        if (filtroSelecionado == 0) {
            this.findByTitleAndIsDone(title, false);
        } else if (filtroSelecionado == 1) {
            this.findByTitleAndIsDone(title, true);
        } else if (filtroSelecionado == 2) {
            this.findByTitle(title);
        }
    }

    @Override
    public void sair() {
        this.view.closeWindown();
    }

    @Override
    public void setView(IViewLIst view) {
        this.view = view;
    }

    @Override
    public void findByTitleAndIsDone(String title, boolean done) {
        ArrayList<Tarefa> tasks;
        try {
            TarefaService serviceDAO = new TarefaService();
            tasks = serviceDAO.findByTitleAndIsDone(title, done);
            this.tarefaTabelModel.updateTable(tasks);
        } catch (SQLException ex) {
            Logger.getLogger(TarefaListPresenter.class.getName()).log(Level.SEVERE, null, ex);
            this.view.showMessageErro("Erro", "Erro no banco de dados: " + ex);
        } catch (TarefaDateException ex) {
            Logger.getLogger(TarefaListPresenter.class.getName()).log(Level.SEVERE, null, ex);
            this.view.showMessageErro("Inconsistência de dados", "Data de termino menor que de inicio " + ex);
        } catch (DateConversionException ex) {
            Logger.getLogger(TarefaListPresenter.class.getName()).log(Level.SEVERE, null, ex);
            this.view.showMessageErro("Inconsistência de dados", "Data inválida: " + ex.getDataInvalida());
        } catch (TarefaPrioridadeException ex) {
            Logger.getLogger(TarefaListPresenter.class.getName()).log(Level.SEVERE, null, ex);
            this.view.showMessageErro("Inconsistência de dados", "prioridade inválida: " + ex.toString());
        } catch (Exception ex) {
            Logger.getLogger(TarefaListPresenter.class.getName()).log(Level.SEVERE, null, ex);
            this.view.showMessageErro("Erro inesperado", ex.toString());
        }
    }

    @Override
    public void findByTitle(String title) {
        ArrayList<Tarefa> tasks;
        try {
            TarefaService serviceDAO = new TarefaService();
            tasks = serviceDAO.findByTitle(title);
            this.tarefaTabelModel.updateTable(tasks);
        } catch (SQLException ex) {
            Logger.getLogger(TarefaListPresenter.class.getName()).log(Level.SEVERE, null, ex);
            this.view.showMessageErro("Erro no banco de dados", ex.toString());
        } catch (TarefaDateException ex) {
            Logger.getLogger(TarefaListPresenter.class.getName()).log(Level.SEVERE, null, ex);
            this.view.showMessageErro("Inconsitência de dados", ex.getMessage());
        } catch (DateConversionException ex) {
            Logger.getLogger(TarefaListPresenter.class.getName()).log(Level.SEVERE, null, ex);
            this.view.showMessageErro("Inconsitência de dados", ex.toString());
        } catch (Exception ex) {
            this.view.showMessageErro("Erro inesperado", "Erro: " + ex.toString());
        }
    }

    @Override
    public void update(Object o) {
        System.err.println("Algo mudou!");
        TarefaService serviceDAO = new TarefaService();
        Map<String, Object> valores = (HashMap) o;
        try {
            Tarefa task = serviceDAO.getById((Integer) valores.get("id"));
            task.setDone((boolean) valores.get("done"));
            serviceDAO.update(task);
        } catch (SQLException ex) {
            Logger.getLogger(TarefaAddPresenterImp.class.getName()).log(Level.SEVERE, null, ex);
            this.view.showMessageErro("Erro", "Tabel model erro: " + ex);
        } catch (NullPointerException ex) {
            this.view.showMessageErro("Erro", "Tabel model erro: " + ex);
        }
    }
}
