/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.thiagorocha503.listadetarefa.model.tabelModel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;
import io.github.thiagorocha503.listadetarefa.model.bean.Tarefa;
import io.github.thiagorocha503.listadetarefa.util.DateConversion;
import io.github.thiagorocha503.listadetarefa.util.Observer;
import io.github.thiagorocha503.listadetarefa.model.bean.exception.TarefaDateException;
import io.github.thiagorocha503.listadetarefa.model.bean.exception.TarefaPrioridadeException;
import io.github.thiagorocha503.listadetarefa.util.SubjectTabel;

/**
 *
 * @author thiago
 */
public class TarefaTabelModel extends AbstractTableModel implements SubjectTabel {

    private final ArrayList<Tarefa> tarefas = new ArrayList<>();
    private final String[] columns = {"id", "nome", "descrição", "data de ínicio", "data de termino", "prioridade", "Concluido"};
    private final ArrayList<Observer> observers = new ArrayList<>();

    public void add(Tarefa task) throws TarefaDateException, TarefaPrioridadeException {
        this.tarefas.add(task);
        this.fireTableDataChanged();

    }

    public void updateTable(ArrayList<Tarefa> tarefas) {
        this.tarefas.clear();
        // for in
        tarefas.forEach((task) -> {
            this.tarefas.add(task);
        });
        this.fireTableDataChanged();
    }

    public void cleanTable() {
        this.tarefas.clear();
        this.fireTableDataChanged();
    }

    public void remover(int row) {
        this.tarefas.remove(row);
        this.fireTableRowsDeleted(row, row);
    }

    @Override
    public Class<?> getColumnClass(int i) {
        if (i == 0) {
            return Integer.class;
        } else if (i >= 1 && i <= 5) {
            return String.class;
        } else if (i == 6) {
            return Boolean.class;
        }
        throw new RuntimeException("Valor inválido de coluna: " + i);
    }

    @Override
    public String getColumnName(int i) {
        return this.columns[i];
    }

    @Override
    public int getRowCount() {
        return this.tarefas.size();
    }

    @Override
    public int getColumnCount() {
        return this.columns.length;
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return column == 6;// if column == 6? true: false;

    }

    @Override
    public Object getValueAt(int row, int column) {
        switch (column) {
            case 0:
                return this.tarefas.get(row).getId();
            case 1:
                return this.tarefas.get(row).getTitle();
            case 2:
                return this.tarefas.get(row).getDescription();
            case 3:
                return DateConversion.calendarToDateFormt(this.tarefas.get(row).getDataInicio());
            case 4:
                return DateConversion.calendarToDateFormt(this.tarefas.get(row).getDataTermino());
            case 5:
                return getPrioridadeText(this.tarefas.get(row).getPrioridade());
            case 6:
                return this.tarefas.get(row).getDone();
            default:
                throw new RuntimeException("valor inválido: {row: " + row + ", column: " + column + "}");
        }
    }

    public String getPrioridadeText(int prioridade) {
        switch (prioridade) {
            case 1:
                return "ALTA";
            case 2:
                return "NORMAL";
            case 3:
                return "BAIXA";
            default:
                throw new RuntimeException("Prioridade inválida");
        }

    }

    @Override
    public void setValueAt(Object o, int row, int column) {
        switch (column) {
            case 0:
                this.tarefas.get(row).setId(Integer.parseInt(o.toString()));
                break;
            case 1:
                this.tarefas.get(row).setTitle(o.toString());
                break;
            case 2:
                this.tarefas.get(row).getDescription();
                break;
            case 3:
                this.tarefas.get(row).setDataInicio((Calendar) o);
                break;
            case 4: {
                try {
                    this.tarefas.get(row).setDataTermino((Calendar) o);
                } catch (TarefaDateException | NullPointerException ex) {
                    Logger.getLogger(TarefaTabelModel.class.getName()).log(Level.SEVERE, null, ex);
                    throw new RuntimeException("Erro: " + ex);
                }
            }
            break;
            case 5: {
                try {
                    this.tarefas.get(row).setPrioridade(Integer.parseInt(o.toString()));
                } catch (TarefaPrioridadeException ex) {
                    Logger.getLogger(TarefaTabelModel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            break;
            case 6:
                this.tarefas.get(row).setDone((Boolean) o);
                this.notifyObserver(row, column);
                break;
            default:
                throw new RuntimeException("valor inválido: {row: " + row + ", column: " + column + "}");
        }
        this.fireTableRowsUpdated(row, column);
    }

    // métodos de subject
    @Override
    public void addObserver(Observer o) {
        this.observers.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        this.observers.remove(o);
    }

    @Override
    public void notifyObserver(int row, int column) {
        this.observers.forEach((o) -> {
            Map<String, Object> valores = new HashMap<>();
            int id = this.tarefas.get(0).getId();
            valores.put("id", this.tarefas.get(row).getId());
            valores.put("done", this.tarefas.get(row).getDone());
            o.update(valores);
        });
    }

}
