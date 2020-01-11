/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import connection.ConnectionFatory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.bean.Tarefa;
import model.bean.TarefaImp;
import util.DateConversion;
import util.exception.DateConversionException;
import util.exception.TarefaDateException;
import util.exception.TarefaPrioridadeException;

/**
 *
 * @author thiago
 */
public class TarefaDAO implements TarefaImp{

    @Override
    public void insert(Tarefa task) throws SQLException{
        Connection conn = ConnectionFatory.getConnection();
        String sql = "INSERT INTO tarefa(title, description, data_inicio, data_termino, prioridade, is_done)"
                    + "VALUES(?,?,?,?,?,?)";
        PreparedStatement stmt =null;
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, task.getTitle());
            stmt.setString(2, task.getDescription());
            stmt.setString(3, DateConversion.calendarToDateSQL(task.getDataInicio()));
            stmt.setString(4, DateConversion.calendarToDateSQL(task.getDataTermino()));
            stmt.setInt(5, task.getPrioridade());
            stmt.setInt(6, (task.getDone())?1:0 );// tru e false sqlite3
            stmt.executeUpdate();
            int i = (task.getDone())?1:0;
        } catch (SQLException ex) {
            Logger.getLogger(TarefaDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new SQLException("Erro: "+ex);
        } finally{
            ConnectionFatory.closeConnection(conn, stmt);
        }  
    }

    @Override
    public ArrayList<Tarefa> findAll() throws SQLException{
      String sql = "SELECT * FROM tarefa";
        Connection conn = ConnectionFatory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Tarefa> tarefas = new ArrayList<>();
        try {
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            while(rs.next()){
                Tarefa task  =new Tarefa();
                task.setId(rs.getInt("id"));
                task.setTitle(rs.getString("title"));
                task.setDescription(rs.getString("description"));
                task.setDataInicio(DateConversion.sqlDateToCalendar(rs.getString("data_inicio")));
                task.setDataTermino(DateConversion.sqlDateToCalendar(rs.getString("data_termino")));
                task.setPrioridade(rs.getInt("prioridade"));
                task.setDone((rs.getInt("is_done") != 0));
                tarefas.add(task);
            }
            return tarefas;
        } catch(SQLException ex){
            Logger.getLogger(TarefaDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new SQLDataException(ex);
        } catch (DateConversionException | TarefaDateException | NullPointerException | TarefaPrioridadeException ex) {
            Logger.getLogger(TarefaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally{
            ConnectionFatory.closeConnection(conn, stmt, rs);
        }
    }
    
    @Override
    public ArrayList<Tarefa> findAllNotDone() throws SQLException,  TarefaDateException, DateConversionException,
            TarefaPrioridadeException{
      String sql = "SELECT * FROM tarefa WHERE is_done=0";
        Connection conn = ConnectionFatory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Tarefa> tarefas = new ArrayList<>();
        try {
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            while(rs.next()){
                Tarefa task  =new Tarefa();
                task.setId(rs.getInt("id"));
                task.setTitle(rs.getString("title"));
                task.setDescription(rs.getString("description"));
                task.setDataInicio(DateConversion.sqlDateToCalendar(rs.getString("data_inicio")));
                task.setDataTermino(DateConversion.sqlDateToCalendar(rs.getString("data_termino")));
                task.setPrioridade(rs.getInt("prioridade"));
                task.setDone((rs.getInt("is_done") != 0));
                tarefas.add(task);
            }
            return tarefas;
        } catch (DateConversionException ex){
            Logger.getLogger(TarefaDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new DateConversionException(ex.toString());
       } catch(SQLException  ex){
            Logger.getLogger(TarefaDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new SQLException(ex.toString());
        } catch (TarefaDateException ex) {
            Logger.getLogger(TarefaDAO.class.getName()).log(Level.SEVERE, null, ex);  
            throw new TarefaDateException(ex.toString());
        } catch (NullPointerException ex) {
            Logger.getLogger(TarefaDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw  new NullPointerException(ex.toString());
        } catch (TarefaPrioridadeException ex) {
            Logger.getLogger(TarefaDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new TarefaPrioridadeException(ex.toString());
        } finally{
            ConnectionFatory.closeConnection(conn, stmt, rs);
        }
    }

    @Override
    public ArrayList<Tarefa> findByTitle(String titulo) throws SQLException, TarefaDateException,
            DateConversionException{
        String sql = "SELECT * FROM tarefa WHERE title LIKE ?";
        Connection conn = ConnectionFatory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Tarefa> tarefas = new ArrayList<>();
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, "%"+titulo+"%");
            rs = stmt.executeQuery();
            while(rs.next()){
                Tarefa task  =new Tarefa();
                task.setId(rs.getInt("id"));
                task.setTitle(rs.getString("title"));
                task.setDescription(rs.getString("description"));
                task.setDataInicio(DateConversion.sqlDateToCalendar(rs.getString("data_inicio")));
                task.setDataTermino(DateConversion.sqlDateToCalendar(rs.getString("data_termino")));
                task.setPrioridade(rs.getInt("prioridade"));
                task.setDone((rs.getInt("is_done") != 0));
                tarefas.add(task);
            }
            return tarefas;
        } catch (SQLException ex) {
            Logger.getLogger(TarefaDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new SQLException(ex);
        } catch (TarefaPrioridadeException ex) {
            Logger.getLogger(TarefaDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new TarefaDateException(ex.toString());
        } catch (DateConversionException ex) {
            Logger.getLogger(TarefaDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new DateConversionException(ex.toString());
        } catch (TarefaDateException | NullPointerException ex) {
            Logger.getLogger(TarefaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally{
            ConnectionFatory.closeConnection(conn, stmt, rs);
        }
    }

    @Override
    public Tarefa getById(int id) {
        String sql = "SELECT * FROM tarefa WHERE id=?";
        Connection conn = ConnectionFatory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            if(rs.next()){
                Tarefa task  =new Tarefa();
                task.setId(rs.getInt("id"));
                task.setTitle(rs.getString("title"));
                task.setDescription(rs.getString("description"));
                task.setDataInicio(DateConversion.sqlDateToCalendar(rs.getString("data_inicio")));
                task.setDataTermino(DateConversion.sqlDateToCalendar(rs.getString("data_termino")));
                task.setPrioridade(rs.getInt("prioridade"));
                task.setDone((rs.getInt("is_done") != 0));
                return task;
            } else{
                return null;
            }
        } catch (SQLException | DateConversionException | TarefaDateException | NullPointerException | TarefaPrioridadeException ex) {
            Logger.getLogger(TarefaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally{
            ConnectionFatory.closeConnection(conn, stmt, rs);
        }
        
        
    }

    @Override
    public void update(Tarefa task) throws SQLException {
        Connection conn = ConnectionFatory.getConnection();
        String sql = "UPDATE tarefa SET title=?, description=?, data_inicio=?, data_termino=?,"
                + " prioridade=?, is_done=? WHERE id=? ";
        PreparedStatement stmt =null;
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, task.getTitle());
            stmt.setString(2, task.getDescription());
            stmt.setString(3, DateConversion.calendarToDateSQL(task.getDataInicio()));
            stmt.setString(4, DateConversion.calendarToDateSQL(task.getDataTermino()));
            stmt.setInt(5, task.getPrioridade());
            stmt.setInt(6, (task.getDone())?1:0 );// tru e false sqlite3
            stmt.setInt(7, task.getId());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(TarefaDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new SQLException("Erro: "+ex);
        } finally{
            ConnectionFatory.closeConnection(conn, stmt);
        }  
    }

    @Override
    public void removeById(int id) throws SQLException{
        Connection conn = ConnectionFatory.getConnection();
        String sql = "DELETE FROM tarefa where id = ?";
        PreparedStatement stmt =null;
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.executeUpdate();  
        } catch (SQLException ex) {
            Logger.getLogger(TarefaDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new SQLException("Erro: "+ex);
        } finally{
            ConnectionFatory.closeConnection(conn, stmt);
        }
    }
    
    
   
}
