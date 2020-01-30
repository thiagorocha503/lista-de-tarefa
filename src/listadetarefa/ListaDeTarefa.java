/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package listadetarefa;

import java.io.File;
import javax.swing.JOptionPane;
import view.JanelaListagem;

/**
 *
 * @author thiago
 */
public class ListaDeTarefa {
    
    
    
    public static void verificaPastaData(){
        String path = new File("").getAbsolutePath()+"\\data";
        File data = new File(path);
        if(!data.exists()){
            data.mkdirs();
        } 
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JanelaListagem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        verificaPastaData();
        JanelaListagem winListagem;
        winListagem = new JanelaListagem();
        try{
            winListagem.initilazeTable();
            winListagem.setVisible(true);
        } catch(Exception ex){
            JOptionPane.showMessageDialog(null,"Erro inesperado: "+ex,"Erro",JOptionPane.ERROR_MESSAGE);
            winListagem.dispose();
        }
        
    }
    
}
