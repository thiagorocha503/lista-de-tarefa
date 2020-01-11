/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.bean;

import java.util.ArrayList;

/**
 *
 * @author thiago
 */
public interface TarefaImp {
    
    void insert(Tarefa task);
    ArrayList<Tarefa> findAll();
    ArrayList<Tarefa> findByTitle(String titulo);
    Tarefa getById(int id);
    void update(Tarefa task);
    void removeById(int id);
    
    
    
}
