/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.bean;

import java.util.Calendar;
import util.exception.TarefaDateException;
import util.exception.TarefaPrioridadeException;

/**
 *
 * @author thiago
 */
public class Tarefa {
    
    private int id;
    private String title;
    private String description;
    private Calendar dataInicio;
    private Calendar dataTermino;
    private int prioridade;
    private boolean done;
    
    public final static int ALTA = 1;   
    public final static int BAIXA = 2;
    public final static int NORMAL = 3;
    
    public Tarefa() {
        
    }       
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Calendar getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Calendar dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Calendar getDataTermino() {
        return dataTermino;
    }

    public void setDataTermino(Calendar dataTermino) throws TarefaDateException, NullPointerException {
        if(this.dataInicio == null){
            throw new NullPointerException("Data de início igual a null");
        }
        if(dataTermino.compareTo(this.dataInicio) < 0){
            throw new TarefaDateException("Data menor que data de íncio");
        }
        this.dataTermino = dataTermino;
    }

    public int getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(int prioridade) throws TarefaPrioridadeException {
        if(prioridade == NORMAL || prioridade == ALTA || prioridade==BAIXA){           
            this.prioridade = prioridade;
        } else {
            throw new TarefaPrioridadeException("Prioridade inválida: "+prioridade);
        }
    }

    public boolean getDone() {
        return done;
    }

    public void setDone(boolean isDone) {
        this.done = isDone;
    }

    @Override
    public String toString() {
        return "Tarefa{" + "id=" + id + ", title=" + title + ", description=" + description + ", dataInicio=" + dataInicio + ", dataTermino=" + dataTermino + ", prioridade=" + prioridade + ", isDone=" + done + '}';
    }
    
    
    
}
