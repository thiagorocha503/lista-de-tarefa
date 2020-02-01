/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.thiagorocha503.listadetarefa.view;

import io.github.thiagorocha503.listadetarefa.presenter.TarefaListPresenter;
import io.github.thiagorocha503.listadetarefa.presenter.interfaces.IPresenterList;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import io.github.thiagorocha503.listadetarefa.model.tabelModel.TarefaTabelModel;
import io.github.thiagorocha503.listadetarefa.model.tabelModel.TarefaTabelRenderer;
import io.github.thiagorocha503.listadetarefa.view.interfaces.IViewLIst;

/**
 *
 * @author thiago
 */
public class JanelaListagem extends javax.swing.JFrame implements IViewLIst {

    /**
     * Creates new form JanelaListagem
     */
    //private final TarefaController controller;
    private final IPresenterList presenter;

    @SuppressWarnings("LeakingThisInConstructor")
    public JanelaListagem() {
        initComponents();
        TarefaTabelModel tarefaTabelModel = new TarefaTabelModel();
        this.tbTarefas.setModel(tarefaTabelModel);
        this.presenter = new TarefaListPresenter(tarefaTabelModel);
        this.presenter.setView(this);
        this.tbTarefas.setDefaultRenderer(Object.class, new TarefaTabelRenderer());

    }

    public void initilazeTable() {
        this.presenter.find("", 0);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelContainer = new javax.swing.JPanel();
        panelBusca = new javax.swing.JPanel();
        txtBusca = new javax.swing.JTextField();
        btnPesquisar = new javax.swing.JButton();
        lblFiltro = new javax.swing.JLabel();
        cbBox = new javax.swing.JComboBox<>();
        scrlTabel = new javax.swing.JScrollPane();
        tbTarefas = new javax.swing.JTable();
        btnNew = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnExit = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Lista de tarefas");
        setMinimumSize(new java.awt.Dimension(600, 400));

        panelBusca.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        txtBusca.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtBuscaKeyPressed(evt);
            }
        });

        btnPesquisar.setText("Buscar");
        btnPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPesquisarActionPerformed(evt);
            }
        });

        lblFiltro.setText("Filtro:");

        cbBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "não concluido", "concluido", "todos" }));

        javax.swing.GroupLayout panelBuscaLayout = new javax.swing.GroupLayout(panelBusca);
        panelBusca.setLayout(panelBuscaLayout);
        panelBuscaLayout.setHorizontalGroup(
            panelBuscaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBuscaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblFiltro)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtBusca, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnPesquisar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelBuscaLayout.setVerticalGroup(
            panelBuscaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBuscaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelBuscaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtBusca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPesquisar)
                    .addComponent(lblFiltro)
                    .addComponent(cbBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        tbTarefas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        scrlTabel.setViewportView(tbTarefas);

        btnNew.setText("Novo");
        btnNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewActionPerformed(evt);
            }
        });

        btnEdit.setText("Alterar");
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });

        btnDelete.setText("Excluir");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnExit.setText("Sair");
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelContainerLayout = new javax.swing.GroupLayout(panelContainer);
        panelContainer.setLayout(panelContainerLayout);
        panelContainerLayout.setHorizontalGroup(
            panelContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelContainerLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelBusca, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelContainerLayout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addGroup(panelContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(scrlTabel)
                            .addGroup(panelContainerLayout.createSequentialGroup()
                                .addComponent(btnNew)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnEdit)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnDelete)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 315, Short.MAX_VALUE)
                                .addComponent(btnExit)
                                .addGap(9, 9, 9)))))
                .addContainerGap())
        );
        panelContainerLayout.setVerticalGroup(
            panelContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelContainerLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelBusca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(scrlTabel, javax.swing.GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE)
                .addGap(13, 13, 13)
                .addGroup(panelContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEdit)
                    .addComponent(btnDelete)
                    .addComponent(btnExit)
                    .addComponent(btnNew)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelContainer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelContainer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(14, 14, 14))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewActionPerformed
        // TODO add your handling code here:
        this.onNovo();
    }//GEN-LAST:event_btnNewActionPerformed


    private void btnPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisarActionPerformed
        // TODO add your handling code here:
        //System.err.println("> " + this.cbBox.getSelectedIndex());
        this.onFind();
    }//GEN-LAST:event_btnPesquisarActionPerformed

    private void txtBuscaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscaKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            this.onFind();
        }
    }//GEN-LAST:event_txtBuscaKeyPressed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // TODO add your handling code here:
        this.onExcluir();


    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        this.onALterar();

    }//GEN-LAST:event_btnEditActionPerformed

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        // TODO add your handling code here:
        this.onSair();
    }//GEN-LAST:event_btnExitActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JanelaListagem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new JanelaListagem().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnNew;
    private javax.swing.JButton btnPesquisar;
    private javax.swing.JComboBox<String> cbBox;
    private javax.swing.JLabel lblFiltro;
    private javax.swing.JPanel panelBusca;
    private javax.swing.JPanel panelContainer;
    private javax.swing.JScrollPane scrlTabel;
    private javax.swing.JTable tbTarefas;
    private javax.swing.JTextField txtBusca;
    // End of variables declaration//GEN-END:variables

    @Override
    public void onFind() {
        int filtroSelecionado = this.cbBox.getSelectedIndex();
        this.presenter.find(this.txtBusca.getText(), this.cbBox.getSelectedIndex());
    }

    @Override
    public void onNovo() {
        this.presenter.adicionar();
    }

    @Override
    public void onExcluir() {
        this.presenter.excluir(this.tbTarefas.getSelectedRow());
    }

    @Override
    public void onALterar() {
        this.presenter.alterar(this.tbTarefas.getSelectedRow());
        
    }

    @Override
    public void onSair() {
        this.presenter.sair();
    }

    @Override
    public void closeWindown() {
        this.dispose();
    }
    @Override
    public boolean showConfirmDialogExcluir(){
        int result = JOptionPane.showConfirmDialog(null, "Deseja realmente remover a tarefa selecionada?", "Remoção", JOptionPane.YES_NO_OPTION);
        return result == 0;
    }
    
    @Override
    public void showMessageWarring(String title, String info) {
        JOptionPane.showMessageDialog(null, info, title, JOptionPane.WARNING_MESSAGE);
    }

    @Override
    public void showMessageErro(String title, String erro) {
        JOptionPane.showMessageDialog(null, erro, title, JOptionPane.ERROR_MESSAGE);
    }

    @Override
    public void showMessageInfo(String title, String info) {
        JOptionPane.showMessageDialog(null, info, title, JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public void openDialogEdit() {
        int row = this.tbTarefas.getSelectedRow();
        Map tarefaMap = new HashMap();
        tarefaMap.put("id", this.tbTarefas.getValueAt(row, 0));
        tarefaMap.put("title", this.tbTarefas.getValueAt(row, 1));
        tarefaMap.put("description", this.tbTarefas.getValueAt(row, 2));
        tarefaMap.put("dateStart", this.tbTarefas.getValueAt(row, 3));
        tarefaMap.put("dateEnd", this.tbTarefas.getValueAt(row, 4));
        tarefaMap.put("priority", this.tbTarefas.getValueAt(row, 5));
        tarefaMap.put("done", this.tbTarefas.getValueAt(row, 6));
        DialogAlterarTarefa dialogEdit = new DialogAlterarTarefa(this, true, tarefaMap);
        dialogEdit.setVisible(true);
        this.onFind();
    }

    @Override
    public void openDialogAdd() {
        DialogNovaTarefa dialogNovo = new DialogNovaTarefa(this, true);
        dialogNovo.setVisible(true);
        this.onFind();
    }
}
