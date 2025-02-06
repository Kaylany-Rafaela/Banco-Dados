
package br.com.sistemas.userInterface;

import br.com.sistemas.model.database.ConexaoBDPostgres;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.util.Vector;

/**
 *
 * @author Bastos
 */
public class TelaEstoque extends javax.swing.JFrame {

    ConexaoBDPostgres conexao;
    DefaultTableModel modelo = new DefaultTableModel(new Object [][] { },
            new String [] { "Código", "Descrição", "Valor", "Quantidade", "ID Fornecedor" });
    
    public TelaEstoque(ConexaoBDPostgres conexao) {
        this.conexao = conexao;
        initComponents();
        carregarProdutos();
    }
    public final void carregarProdutos() {
        String busca = jTextFieldBuscarProduto.getText();
        String sql = "SELECT * FROM tb_produtos WHERE pro_descricao LIKE '" + busca + "%';";
        try  (PreparedStatement ps = conexao.getConexao().prepareStatement(sql);
            ResultSet rs = ps.executeQuery()) {
                while(modelo.getRowCount() > 0) {
                    modelo.removeRow(0);
                }
                while (rs.next()) {
                    Vector linha = new Vector();
                    linha.add(rs.getInt("pro_codigo"));
                    linha.add(rs.getString("pro_descricao"));
                    linha.add(rs.getDouble("pro_valor"));
                    linha.add(rs.getInt("pro_quantidade"));
                    linha.add(rs.getInt("tb_fornecedores_for_codigo"));
                    modelo.addRow(linha);
                }
            ps.close();
        } catch(SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
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
        tabelaProdutosEstoque = new javax.swing.JTable();
        buttonAdicionar = new javax.swing.JButton();
        buttonBuscar = new javax.swing.JButton();
        jTextFieldBuscarProduto = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        buttonRemover = new javax.swing.JButton();
        jButtonAtualizar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        setSize(new java.awt.Dimension(800, 600));

        tabelaProdutosEstoque.setModel(modelo);
        tabelaProdutosEstoque.setEnabled(false);
        jScrollPane1.setViewportView(tabelaProdutosEstoque);

        buttonAdicionar.setText("Adicionar");
        buttonAdicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAdicionarActionPerformed(evt);
            }
        });

        buttonBuscar.setText("Buscar");
        buttonBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonBuscarActionPerformed(evt);
            }
        });

        jTextFieldBuscarProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldBuscarProdutoActionPerformed(evt);
            }
        });

        jLabel1.setText("Buscar:");

        buttonRemover.setText("Remover");
        buttonRemover.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRemoverActionPerformed(evt);
            }
        });

        jButtonAtualizar.setText("Atualizar");
        jButtonAtualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAtualizarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jTextFieldBuscarProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 463, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonBuscar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonAtualizar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonAdicionar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonRemover, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(31, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldBuscarProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonBuscar)
                    .addComponent(buttonAdicionar)
                    .addComponent(buttonRemover)
                    .addComponent(jButtonAtualizar))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jTextFieldBuscarProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldBuscarProdutoActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jTextFieldBuscarProdutoActionPerformed

    private void buttonBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonBuscarActionPerformed
        carregarProdutos();
    }//GEN-LAST:event_buttonBuscarActionPerformed

    private void buttonAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAdicionarActionPerformed
        TelaCadastroProduto telaAdicionarProduto = new TelaCadastroProduto(conexao);
        telaAdicionarProduto.setVisible(true);
    }//GEN-LAST:event_buttonAdicionarActionPerformed

    private void buttonRemoverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRemoverActionPerformed
        TelaRemocaoProduto telaRemocaoProduto = new TelaRemocaoProduto(conexao);
        telaRemocaoProduto.setVisible(true);
    }//GEN-LAST:event_buttonRemoverActionPerformed

    private void jButtonAtualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAtualizarActionPerformed
        TelaAtualizarEstoque telaAtualizarEstoque = new TelaAtualizarEstoque(conexao);
        telaAtualizarEstoque.setVisible(true);
    }//GEN-LAST:event_jButtonAtualizarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonAdicionar;
    private javax.swing.JButton buttonBuscar;
    private javax.swing.JButton buttonRemover;
    private javax.swing.JButton jButtonAtualizar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextFieldBuscarProduto;
    private javax.swing.JTable tabelaProdutosEstoque;
    // End of variables declaration//GEN-END:variables
}
