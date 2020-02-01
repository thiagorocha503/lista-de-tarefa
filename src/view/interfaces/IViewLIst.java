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
public interface IViewLIst {
    
    void onFind();
    void onNovo();
    void onExcluir();
    void onALterar();
    void onSair();
    void closeWindown();
    void showMessageWarring(String title, String warring);
    void showMessageErro(String title, String erro);
    void showMessageInfo(String title, String info);
    
}
