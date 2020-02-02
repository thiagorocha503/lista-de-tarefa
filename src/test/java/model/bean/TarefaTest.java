/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.bean;

import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import io.github.thiagorocha503.listadetarefa.model.bean.exception.TarefaDateException;
import io.github.thiagorocha503.listadetarefa.model.bean.Tarefa;
import io.github.thiagorocha503.listadetarefa.model.bean.exception.TarefaPrioridadeException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

/**
 *
 * @author thiago
 */
public class TarefaTest {
    
    public TarefaTest() {
    }

    @Test
    public void testSetDateTerminoValido(){
        
        Tarefa task = new Tarefa();
        Calendar dataInicio = Calendar.getInstance();
        dataInicio.set(Calendar.DAY_OF_MONTH, 5);
        dataInicio.set(Calendar.MONTH, 0);// janeiro=0
        dataInicio.set(Calendar.YEAR,2020);
        
        task.setDataInicio(dataInicio);
        
        Calendar dataTermino = Calendar.getInstance();        
        dataTermino.set(Calendar.MONTH, 0);// janeiro=0
        dataTermino.set(Calendar.YEAR,2020);       
        
        try {
            // data de termino igual ou maior data de inicio
            dataTermino.set(Calendar.DAY_OF_MONTH, 5);
            task.setDataTermino(dataTermino);
            
            dataTermino.set(Calendar.DAY_OF_MONTH, 6);
            task.setDataTermino(dataTermino);
        } catch (TarefaDateException ex) {           
            Logger.getLogger(TarefaTest.class.getName()).log(Level.SEVERE, null, ex);
            Assertions.fail("Data inválida");
        }       
            
    }
    
    public void testSetDataTerminoInvalido(){       
        Tarefa task = new Tarefa();
        Calendar dataInicio = Calendar.getInstance();
        dataInicio.set(Calendar.DAY_OF_MONTH, 5);
        dataInicio.set(Calendar.MONTH, 0);// janeiro=0
        dataInicio.set(Calendar.YEAR,2020);
        task.setDataInicio(dataInicio);
        
        Calendar dataTermino = Calendar.getInstance();        
        dataTermino.set(Calendar.MONTH, 0);// janeiro=0
        dataTermino.set(Calendar.YEAR,2020); 
        dataTermino.set(Calendar.DAY_OF_MONTH, 2);   
        try {
            task.setDataTermino(dataTermino);
            Assertions.fail("Data maior que data de inicio");
        } catch (TarefaDateException ex) {
            //Logger.getLogger(TarefaTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        dataTermino.set(Calendar.DAY_OF_MONTH, 6);  
        try {
            task.setDataInicio(null);
            task.setDataTermino(dataTermino);
            Assertions.fail("Data de inicio diferente de null");
        } catch (NullPointerException ex) {// Erro esperado
            //Logger.getLogger(TarefaTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TarefaDateException ex) {
            Logger.getLogger(TarefaTest.class.getName()).log(Level.SEVERE, null, ex);
            Assertions.fail("Tarefa Date exception não experado");
        } 
        
        
    }
    
    @Test
    public void testPrioridade(){
        Tarefa task = new Tarefa();
        int prioridades[] = {Tarefa.BAIXA, Tarefa.NORMAL, Tarefa.ALTA};
        // valores validos
        try{
            for(int i: prioridades){
                task.setPrioridade(i);
            } 
        } catch (TarefaPrioridadeException ex) {
            Logger.getLogger(TarefaTest.class.getName()).log(Level.SEVERE, null, ex);
            Assertions.fail("Valor inválido inesperado");
        }
        //valores invalidos
        int valores[] = {-1, 0, 4, 5};
        for(int j: valores){
            try {
                task.setPrioridade(j);
                Assertions.fail("Valor válido não esperado: "+j);
            } catch (TarefaPrioridadeException ex) {
                //Logger.getLogger(TarefaTest.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        
       
    }
}
