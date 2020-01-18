/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.bean.Tarefa;
import model.dao.TarefaDAO;
import model.tabelModel.TarefaTabelModel;
import util.DateConversion;
import util.Observer;
import util.exception.DateConversionException;
import util.exception.TarefaDateException;
import util.exception.TarefaPrioridadeException;

/**
 *
 * @author thiago
 */
public class TarefaController implements Observer {

    private final TarefaTabelModel tarefaTabelModel;

    public TarefaController(TarefaTabelModel tarefaTabelModel) {
        this.tarefaTabelModel = tarefaTabelModel;
        this.tarefaTabelModel.addObserver(this);
    }

    public void inserir(String titulo, String descricao, String dataInicio,
            String dataTermino, int prioridade, boolean done
    ) {
        Tarefa task = new Tarefa();
        task.setTitle(titulo);
        task.setDescription(descricao);
        try {
            task.setDataInicio(DateConversion.dateFormtToCalendar(dataInicio));
            task.setDataTermino(DateConversion.dateFormtToCalendar(dataTermino));
            task.setPrioridade(prioridade);
            task.setDone(done);
            TarefaDAO dao = new TarefaDAO();
            dao.insert(task);
            this.findAll();
            JOptionPane.showMessageDialog(null, "Tarefa adicionada com sucesso", "Adição de tarefa", JOptionPane.INFORMATION_MESSAGE);
        } catch (DateConversionException ex) {
            Logger.getLogger(TarefaController.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Data inválida: " + ex.getDataInvalida(), "Conversão de dados", JOptionPane.ERROR_MESSAGE);
        } catch (TarefaDateException ex) {
            Logger.getLogger(TarefaController.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Datas de termino menor que data de inicio", "Datas inválida", JOptionPane.ERROR_MESSAGE);
        } catch (NullPointerException ex) {
            Logger.getLogger(TarefaController.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erro", "Erro: " + ex, JOptionPane.ERROR_MESSAGE);
        } catch (SQLException ex) {
            Logger.getLogger(TarefaController.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Banco de dados", "Erro: " + ex, JOptionPane.ERROR_MESSAGE);
        } catch (TarefaPrioridadeException ex) {
            Logger.getLogger(TarefaController.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Prioridade inválida", "Prioridade inserida inválida", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            Logger.getLogger(TarefaController.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erro", "Erro inesperado: " + ex, JOptionPane.ERROR_MESSAGE);
        }

    }

    public void findAll() {
        ArrayList<Tarefa> tasks;
        try {
            TarefaDAO dao = new TarefaDAO();
            tasks = dao.findByTitleAndIsDone("", false);
            this.tarefaTabelModel.updateTable(tasks);
        } catch (SQLException ex) {
            Logger.getLogger(TarefaController.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erro: " + ex, "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (TarefaPrioridadeException | TarefaDateException | DateConversionException ex) {
            Logger.getLogger(TarefaController.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erro: " + ex, "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void findByTitle(String titulo) {
        ArrayList<Tarefa> tasks;
        try {
            TarefaDAO dao = new TarefaDAO();
            tasks = dao.findByTitle(titulo);
            this.tarefaTabelModel.updateTable(tasks);
        } catch (SQLException ex) {
            Logger.getLogger(TarefaController.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erro: " + ex, "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (TarefaDateException ex) {
            Logger.getLogger(TarefaController.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Data de termino menor que de inicio " + ex, "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (DateConversionException ex) {
            Logger.getLogger(TarefaController.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Data inválida: " + ex.getDataInvalida(), "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Erro inesperado: " + ex, "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void findByTitleAndIsDone(String titulo, boolean done) {
        ArrayList<Tarefa> tasks;
        try {
            TarefaDAO dao = new TarefaDAO();
            tasks = dao.findByTitleAndIsDone(titulo, done);
            this.tarefaTabelModel.updateTable(tasks);
        } catch (SQLException ex) {
            Logger.getLogger(TarefaController.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erro: " + ex, "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (TarefaDateException ex) {
            Logger.getLogger(TarefaController.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Data de termino menor que de inicio " + ex, "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (DateConversionException ex) {
            Logger.getLogger(TarefaController.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Data inválida: " + ex.getDataInvalida(), "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Erro inesperado: " + ex, "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void remover(int row) {
        TarefaDAO dao = new TarefaDAO();
        try {
            int id = Integer.parseInt(this.tarefaTabelModel.getValueAt(row, 0).toString());
            dao.removeById(id);
            JOptionPane.showMessageDialog(null, "Tarefa removida com sucesso");
        } catch (SQLException ex) {
            Logger.getLogger(TarefaController.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erro ao excluir", "Erro", JOptionPane.ERROR_MESSAGE);
        } finally {
            this.findAll();
        }
    }

    public void update(int id, String titulo, String descricao, String dataInicio,
            String dataTermino, int prioridade, boolean done
    ) {
        Tarefa task = new Tarefa();
        task.setId(id);
        task.setTitle(titulo);
        task.setDescription(descricao);
        try {
            task.setDataInicio(DateConversion.dateFormtToCalendar(dataInicio));
            task.setDataTermino(DateConversion.dateFormtToCalendar(dataTermino));
            task.setPrioridade(prioridade);
            task.setDone(done);
            TarefaDAO dao = new TarefaDAO();
            dao.update(task);
            this.findAll();
            JOptionPane.showMessageDialog(null, "Tarefa atualizada com sucesso", "Adição de tarefa", JOptionPane.INFORMATION_MESSAGE);
        } catch (DateConversionException ex) {
            Logger.getLogger(TarefaController.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Data inválida: " + ex.getDataInvalida(), "Conversão de dados", JOptionPane.ERROR_MESSAGE);
        } catch (TarefaDateException ex) {
            Logger.getLogger(TarefaController.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Datas de termino menor que data de inicio", "Datas inválida", JOptionPane.ERROR_MESSAGE);
        } catch (NullPointerException ex) {
            Logger.getLogger(TarefaController.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erro", "Erro: " + ex, JOptionPane.ERROR_MESSAGE);
        } catch (SQLException ex) {
            Logger.getLogger(TarefaController.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Banco de dados", "Erro: " + ex, JOptionPane.ERROR_MESSAGE);
        } catch (TarefaPrioridadeException ex) {
            Logger.getLogger(TarefaController.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Prioridade inválida", "Prioridade inserida inválida", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            Logger.getLogger(TarefaController.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erro", "Erro inesperado: " + ex, JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void update(Object o) {
        //System.err.println("Algo mudou!");
        TarefaDAO dao = new TarefaDAO();
        Map<String, Object> valores = (HashMap) o;
        try {
            Tarefa task = dao.getById((Integer) valores.get("id"));
            task.setDone((boolean) valores.get("done"));
            dao.update(task);
        } catch (SQLException ex) {
            Logger.getLogger(TarefaController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NullPointerException ex) {
            JOptionPane.showMessageDialog(null, "Tarefa não encontrada");
        }
    }

}
