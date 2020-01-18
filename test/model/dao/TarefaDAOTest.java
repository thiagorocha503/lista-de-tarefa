/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import connection.ConnectionFatory;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import junit.framework.TestCase;
import model.bean.Tarefa;
import org.junit.Test;
import util.DateConversion;
import util.exception.DateConversionException;
import util.exception.TarefaDateException;
import util.exception.TarefaPrioridadeException;

/**
 *
 * @author thiago
 */
public class TarefaDAOTest extends TestCase {

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
        System.err.println(">> " + path);
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
                fail("Erro ao apagar tabela");
            }
        } else {
            fail("Caminho não encontrado: " + path);
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
            fail("Erro de data");
        } catch (NullPointerException ex) {
            Logger.getLogger(TarefaDAOTest.class.getName()).log(Level.SEVERE, null, ex);
            fail("null error");
        }
        try {
            task.setPrioridade(PRIORIDADE);
        } catch (TarefaPrioridadeException ex) {
            Logger.getLogger(TarefaDAOTest.class.getName()).log(Level.SEVERE, null, ex);
            fail("Prioridade inválida");
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
        assertNotNull(task);

    }

    @Test
    public void testGetById() {
        resetDataBase();
        TarefaDAO dao = new TarefaDAO();
        Tarefa task = getTarefa();
        try {
            dao.insert(task);
            task = dao.getById(1);
            assertNotNull(task);
        } catch (SQLException ex) {
            Logger.getLogger(TarefaDAOTest.class.getName()).log(Level.SEVERE, null, ex);
            fail();
        }

        assertEquals(task.getId(), 1);
        assertEquals(task.getTitle(), TITULO);
        assertEquals(task.getDescription(), DESCRIPTCION);
        try {
            assertEquals(DateConversion.calendarToDateSQL(task.getDataInicio()), DATA_INICIO);
            assertEquals(DateConversion.calendarToDateSQL(task.getDataTermino()), DATA_TERMINO);
        } catch (Exception ex) {
            Logger.getLogger(TarefaDAOTest.class.getName()).log(Level.SEVERE, null, ex);
            fail("Erro: " + ex);
        }
        assertEquals(task.getDone(), IS_DONE);
        assertEquals(task.getPrioridade(), PRIORIDADE);

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
            fail("Erro remover");
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
            assertNotNull(task);
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
            fail();
        }

        task = dao.getById(1);
        assertNotNull(task);
        assertEquals(task.getId(), 1);
        assertEquals(task.getTitle(), "aaaa");
        assertEquals(task.getDescription(), DESCRIPTCION);
        try {
            assertEquals(DateConversion.calendarToDateSQL(task.getDataInicio()), DATA_INICIO);
            assertEquals(DateConversion.calendarToDateSQL(task.getDataTermino()), "2020-01-16");
        } catch (Exception ex) {
            Logger.getLogger(TarefaDAOTest.class.getName()).log(Level.SEVERE, null, ex);
            fail("Erro: " + ex);
        }
        assertEquals(task.getDone(), true);
        assertEquals(task.getPrioridade(), 1);
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
                fail();
            }
        });

        ArrayList<Tarefa> resultado = null;

        try {
            // pesquisa tudos
            resultado = dao.findByTitle("");
            assertEquals(resultado.size(), 8);

            // pesquisa tarefa não feitas
            resultado = dao.findByTitleAndIsDone("", false);
        } catch (SQLException | TarefaDateException | DateConversionException | TarefaPrioridadeException ex) {
            Logger.getLogger(TarefaDAOTest.class.getName()).log(Level.SEVERE, null, ex);
            fail();
        }
        assertEquals(resultado.size(), 4);
        for (Tarefa tarefa : resultado) {
            assertEquals(tarefa.getDone(), false);
        }

        try {
            // pesquisa pelo título da tarefa
            resultado = dao.findByTitle("A");
            assertEquals(resultado.size(), 2);
            resultado = dao.findByTitle("X");
            assertEquals(resultado.size(), 4);
            resultado = dao.findByTitle("E");
            assertEquals(resultado.size(), 0);
        } catch (SQLException | TarefaDateException | DateConversionException ex) {
            Logger.getLogger(TarefaDAOTest.class.getName()).log(Level.SEVERE, null, ex);
            fail();
        }

    }
}
