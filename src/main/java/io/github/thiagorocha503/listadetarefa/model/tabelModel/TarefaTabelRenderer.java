/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.thiagorocha503.listadetarefa.model.tabelModel;

import java.awt.Color;
import java.awt.Component;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JTable;
import sun.swing.table.DefaultTableCellHeaderRenderer;
import io.github.thiagorocha503.listadetarefa.util.DateConversion;
import io.github.thiagorocha503.listadetarefa.util.exception.DateConversionException;
import io.github.thiagorocha503.listadetarefa.view.JanelaListagem;

/**
 *
 * @author thiago
 */
public class TarefaTabelRenderer extends DefaultTableCellHeaderRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable jtable, Object value, boolean isSelected, boolean bln1, int row, int column) {
        JLabel label = (JLabel) super.getTableCellRendererComponent(jtable, value, isSelected, bln1, row, column); //To change body of generated methods, choose Tools | Templates.
        Calendar hoje = Calendar.getInstance();
        Calendar prazo = null;
        if (isSelected) {
            label.setBackground(jtable.getSelectionBackground());
            return label;
        }
        try {
            prazo = DateConversion.dateFormtToCalendar(jtable.getValueAt(row, 4).toString());
        } catch (DateConversionException ex) {
            Logger.getLogger(JanelaListagem.class.getName()).log(Level.SEVERE, null, ex);
        }
        Color cor = new Color(231, 76, 76); //#e74c3c
        if (hoje.compareTo(prazo) > 0 && !(boolean) jtable.getValueAt(row, 6)) {
            label.setBackground(cor);
            return label;
        }
        return label;
    }

}
