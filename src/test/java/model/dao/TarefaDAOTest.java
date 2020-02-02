/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import io.github.thiagorocha503.listadetarefa.connection.ConnectionFatory;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import io.github.thiagorocha503.listadetarefa.model.bean.Tarefa;

import io.github.thiagorocha503.listadetarefa.model.dao.TarefaDAO;
import io.github.thiagorocha503.listadetarefa.util.DateConversion;
import io.github.thiagorocha503.listadetarefa.util.exception.DateConversionException;
import io.github.thiagorocha503.listadetarefa.model.bean.exception.TarefaDateException;
import io.github.thiagorocha503.listadetarefa.model.bean.exception.TarefaPrioridadeException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 *
 * @author thiago
 */
public class TarefaDAOTest {

    final String TITULO = "titulo";
    final String DESCRIPTCION = "texto";
    final String DATA_INICIO = "2020-01-08";
    final String DATA_TERMINO = "2020-01-16";
    final boolean IS_DONE = false;
    final int PRIORIDADE = 3;

    /**
     * Apagar tabela para novos testes
     *
     */
    public void resetDataBase() {
        String path = ConnectionFatory.getPATH();
        //System.err.println(">> " + path);
        File data = new File(path);
        if (data.exists()) {
            Connection conn = ConnectionFatory.getConnection();
            String sql = "DROP TABLE IF EXISTS tarefa";
            PreparedStatement stmt;
            try {
                stmt = conn.prepareStatement(sql);
                stmt.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(TarefaDAOTest.class.getName()).log(Level.SEVERE, null, ex);
                Assertions.fail("Erro ao apagar tabela");
            }
        } else {
            Assertions.fail("Caminho não encontrado: " + path);
        }

    }

    public Tarefa getTarefa() {
        Tarefa task = new Tarefa();
        task.setTitle(TITULO);
        task.setDescription(DESCRIPTCION);
        try {
            task.setDataInicio(DateConversion.sqlDateToCalendar(DATA_INICIO));
            task.setDataTermino(DateConversion.sqlDateToCalendar(DATA_TERMINO));
        } catch (DateConversionException | TarefaDateException ex) {
            Logger.getLogger(TarefaDAOTest.class.getName()).log(Level.SEVERE, null, ex);
            Assertions.fail("Erro de data");
        } catch (NullPointerException ex) {
            Logger.getLogger(TarefaDAOTest.class.getName()).log(Level.SEVERE, null, ex);
            Assertions.fail("null error");
        }
        try {
            task.setPrioridade(PRIORIDADE);
        } catch (TarefaPrioridadeException ex) {
            Logger.getLogger(TarefaDAOTest.class.getName()).log(Level.SEVERE, null, ex);
            Assertions.fail("Prioridade inválida");
        }
        task.setDone(IS_DONE);
        return task;
    }

    @Test
    public void testInsert() {
        resetDataBase();
        Tarefa task = getTarefa();
        TarefaDAO dao = new TarefaDAO();
        // testa inserir
        try {
            dao.insert(task);
        } catch (SQLException ex) {
            Logger.getLogger(TarefaDAOTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        dao = new TarefaDAO();
        task = dao.getById(1);

        //verifica se o dado foi salvo
        Assertions.assertNotNull(task);

    }

    @Test
    public void testGetById() {
        resetDataBase();
        TarefaDAO dao = new TarefaDAO();
        Tarefa task = getTarefa();
        try {
            dao.insert(task);
            task = dao.getById(1);
            Assertions.assertNotNull(task);
        } catch (SQLException ex) {
            Logger.getLogger(TarefaDAOTest.class.getName()).log(Level.SEVERE, null, ex);
            Assertions.fail();
        }

        Assertions.assertEquals(task.getId(), 1);
        Assertions.assertEquals(task.getTitle(), TITULO);
        Assertions.assertEquals(task.getDescription(), DESCRIPTCION);
        try {
            Assertions.assertEquals(DateConversion.calendarToDateSQL(task.getDataInicio()), DATA_INICIO);
            Assertions.assertEquals(DateConversion.calendarToDateSQL(task.getDataTermino()), DATA_TERMINO);
        } catch (Exception ex) {
            Logger.getLogger(TarefaDAOTest.class.getName()).log(Level.SEVERE, null, ex);
            Assertions.fail("Erro: " + ex);
        }
        Assertions.assertEquals(task.getDone(), IS_DONE);
        Assertions.assertEquals(task.getPrioridade(), PRIORIDADE);

    }

    @Test
    public void testRemoveById() {
        resetDataBase();
        Tarefa task = getTarefa();
        TarefaDAO dao = new TarefaDAO();
        try {
            dao.insert(task);
            dao.removeById(1);
        } catch (SQLException ex) {
            Logger.getLogger(TarefaDAOTest.class.getName()).log(Level.SEVERE, null, ex);
            Assertions.fail("Erro remover");
        }
    }

    @Test
    public void testUpdate() {
        resetDataBase();
        Tarefa task = getTarefa();
        TarefaDAO dao = new TarefaDAO();
        // testa inserir
        try {
            dao.insert(task);
            task = dao.getById(1);
            Assertions.assertNotNull(task);
        } catch (SQLException ex) {
            Logger.getLogger(TarefaDAOTest.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            task.setTitle("aaaa");
            task.setDone(true);
            task.setDataTermino(DateConversion.sqlDateToCalendar("2020-01-16"));
            task.setPrioridade(1);
            dao.update(task);
        } catch (TarefaPrioridadeException | SQLException | DateConversionException | TarefaDateException | NullPointerException ex) {
            Logger.getLogger(TarefaDAOTest.class.getName()).log(Level.SEVERE, null, ex);
            Assertions.fail();
        }

        task = dao.getById(1);
        Assertions.assertNotNull(task);
        Assertions.assertEquals(task.getId(), 1);
        Assertions.assertEquals(task.getTitle(), "aaaa");
        Assertions.assertEquals(task.getDescription(), DESCRIPTCION);
        try {
            Assertions.assertEquals(DateConversion.calendarToDateSQL(task.getDataInicio()), DATA_INICIO);
             Assertions.assertEquals(DateConversion.calendarToDateSQL(task.getDataTermino()), "2020-01-16");
        } catch (Exception ex) {
            Logger.getLogger(TarefaDAOTest.class.getName()).log(Level.SEVERE, null, ex);
            Assertions.fail("Erro: " + ex);
        }
         Assertions.assertEquals(task.getDone(), true);
         Assertions.assertEquals(task.getPrioridade(), 1);
    }

    public ArrayList<Tarefa> getListaTarefa() {
        return null;
    }

    @Test
    public void testFind() {
        resetDataBase();
        String[] titulosDone = {"AAA", "BBB", "CCC", "DDD"};
        String[] titulosNotDone = {"XXA", "XXXB", "XCX", "XXD"};
        ArrayList<Tarefa> tasks = new ArrayList<>();

        // popula tabela
        // tarefas feitas
        for (String i : titulosDone) {
            Tarefa tarefa = getTarefa();
            tarefa.setTitle(i);
            tarefa.setDone(true);
            tasks.add(tarefa);
        }
        // tarefas não feitas
        for (String j : titulosNotDone) {
            Tarefa tarefa = getTarefa();
            tarefa.setTitle(j);
            tarefa.setDone(false);
            tasks.add(tarefa);
        }
        TarefaDAO dao = new TarefaDAO();
        // for in
        tasks.forEach((tarefa) -> {
            try {
                dao.insert(tarefa);
            } catch (SQLException ex) {
                Logger.getLogger(TarefaDAOTest.class.getName()).log(Level.SEVERE, null, ex);
                Assertions.fail();
            }
        });

        ArrayList<Tarefa> resultado = null;

        try {
            // pesquisa tudos
            resultado = dao.findByTitle("");
            Assertions.assertEquals(resultado.size(), 8);

            // pesquisa tarefa não feitas
            resultado = dao.findByTitleAndIsDone("", false);
        } catch (SQLException | TarefaDateException | DateConversionException | TarefaPrioridadeException ex) {
            Logger.getLogger(TarefaDAOTest.class.getName()).log(Level.SEVERE, null, ex);
            Assertions.fail();
        }
        Assertions.assertEquals(resultado.size(), 4);
        for (Tarefa tarefa : resultado) {
            Assertions.assertEquals(tarefa.getDone(), false);
        }

        try {
            // pesquisa pelo título da tarefa
            resultado = dao.findByTitle("A");
            Assertions.assertEquals(resultado.size(), 2);
            resultado = dao.findByTitle("X");
            Assertions.assertEquals(resultado.size(), 4);
            resultado = dao.findByTitle("E");
            Assertions.assertEquals(resultado.size(), 0);
        } catch (SQLException | TarefaDateException | DateConversionException ex) {
            Logger.getLogger(TarefaDAOTest.class.getName()).log(Level.SEVERE, null, ex);
            Assertions.fail();
        }

    }
}
