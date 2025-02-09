/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package br.com.sistemas.userInterface;

import br.com.sistemas.model.database.ConexaoBDPostgres;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Bastos
 */
    
public class TelaCadastroProduto extends javax.swing.JFrame {

    ConexaoBDPostgres conexaoBD;
    public TelaCadastroProduto(ConexaoBDPostgres conexaoBD) {
    this.conexaoBD = conexaoBD;
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

        buttonAdicionar = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabelFornecedor = new javax.swing.JLabel();
        jLabelPreco = new javax.swing.JLabel();
        jTextFieldDescricaoProduto = new javax.swing.JTextField();
        jTextFieldValorProduto = new javax.swing.JTextField();
        jTextFieldQuantidadeProduto = new javax.swing.JTextField();
        jLabelQuantidade = new javax.swing.JLabel();
        jLabelDescricao = new javax.swing.JLabel();
        jTextFieldIdFornecedorProduto = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cadastro de Produtos");
        setResizable(false);

        buttonAdicionar.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        buttonAdicionar.setText("Cadastrar Produto");
        buttonAdicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAdicionarActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        jLabelFornecedor.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelFornecedor.setText("ID Fornecedor");

        jLabelPreco.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelPreco.setText("Valor");

        jTextFieldDescricaoProduto.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        jTextFieldValorProduto.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        jTextFieldQuantidadeProduto.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        jLabelQuantidade.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelQuantidade.setText("Quantidade");

        jLabelDescricao.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelDescricao.setText("Descrição");

        jTextFieldIdFornecedorProduto.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabelQuantidade)
                    .addComponent(jLabelPreco)
                    .addComponent(jLabelDescricao)
                    .addComponent(jLabelFornecedor)
                    .addComponent(jTextFieldIdFornecedorProduto)
                    .addComponent(jTextFieldDescricaoProduto)
                    .addComponent(jTextFieldValorProduto)
                    .addComponent(jTextFieldQuantidadeProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelQuantidade)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldQuantidadeProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelPreco)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldValorProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelDescricao)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldDescricaoProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelFornecedor)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldIdFornecedorProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buttonAdicionar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonAdicionar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents


    private void buttonAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAdicionarActionPerformed

        Connection conexao = conexaoBD.getConexao();

        // Pegando os valores dos campos de texto
        String descricaoProduto = jTextFieldDescricaoProduto.getText();
        double valorProduto;
        int quantidadeProduto;
        int idFornecedorProduto;

        // Verificação de tipo das entradas
        try {
            valorProduto = Double.parseDouble(jTextFieldValorProduto.getText());
            quantidadeProduto = Integer.parseInt(jTextFieldQuantidadeProduto.getText());
            idFornecedorProduto = Integer.parseInt(jTextFieldIdFornecedorProduto.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Erro: Certifique-se de que o preço e a quantidade são números válidos.");
            return;
        }

        String sql = "CALL insert_tb_produtos('" + descricaoProduto + "', '" + valorProduto + "', '" 
                + quantidadeProduto + "', '" + idFornecedorProduto + "')";

        try (PreparedStatement ps = conexao.prepareStatement(sql)) {
            ps.executeUpdate();
            JOptionPane.showMessageDialog(this, "✅ Produto " + descricaoProduto + " inserido com sucesso!");
            ps.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }//GEN-LAST:event_buttonAdicionarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonAdicionar;
    private javax.swing.JLabel jLabelDescricao;
    private javax.swing.JLabel jLabelFornecedor;
    private javax.swing.JLabel jLabelPreco;
    private javax.swing.JLabel jLabelQuantidade;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTextFieldDescricaoProduto;
    private javax.swing.JTextField jTextFieldIdFornecedorProduto;
    private javax.swing.JTextField jTextFieldQuantidadeProduto;
    private javax.swing.JTextField jTextFieldValorProduto;
    // End of variables declaration//GEN-END:variables
}
