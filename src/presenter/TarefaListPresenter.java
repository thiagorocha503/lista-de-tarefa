/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presenter;

import presenter.interfaces.IPresenterList;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.bean.Tarefa;
import model.bean.exception.TarefaDateException;
import model.bean.exception.TarefaPrioridadeException;
import model.dao.TarefaDAO;
import model.tabelModel.TarefaTabelModel;
import util.Observer;
import util.exception.DateConversionException;
import view.interfaces.IViewLIst;

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
        TarefaDAO dao = new TarefaDAO();
        try {
            int id = Integer.parseInt(this.tarefaTabelModel.getValueAt(row, 0).toString());
            dao.removeById(id);
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
            this.view.showMessageInfo("Seleeção", "Selecione uma tarefa");
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
            TarefaDAO dao = new TarefaDAO();
            tasks = dao.findByTitleAndIsDone(title, done);
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
            TarefaDAO dao = new TarefaDAO();
            tasks = dao.findByTitle(title);
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
        TarefaDAO dao = new TarefaDAO();
        Map<String, Object> valores = (HashMap) o;
        try {
            Tarefa task = dao.getById((Integer) valores.get("id"));
            task.setDone((boolean) valores.get("done"));
            dao.update(task);
        } catch (SQLException ex) {
            Logger.getLogger(TarefaAddPresenterImp.class.getName()).log(Level.SEVERE, null, ex);
            this.view.showMessageErro("Erro", "Tabel model erro: " + ex);
        } catch (NullPointerException ex) {
            this.view.showMessageErro("Erro", "Tabel model erro: " + ex);
        }
    }
}
