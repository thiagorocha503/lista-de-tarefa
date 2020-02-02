/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.thiagorocha503.listadetarefa.view;

import io.github.thiagorocha503.listadetarefa.presenter.TarefaAddPresenterImp;
import io.github.thiagorocha503.listadetarefa.presenter.interfaces.IPresenterAdd;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.showMessageDialog;
import io.github.thiagorocha503.listadetarefa.view.interfaces.IViewAdd;

/**
 *
 * @author thiago
 */
public class DialogNovaTarefa extends javax.swing.JDialog implements IViewAdd {

    private final IPresenterAdd presenter;

    /**
     * Creates new form DialogNovaTarefa
     *
     * @param parent
     * @param modal
     */
    @SuppressWarnings("LeakingThisInConstructor")
    public DialogNovaTarefa(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        this.presenter = new TarefaAddPresenterImp();
        this.presenter.setView(this);
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelContaiiner = new javax.swing.JPanel();
        panelForm = new javax.swing.JPanel();
        lblNome = new javax.swing.JLabel();
        txtNome = new javax.swing.JTextField();
        lblDescricao = new javax.swing.JLabel();
        scrllDescricao = new javax.swing.JScrollPane();
        txtDescricao = new javax.swing.JTextArea();
        lblDataInicio = new javax.swing.JLabel();
        lblDataTermino = new javax.swing.JLabel();
        lblPrioridade = new javax.swing.JLabel();
        comboBoxPrioridade = new javax.swing.JComboBox<>();
        txtDataInicio = new com.toedter.calendar.JDateChooser();
        txtDataTermino = new com.toedter.calendar.JDateChooser();
        checkDone = new javax.swing.JCheckBox();
        btnSalvar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Nova tarefa");
        setMinimumSize(new java.awt.Dimension(570, 390));
        setModal(true);
        setPreferredSize(new java.awt.Dimension(515, 400));

        panelForm.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        lblNome.setText("Nome");

        lblDescricao.setText("Descrição");

        txtDescricao.setColumns(20);
        txtDescricao.setRows(5);
        scrllDescricao.setViewportView(txtDescricao);

        lblDataInicio.setText("Data de ínicio");

        lblDataTermino.setText("Data máxima");

        lblPrioridade.setText("Prioridade");

        comboBoxPrioridade.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Normal", "Baixa", "Alta" }));

        checkDone.setText("concluído");
        checkDone.setContentAreaFilled(false);
        checkDone.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        checkDone.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        javax.swing.GroupLayout panelFormLayout = new javax.swing.GroupLayout(panelForm);
        panelForm.setLayout(panelFormLayout);
        panelFormLayout.setHorizontalGroup(
            panelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFormLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelFormLayout.createSequentialGroup()
                        .addGroup(panelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelFormLayout.createSequentialGroup()
                                .addGroup(panelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblNome)
                                    .addComponent(lblDescricao))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(txtNome)
                            .addComponent(scrllDescricao, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addContainerGap())
                    .addGroup(panelFormLayout.createSequentialGroup()
                        .addGroup(panelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(checkDone)
                            .addGroup(panelFormLayout.createSequentialGroup()
                                .addGroup(panelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panelFormLayout.createSequentialGroup()
                                        .addComponent(lblDataInicio)
                                        .addGap(6, 6, 6)
                                        .addComponent(txtDataInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(panelFormLayout.createSequentialGroup()
                                        .addComponent(lblPrioridade)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(comboBoxPrioridade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblDataTermino)
                                .addGap(12, 12, 12)
                                .addComponent(txtDataTermino, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        panelFormLayout.setVerticalGroup(
            panelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFormLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblNome)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblDescricao)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrllDescricao, javax.swing.GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE)
                .addGap(17, 17, 17)
                .addGroup(panelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblDataInicio)
                            .addComponent(txtDataTermino, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(txtDataInicio, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblDataTermino))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPrioridade)
                    .addComponent(comboBoxPrioridade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(checkDone)
                .addContainerGap())
        );

        btnSalvar.setText("Salvar");
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelContaiinerLayout = new javax.swing.GroupLayout(panelContaiiner);
        panelContaiiner.setLayout(panelContaiinerLayout);
        panelContaiinerLayout.setHorizontalGroup(
            panelContaiinerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelContaiinerLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelContaiinerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(panelForm, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelContaiinerLayout.createSequentialGroup()
                        .addGap(0, 351, Short.MAX_VALUE)
                        .addComponent(btnCancelar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSalvar)))
                .addContainerGap())
        );
        panelContaiinerLayout.setVerticalGroup(
            panelContaiinerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelContaiinerLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelForm, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelContaiinerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSalvar)
                    .addComponent(btnCancelar))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelContaiiner, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelContaiiner, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        // TODO add your handling code here:
        this.onSalve();
    }//GEN-LAST:event_btnSalvarActionPerformed

    public int getPrioridadeId(String text) {
        switch (text) {
            case "Alta":
                return 1;
            case "Normal":
                return 2;
            case "Baixa":
                return 3;
            default:
                throw new RuntimeException("Prioridade text inválido");
        }
    }

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
        this.onCancelar();
    }//GEN-LAST:event_btnCancelarActionPerformed

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
            java.util.logging.Logger.getLogger(DialogNovaTarefa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(() -> {
            DialogNovaTarefa dialog = new DialogNovaTarefa(new javax.swing.JFrame(), true);
            dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosing(java.awt.event.WindowEvent e) {
                    System.exit(0);
                }
            });
            dialog.setVisible(true);
        });
    }
    public boolean isFieldEmpty() {
        Boolean[] field = {this.txtNome.getText().equals(""),
            this.txtDescricao.getText().equals(""),
            this.txtDataInicio.getDate() == null,
            this.txtDataTermino.getDate() == null
        };
        for (Boolean isEmpty : field) {
            if (isEmpty) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void onSalve() {
        if (this.isFieldEmpty()) {
            showMessageDialog(null, "Preencha todos os campos corretamente", "Campo em branco ou inválidos", JOptionPane.WARNING_MESSAGE);
            return;
        }
        String titulo = this.txtNome.getText(), descricao = this.txtDescricao.getText();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String dataInicio = dateFormat.format(this.txtDataInicio.getDate());
        String dataTermino = dateFormat.format(this.txtDataTermino.getDate());
        int prioridade = getPrioridadeId(this.comboBoxPrioridade.getSelectedItem().toString());
        boolean done = this.checkDone.isSelected();
        this.presenter.onSalve(titulo, descricao, dataInicio, dataTermino, prioridade, done);
    }

    @Override
    public void onCancelar() {
        this.presenter.onCancelar();
    }

    @Override
    public void cleanField() {
        this.txtNome.setText("");
        this.txtDescricao.setText("");
        this.txtDataInicio.setDate(null);
        this.txtDataTermino.setDate(null);
        this.comboBoxPrioridade.setSelectedIndex(0);
        this.checkDone.setSelected(false);
    }

    @Override
    public void showMessageInfo(String info, String title) {
        JOptionPane.showMessageDialog(null, title, info, JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public void showMessageErro(String title, String erro) {
        JOptionPane.showMessageDialog(null, title, erro, JOptionPane.ERROR_MESSAGE);
    }

    @Override
    public void closeWindons() {
        this.dispose();
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JCheckBox checkDone;
    private javax.swing.JComboBox<String> comboBoxPrioridade;
    private javax.swing.JLabel lblDataInicio;
    private javax.swing.JLabel lblDataTermino;
    private javax.swing.JLabel lblDescricao;
    private javax.swing.JLabel lblNome;
    private javax.swing.JLabel lblPrioridade;
    private javax.swing.JPanel panelContaiiner;
    private javax.swing.JPanel panelForm;
    private javax.swing.JScrollPane scrllDescricao;
    private com.toedter.calendar.JDateChooser txtDataInicio;
    private com.toedter.calendar.JDateChooser txtDataTermino;
    private javax.swing.JTextArea txtDescricao;
    private javax.swing.JTextField txtNome;
    // End of variables declaration//GEN-END:variables
}