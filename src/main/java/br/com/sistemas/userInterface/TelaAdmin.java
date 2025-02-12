 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package br.com.sistemas.userInterface;

import br.com.sistemas.model.database.ConexaoBDPostgres;
import java.io.File;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

//registro de teste 123321
/**
 *
 * @author Bastos
 */
public class TelaAdmin extends javax.swing.JFrame {

    /**
     * Creates new form TelaHome
     */
    ConexaoBDPostgres conexao;
    DefaultTableModel modeloFuncionario = new DefaultTableModel(new Object [][] { },
            new String [] { "ID Funcionario", "Nome", "CPF", "Funcao" });
    
    DefaultTableModel modeloFornecedor = new DefaultTableModel(new Object [][] { },
            new String [] { "ID Fornecedor", "Nome" });
    
    public TelaAdmin(ConexaoBDPostgres conexao) {
        this.conexao = conexao;
        initComponents();
        carregarFuncionarios();
        carregarFornecedores();
    }

    public final void carregarFuncionarios() {
        String sql = "SELECT * FROM tb_funcionarios;";
        try (PreparedStatement ps = conexao.getConexao().prepareStatement(sql);
            ResultSet rs = ps.executeQuery()) {
                while(modeloFuncionario.getRowCount() > 0) {
                    modeloFuncionario.removeRow(0);
                }
                while (rs.next()) {
                    Vector linha = new Vector();
                    linha.add(rs.getInt("fun_codigo"));
                    linha.add(rs.getString("fun_nome"));
                    linha.add(rs.getString("fun_cpf"));
                    linha.add(rs.getString("fun_funcao"));
                    modeloFuncionario.addRow(linha);
                }
                ps.close();
        } catch(SQLException e){
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }
    
    public final void carregarFornecedores() {
        String sql = "SELECT * FROM tb_fornecedores;";
        try  (PreparedStatement ps = conexao.getConexao().prepareStatement(sql);
            ResultSet rs = ps.executeQuery()) {
                while(modeloFornecedor.getRowCount() > 0) {
                    modeloFornecedor.removeRow(0);
                }
                while (rs.next()) {
                    Vector linha = new Vector();
                    linha.add(rs.getInt("for_codigo"));
                    linha.add(rs.getString("for_descricao"));
                    modeloFornecedor.addRow(linha);
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

        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaMostrarFuncionarios = new javax.swing.JTable();
        jButtonCadastrarFuncionarios = new javax.swing.JButton();
        jButtonCadastrarFornecedores = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItemBackup = new javax.swing.JMenuItem();
        jMenuItemRefresh = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Admininstração");

        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        jTable1.setModel(modeloFornecedor);
        jTable1.setEnabled(false);
        jScrollPane2.setViewportView(jTable1);

        tabelaMostrarFuncionarios.setModel(modeloFuncionario);
        tabelaMostrarFuncionarios.setEnabled(false);
        tabelaMostrarFuncionarios.setFocusable(false);
        tabelaMostrarFuncionarios.setRowSelectionAllowed(false);
        jScrollPane1.setViewportView(tabelaMostrarFuncionarios);

        jButtonCadastrarFuncionarios.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButtonCadastrarFuncionarios.setText("Cadastrar Funcionários");
        jButtonCadastrarFuncionarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCadastrarFuncionariosActionPerformed(evt);
            }
        });

        jButtonCadastrarFornecedores.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButtonCadastrarFornecedores.setText("Cadastrar Fornecedores");
        jButtonCadastrarFornecedores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCadastrarFornecedoresActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 497, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonCadastrarFuncionarios))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonCadastrarFornecedores))
                .addContainerGap(15, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonCadastrarFuncionarios)
                    .addComponent(jButtonCadastrarFornecedores))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 524, Short.MAX_VALUE)
                    .addComponent(jScrollPane2))
                .addContainerGap())
        );

        jMenu1.setText("Sistema");

        jMenuItemBackup.setText("Backup");
        jMenuItemBackup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemBackupActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItemBackup);

        jMenuItemRefresh.setText("Refresh");
        jMenuItemRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemRefreshActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItemRefresh);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItemRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemRefreshActionPerformed
        carregarFuncionarios();
        carregarFornecedores();
    }//GEN-LAST:event_jMenuItemRefreshActionPerformed

    private void jMenuItemBackupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemBackupActionPerformed
        try {
            File backup = new File("backup_manual.bat");
            if(backup.isFile()){
                Runtime.getRuntime().exec(new String[] { "cmd.exe", "/c", "backup_manual.bat" } );
                JOptionPane.showMessageDialog(this, "Realizando backup!");
            } else{
                JOptionPane.showMessageDialog(this, "Erro! Batch file para backup não encontrado!");
            }        
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }//GEN-LAST:event_jMenuItemBackupActionPerformed

    private void jButtonCadastrarFuncionariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCadastrarFuncionariosActionPerformed
        TelaCadastroUsuario telaCadastroUsuario = new TelaCadastroUsuario(conexao);
        telaCadastroUsuario.setVisible(true);
    }//GEN-LAST:event_jButtonCadastrarFuncionariosActionPerformed

    private void jButtonCadastrarFornecedoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCadastrarFornecedoresActionPerformed
        TelaCadastroFornecedor telaCadastroFornecedor = new TelaCadastroFornecedor(conexao);
        telaCadastroFornecedor.setVisible(true);
    }//GEN-LAST:event_jButtonCadastrarFornecedoresActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonCadastrarFornecedores;
    private javax.swing.JButton jButtonCadastrarFuncionarios;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItemBackup;
    private javax.swing.JMenuItem jMenuItemRefresh;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable tabelaMostrarFuncionarios;
    // End of variables declaration//GEN-END:variables
}
