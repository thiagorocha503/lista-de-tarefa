/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.thiagorocha503.listadetarefa.service;

import java.sql.SQLException;
import java.util.ArrayList;
import io.github.thiagorocha503.listadetarefa.model.bean.Tarefa;
import io.github.thiagorocha503.listadetarefa.model.bean.exception.TarefaDateException;
import io.github.thiagorocha503.listadetarefa.model.bean.exception.TarefaPrioridadeException;
import io.github.thiagorocha503.listadetarefa.model.dao.TarefaDAO;
import io.github.thiagorocha503.listadetarefa.util.exception.DateConversionException;

/**
 *
 * @author thiago
 */
public class TarefaService {

    public Tarefa findById(int id) {
        TarefaDAO dao = new TarefaDAO();
        return dao.getById(id);
    }

    public ArrayList<Tarefa> findByTitle(String titulo) throws SQLException, TarefaDateException, DateConversionException {
        TarefaDAO dao = new TarefaDAO();
        return dao.findByTitle(titulo);
    }
    public ArrayList<Tarefa> findByTitleAndIsDone(String title, boolean done) throws SQLException, TarefaPrioridadeException, TarefaDateException, DateConversionException{
        TarefaDAO dao = new TarefaDAO();
        return dao.findByTitleAndIsDone(title, done);
        
    }
    public void insert(Tarefa t) throws SQLException {
        TarefaDAO dao = new TarefaDAO();
        dao.insert(t);
    }

    public void removeById(int id) throws SQLException {
        TarefaDAO dao = new TarefaDAO();
        dao.removeById(id);
    }
    
    public Tarefa getById(int id){
        TarefaDAO dao = new TarefaDAO();
        return dao.getById(id);
    }
    
    public void update(Tarefa tarefa) throws SQLException {
        TarefaDAO dao = new TarefaDAO();
        dao.update(tarefa);
    }
}
