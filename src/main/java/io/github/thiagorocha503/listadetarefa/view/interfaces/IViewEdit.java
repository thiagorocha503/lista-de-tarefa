/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.interfaces;

/**
 *
 * @author thiago
 */
public interface IViewEdit {
    
    void onAlterar();
    void onCancelar();
    void cleanField();
    void closeWindows();
    void showMessageInfo(String title, String info);
    void showMessageErro(String title, String erro);
}
