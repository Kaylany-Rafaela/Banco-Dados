/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package br.com.sistemas.userInterface;

import javax.swing.table.DefaultTableModel;
import br.com.sistemas.model.database.ConexaoBDPostgres;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import javax.swing.JOptionPane;

/**
 *
 * @author user
 */
public class TelaHistoricoVenda extends javax.swing.JFrame {

    /**
     * Creates new form TelaHistoricoVenda
     */
    
    ConexaoBDPostgres conexao;
    DefaultTableModel modelo = new DefaultTableModel(new Object [][] { },
            new String [] { "Código", "Horário", "Valor Total" , "Funcionário"});
    
    public TelaHistoricoVenda(ConexaoBDPostgres conexao) {
        this.conexao = conexao;
        initComponents();
        carregarVendas();
    }

    public final void carregarVendas() {
        String sql = "SELECT tb_vendas.ven_codigo, tb_vendas.ven_horario, tb_vendas.ven_valor_total, view_funcionarios_login.fun_nome FROM tb_vendas "
                + "LEFT JOIN view_funcionarios_login ON tb_funcionarios_fun_codigo = fun_codigo;";
        try (PreparedStatement ps = conexao.getConexao().prepareStatement(sql);
            ResultSet rs = ps.executeQuery()) {
                while(modelo.getRowCount() > 0) {
                    modelo.removeRow(0);
                }
                while (rs.next()) {
                    Vector linha = new Vector();
                    linha.add(rs.getInt("ven_codigo"));
                    linha.add(rs.getString("ven_horario"));
                    linha.add(rs.getString("ven_valor_total"));
                    linha.add(rs.getString("fun_nome"));
                    modelo.addRow(linha);
                }
                ps.close();
        } catch(SQLException e){
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jTable1.setModel(modelo);
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 737, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 432, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
