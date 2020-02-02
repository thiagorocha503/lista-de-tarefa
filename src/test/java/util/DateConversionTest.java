/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import io.github.thiagorocha503.listadetarefa.util.exception.DateConversionException;
import io.github.thiagorocha503.listadetarefa.util.DateConversion;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 *
 * @author thiago
 */
public class DateConversionTest{
    
    public DateConversionTest() {
    }

    @Test
    public void testCalendaToSqlDateDay() {
        Calendar data = Calendar.getInstance();
        data.set(Calendar.YEAR, 2020);
        data.set(Calendar.MONTH, 0);//Janeiro = 0, fevereiro = 1 [...]
        //Dias de 1 a 9
        for(int day=1;day<10; day++){
            data.set(Calendar.DAY_OF_MONTH, day);
            Assertions.assertEquals(DateConversion.calendarToDateSQL(data), "2020-01-"+"0"+String.valueOf(day));
        }
        //Dias de 10 a 31
        for (int day=10;day<32;day++){
            data.set(Calendar.DAY_OF_MONTH, day);
            Assertions.assertEquals(DateConversion.calendarToDateSQL(data), "2020-01-"+String.valueOf(day));
        }
    }
    
    @Test
    public void testCalendaToSqlDateMonth() {
        Calendar data = Calendar.getInstance();
        data.set(Calendar.YEAR, 2020);
        data.set(Calendar.DAY_OF_MONTH, 1); 
        // Meses de 1 a 9 OBS: Janeiro = 0, fevereiro = 1 [...]
        for (int month=0; month <=8;month++){
            data.set(Calendar.MONTH, month);
            Assertions.assertEquals(DateConversion.calendarToDateSQL(data), "2020-"+"0"+String.valueOf(month +1)+"-01");
        }
        // Meses de 10 1a 12
        data.set(Calendar.MONTH, 9);
        Assertions.assertEquals(DateConversion.calendarToDateSQL(data), "2020-10-01");
        data.set(Calendar.MONTH, 10);
        Assertions.assertEquals(DateConversion.calendarToDateSQL(data), "2020-11-01");
        data.set(Calendar.MONTH, 11);
        Assertions.assertEquals(DateConversion.calendarToDateSQL(data), "2020-12-01");
        
    }
    
    @Test
    public void testCalendaToSqlDateYear() {
        // teste anos
        Calendar data = Calendar.getInstance();
        data.set(Calendar.DAY_OF_MONTH, 1);
        data.set(Calendar.MONTH, 1); //Janeiro = 0, fevereiro = 1 [...]
        
        // ano de 1 a 9
        for (int i = 1; i < 10; i++){
            data.set(Calendar.YEAR, i);
            Assertions.assertEquals(DateConversion.calendarToDateSQL(data) , "000"+String.valueOf(i)+"-02-01" );
        }
        // ano de 10 a 99
        for (int i = 10; i < 100; i++){
            data.set(Calendar.YEAR, i);
            Assertions.assertEquals(DateConversion.calendarToDateSQL(data) , "00"+String.valueOf(i)+"-02-01" );
        }
        // anos entre 100 e 999
        data.set(Calendar.YEAR, 100);
        Assertions.assertEquals(DateConversion.calendarToDateSQL(data) , "0100-02-01" );
        data.set(Calendar.YEAR, 200);
        Assertions.assertEquals(DateConversion.calendarToDateSQL(data) , "0200-02-01" );
        data.set(Calendar.YEAR, 300);
        Assertions.assertEquals(DateConversion.calendarToDateSQL(data) , "0300-02-01" );
        data.set(Calendar.YEAR, 500);
        Assertions.assertEquals(DateConversion.calendarToDateSQL(data) , "0500-02-01" );
        data.set(Calendar.YEAR, 999);
        Assertions.assertEquals(DateConversion.calendarToDateSQL(data) , "0999-02-01" );
        
        // anos mais de 1000
        data.set(Calendar.YEAR, 1000);
        Assertions.assertEquals(DateConversion.calendarToDateSQL(data) , "1000-02-01" );
        data.set(Calendar.YEAR, 2000);
        Assertions.assertEquals(DateConversion.calendarToDateSQL(data) , "2000-02-01" );
        
        
        
    }
    @Test
    public void testSqlDateToCalendar(){
        String sql;
        Calendar data;
        try {
            //testa meses OBS: Janeiro = 0
            for(int i =1; i<10;i++){
                sql = "2020-" +"0"+String.valueOf(i)+ "-05";
                data = DateConversion.sqlDateToCalendar(sql);
                Assertions.assertEquals(data.get(Calendar.DAY_OF_MONTH), 5);
                Assertions.assertEquals(data.get(Calendar.MONTH), i-1);
                Assertions.assertEquals(data.get(Calendar.YEAR), 2020);      
            }
            
            data = DateConversion.sqlDateToCalendar("2020-11-05");
            Assertions.assertEquals(data.get(Calendar.DAY_OF_MONTH), 5);
            Assertions.assertEquals(data.get(Calendar.MONTH), 10);
            Assertions.assertEquals(data.get(Calendar.YEAR), 2020);      
            
            data = DateConversion.sqlDateToCalendar("2020-12-05");
            Assertions.assertEquals(data.get(Calendar.DAY_OF_MONTH), 5);
            Assertions.assertEquals(data.get(Calendar.MONTH), 11);
            Assertions.assertEquals(data.get(Calendar.YEAR), 2020);      
                        
        } catch (DateConversionException ex) {
            Logger.getLogger(DateConversionTest.class.getName()).log(Level.SEVERE, null, ex);
            Assertions.fail();
            
        }
    }   
        
    @Test
    public void testDateFormtToCalendar(){
        String sql;
        Calendar data;
        try {
           //testa meses OBS: Janeiro = 0
            for(int i =1; i<10;i++){
                sql = "05/" +"0"+String.valueOf(i)+ "/2020";
                data = DateConversion.dateFormtToCalendar(sql);
                Assertions.assertEquals(data.get(Calendar.DAY_OF_MONTH), 5);
                Assertions.assertEquals(data.get(Calendar.MONTH), i-1);
                Assertions.assertEquals(data.get(Calendar.YEAR), 2020);      
            }
            
            data = DateConversion.dateFormtToCalendar("05/11/2020");
            Assertions.assertEquals(data.get(Calendar.DAY_OF_MONTH), 5);
            Assertions.assertEquals(data.get(Calendar.MONTH), 10);
            Assertions.assertEquals(data.get(Calendar.YEAR), 2020);      
            
            data = DateConversion.dateFormtToCalendar("05/12/2020");
            Assertions.assertEquals(data.get(Calendar.DAY_OF_MONTH), 5);
            Assertions.assertEquals(data.get(Calendar.MONTH), 11);
            Assertions.assertEquals(data.get(Calendar.YEAR), 2020);        
        } catch (DateConversionException ex) {
            Logger.getLogger(DateConversionTest.class.getName()).log(Level.SEVERE, null, ex);
            Assertions.fail();
        }
        
        // teste dias
        try{
            // dias de 1 a 9
            for(int i =1; i<10;i++){
                sql = "0"+String.valueOf(i)+"/01/2020";
                data = DateConversion.dateFormtToCalendar(sql);
                Assertions.assertEquals(data.get(Calendar.DAY_OF_MONTH), i);
                Assertions.assertEquals(data.get(Calendar.MONTH), 0);
                Assertions.assertEquals(data.get(Calendar.YEAR), 2020);      
            }
            // dias de 10 a 31
            for(int i =10; i<32;i++){
                sql = String.valueOf(i)+"/01/2020";
                data = DateConversion.dateFormtToCalendar(sql);
                Assertions.assertEquals(data.get(Calendar.DAY_OF_MONTH), i);
                Assertions.assertEquals(data.get(Calendar.MONTH), 0);
                Assertions.assertEquals(data.get(Calendar.YEAR), 2020);      
            }
        } catch(DateConversionException ex){
            Logger.getLogger(DateConversionTest.class.getName()).log(Level.SEVERE, null, ex);
            Assertions.fail();
        }
        
    }
    
    
    @Test
    public void testCalendaToDateFormtDay() {
        Calendar data = Calendar.getInstance();
        data.set(Calendar.YEAR, 2020);
        data.set(Calendar.MONTH, 0);//Janeiro = 0, fevereiro = 1 [...]
        //Dias de 1 a 9
        for(int day=1;day<10; day++){
            data.set(Calendar.DAY_OF_MONTH, day);
            Assertions.assertEquals(DateConversion.calendarToDateFormt(data), "0"+String.valueOf(day)+"/01/2020");
        }
        //Dias de 10 a 31
        for (int day=10;day<32;day++){
            data.set(Calendar.DAY_OF_MONTH, day);
            Assertions.assertEquals(DateConversion.calendarToDateFormt(data), String.valueOf(day)+"/01/2020");
        }
    }
    
    @Test
    public void testCalendaToDateFormtMonth() {
        Calendar data = Calendar.getInstance();
        data.set(Calendar.YEAR, 2020);
        data.set(Calendar.DAY_OF_MONTH, 1); 
        // Meses de 1 a 9 OBS: Janeiro = 0, fevereiro = 1 [...]
        for (int month=0; month <=8;month++){
            data.set(Calendar.MONTH, month);
            Assertions.assertEquals(DateConversion.calendarToDateFormt(data), "01/"+"0"+String.valueOf(month +1)+"/2020");
        }
        // Meses de 10 1a 12
        data.set(Calendar.MONTH, 9);
        Assertions.assertEquals(DateConversion.calendarToDateFormt(data), "01/10/2020");
        data.set(Calendar.MONTH, 10);
        Assertions.assertEquals(DateConversion.calendarToDateFormt(data), "01/11/2020");
        data.set(Calendar.MONTH, 11);
        Assertions.assertEquals(DateConversion.calendarToDateFormt(data), "01/12/2020");
        
    }
    
    @Test
    public void testCalendaToDateFormtYear() {
        // teste anos
        Calendar data = Calendar.getInstance();
        data.set(Calendar.DAY_OF_MONTH, 1);
        data.set(Calendar.MONTH, 1); //Janeiro = 0, fevereiro = 1 [...]
        
        // ano de 1 a 9
        for (int i = 1; i < 10; i++){
            data.set(Calendar.YEAR, i);
            Assertions.assertEquals(DateConversion.calendarToDateFormt(data) , "01/02/"+ "000"+String.valueOf(i));
        }
        // ano de 10 a 99
        for (int i = 10; i < 100; i++){
            data.set(Calendar.YEAR, i);
            Assertions.assertEquals(DateConversion.calendarToDateFormt(data) , "01/02/"+ "00"+String.valueOf(i));
        }
        // anos entre 100 e 999
        data.set(Calendar.YEAR, 100);
        Assertions.assertEquals(DateConversion.calendarToDateFormt(data) , "01/02/0100" );
        data.set(Calendar.YEAR, 200);
        Assertions.assertEquals(DateConversion.calendarToDateFormt(data) , "01/02/0200" );
        data.set(Calendar.YEAR, 300);
        Assertions.assertEquals(DateConversion.calendarToDateFormt(data) , "01/02/0300" );
        data.set(Calendar.YEAR, 500);
        Assertions.assertEquals(DateConversion.calendarToDateFormt(data) , "01/02/0500" );
        data.set(Calendar.YEAR, 999);
        Assertions.assertEquals(DateConversion.calendarToDateFormt(data) , "01/02/0999" );
        // anos mais de 1000
        data.set(Calendar.YEAR, 1000);
        Assertions.assertEquals(DateConversion.calendarToDateFormt(data) , "01/02/1000" );
        data.set(Calendar.YEAR, 2000);
        Assertions.assertEquals(DateConversion.calendarToDateFormt(data) , "01/02/2000" );
        
        
        
    }

}
