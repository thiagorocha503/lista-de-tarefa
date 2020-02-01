/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.thiagorocha503.listadetarefa.model.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import io.github.thiagorocha503.listadetarefa.model.bean.Tarefa;
import io.github.thiagorocha503.listadetarefa.util.exception.DateConversionException;
import io.github.thiagorocha503.listadetarefa.model.bean.exception.TarefaDateException;
import io.github.thiagorocha503.listadetarefa.model.bean.exception.TarefaPrioridadeException;

/**
 *
 * @author thiago
 */
public interface ITarefaDAO {

    void insert(Tarefa task) throws SQLException;

    ArrayList<Tarefa> findByTitle(String titulo) throws SQLException, TarefaDateException, DateConversionException;

    ArrayList<Tarefa> findByTitleAndIsDone(String title, boolean done) throws SQLException, TarefaPrioridadeException, TarefaDateException, DateConversionException;

    Tarefa getById(int id);

    void update(Tarefa task) throws SQLException;

    void removeById(int id) throws SQLException;

}
