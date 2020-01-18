/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.bean;

import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import junit.framework.TestCase;
import org.junit.Test;
import model.bean.exception.TarefaDateException;
import model.bean.exception.TarefaPrioridadeException;


/**
 *
 * @author thiago
 */
public class TarefaTest extends TestCase{
    
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
            fail("Data inválida");
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
            fail("Data maior que data de inicio");
        } catch (TarefaDateException ex) {
            //Logger.getLogger(TarefaTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        dataTermino.set(Calendar.DAY_OF_MONTH, 6);  
        try {
            task.setDataInicio(null);
            task.setDataTermino(dataTermino);
            fail("Data de inicio diferente de null");
        } catch (NullPointerException ex) {// Erro esperado
            //Logger.getLogger(TarefaTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TarefaDateException ex) {
            Logger.getLogger(TarefaTest.class.getName()).log(Level.SEVERE, null, ex);
            fail("Tarefa Date exception não experado");
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
            fail("Valor inválido inesperado");
        }
        //valores invalidos
        int valores[] = {-1, 0, 4, 5};
        for(int j: valores){
            try {
                task.setPrioridade(j);
                fail("Valor válido não esperado: "+j);
            } catch (TarefaPrioridadeException ex) {
                //Logger.getLogger(TarefaTest.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        
       
    }
}
