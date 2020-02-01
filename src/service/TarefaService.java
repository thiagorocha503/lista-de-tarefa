/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.sql.SQLException;
import java.util.ArrayList;
import model.bean.Tarefa;
import model.bean.exception.TarefaDateException;
import model.bean.exception.TarefaPrioridadeException;
import model.dao.TarefaDAO;
import util.exception.DateConversionException;

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
