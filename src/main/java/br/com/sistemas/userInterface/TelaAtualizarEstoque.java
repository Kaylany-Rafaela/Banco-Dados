
package br.com.sistemas.userInterface;

import br.com.sistemas.model.database.ConexaoBDPostgres;
import java.awt.Color;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Bastos
 */
public class TelaAtualizarEstoque extends javax.swing.JFrame {

    ConexaoBDPostgres conexao;
        DefaultTableModel modeloTabelaBuscaEstoque = new DefaultTableModel(new Object [][] { },
            new String [] { "Código", "Descrição", "Quantidade"});
    
    public TelaAtualizarEstoque(ConexaoBDPostgres conexao) {
        this.conexao = conexao;
        initComponents();
    }
    
    public final void carregarTabelaBuscaEstoque() {
        String busca = jTextFieldBuscar.getText();
        if(busca.isBlank()){
            jLabelMensagemErroCodigo.setForeground(Color.red);
        } else{
            jLabelMensagemErroCodigo.setForeground(new Color(214, 217, 223));
            String sql = "SELECT * FROM tb_produtos WHERE pro_codigo = " + busca + ";";
            try  (PreparedStatement ps = conexao.getConexao().prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {
                    while(modeloTabelaBuscaEstoque.getRowCount() > 0) {
                        modeloTabelaBuscaEstoque.removeRow(0);
                    }
                    while (rs.next()) {
                        Vector linha = new Vector();
                        linha.add(rs.getInt("pro_codigo"));
                        linha.add(rs.getString("pro_descricao"));
                        linha.add(rs.getInt("pro_quantidade"));
                        modeloTabelaBuscaEstoque.addRow(linha);
                    }
                    ps.close();
            } catch(SQLException e){
                JOptionPane.showMessageDialog(this, e.getMessage());
            }
        }
    }
    
    public void entradaDeProduto(){
        int quantidadeEntrada = (int)jSpinnerSeletorQuantidadeAdd.getValue();
        String sql = "CALL entrada_produto('" + (int)modeloTabelaBuscaEstoque.getValueAt(0, 0) + "', '" + quantidadeEntrada + "');";
        try{
            PreparedStatement ps = conexao.getConexao().prepareStatement(sql);
            ps.executeUpdate();
            ps.close();
            JOptionPane.showMessageDialog(this, "Atualizado com sucesso!");
        } catch (SQLException e){
            JOptionPane.showMessageDialog(this, "Erro" + e.getMessage());
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

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButtonAdicionar = new javax.swing.JButton();
        jTextFieldBuscar = new javax.swing.JTextField();
        jButtonBuscar = new javax.swing.JButton();
        jLabelMensagemErroCodigo = new javax.swing.JLabel();
        jSpinnerSeletorQuantidadeAdd = new javax.swing.JSpinner();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Atualização de Estoque");

        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setText("ID do Produto");

        jTable1.setModel(modeloTabelaBuscaEstoque);
        jTable1.setEnabled(false);
        jScrollPane1.setViewportView(jTable1);

        jButtonAdicionar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButtonAdicionar.setText("Adicionar");
        jButtonAdicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAdicionarActionPerformed(evt);
            }
        });

        jTextFieldBuscar.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jButtonBuscar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButtonBuscar.setText("Buscar");
        jButtonBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBuscarActionPerformed(evt);
            }
        });

        jLabelMensagemErroCodigo.setForeground(new java.awt.Color(214, 217, 223));
        jLabelMensagemErroCodigo.setText("Insira o ID do Produto");
        jLabelMensagemErroCodigo.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);

        jSpinnerSeletorQuantidadeAdd.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jSpinnerSeletorQuantidadeAdd.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSpinnerSeletorQuantidadeAddStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jSpinnerSeletorQuantidadeAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonAdicionar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jTextFieldBuscar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabelMensagemErroCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabelMensagemErroCodigo))
                .addGap(7, 7, 7)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonBuscar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jSpinnerSeletorQuantidadeAdd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonAdicionar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(10, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAdicionarActionPerformed
        if(modeloTabelaBuscaEstoque.getRowCount() == 0){
            jLabelMensagemErroCodigo.setForeground(Color.red);
        } else{
            entradaDeProduto();
            carregarTabelaBuscaEstoque();
        }
    }//GEN-LAST:event_jButtonAdicionarActionPerformed

    private void jSpinnerSeletorQuantidadeAddStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSpinnerSeletorQuantidadeAddStateChanged
        if((int)jSpinnerSeletorQuantidadeAdd.getValue() < 0){
            jSpinnerSeletorQuantidadeAdd.setValue(0);
        }
    }//GEN-LAST:event_jSpinnerSeletorQuantidadeAddStateChanged

    private void jButtonBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBuscarActionPerformed
        carregarTabelaBuscaEstoque();
    }//GEN-LAST:event_jButtonBuscarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAdicionar;
    private javax.swing.JButton jButtonBuscar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabelMensagemErroCodigo;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSpinner jSpinnerSeletorQuantidadeAdd;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextFieldBuscar;
    // End of variables declaration//GEN-END:variables
}
