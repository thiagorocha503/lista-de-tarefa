/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presenter;

import presenter.interfaces.IPresenterAdd;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.bean.Tarefa;
import model.bean.exception.TarefaDateException;
import model.bean.exception.TarefaPrioridadeException;
import service.TarefaService;
import util.DateConversion;
import util.exception.DateConversionException;
import view.interfaces.IViewAdd;

/**
 *
 * @author thiago
 */
public class TarefaAddPresenterImp implements IPresenterAdd {

    private Tarefa task;
    public IViewAdd view;

    @Override
    public void onSalve(String titulo, String descricao, String dataInicio, String dataTermino, int prioridade, boolean done) {
        {
            task = new Tarefa();
            task.setTitle(titulo);
            task.setDescription(descricao);
            try {
                task.setDataInicio(DateConversion.dateFormtToCalendar(dataInicio));
                task.setDataTermino(DateConversion.dateFormtToCalendar(dataTermino));
                task.setPrioridade(prioridade);
                task.setDone(done);
                // Service DAO
                TarefaService service = new TarefaService();
                service.insert(task);
                this.view.cleanField();
                this.view.showMessageInfo("Sucesso", "Tarefa salva com sucesso!");
            } catch (DateConversionException ex) {
                Logger.getLogger(TarefaAddPresenterImp.class.getName()).log(Level.SEVERE, null, ex);
                this.view.showMessageErro("Data inválida", "Data inválida");
            } catch (TarefaDateException ex) {
                Logger.getLogger(TarefaAddPresenterImp.class.getName()).log(Level.SEVERE, null, ex);
                this.view.showMessageErro("Datas inválida", "Datas de termino menor que data de inicio");
            } catch (NullPointerException ex) {
                Logger.getLogger(TarefaAddPresenterImp.class.getName()).log(Level.SEVERE, null, ex);
                this.view.showMessageErro("Erro", "Erro: " + ex);
            } catch (SQLException ex) {
                Logger.getLogger(TarefaAddPresenterImp.class.getName()).log(Level.SEVERE, null, ex);
                this.view.showMessageErro("Banco de dados", "Erro: " + ex);
            } catch (TarefaPrioridadeException ex) {
                Logger.getLogger(TarefaAddPresenterImp.class.getName()).log(Level.SEVERE, null, ex);
                this.view.showMessageErro("Prioridade inválida", "Prioridade inserida inválida");
            } catch (Exception ex) {
                Logger.getLogger(TarefaAddPresenterImp.class.getName()).log(Level.SEVERE, null, ex);
                this.view.showMessageErro("Erro", "Erro inesperado: " + ex);
            }

        }
    }

    @Override
    public void onCancelar() {
        this.view.closeWindons();

    }

    @Override
    public void setView(IViewAdd view) {
        this.view = view;
    }

}
