/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.tabelModel.TarefaTabelModel;

/**
 *
 * @author thiago
 */
public class ControllerTest {
    
    public static void main(String[] args) {
        TarefaController c = new TarefaController(new TarefaTabelModel());
        String titulo="titulo", descricao="texto", dataInicio="14/01/2020", dataTermino="21/01/2020",prioridade="ALTA";
        c.inserir(titulo, descricao, dataInicio, dataTermino, prioridade, true);
    }
    
}
