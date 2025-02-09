package br.com.sistemas.model.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

/**
 * Classe responsável por gerenciar a conexão com o banco de dados PostgreSQL.
 */
public class ConexaoBDPostgres {
    private Connection conexao;
    private static final String DRIVER = "org.postgresql.Driver";
    private static final String HOST = "localhost";
    private static final int PORTA = 5432;
    
    protected String cpf, senha, nomeBanco;
    
    public ConexaoBDPostgres(String cpf, String senha, String nomeBanco){
        this.cpf = cpf;
        this.senha = senha;
        this.nomeBanco = nomeBanco;
    }
    
    public boolean conectar() {
        
        String url = "jdbc:postgresql://" + HOST + ":" + PORTA + "/" + nomeBanco;
        
        try {
            Class.forName(DRIVER);
            // Conexao sem prefixo para admins
            if(cpf.equals("postgres") || cpf.equals("admin")){
                conexao = DriverManager.getConnection(url, cpf, senha);
            } else{
                conexao = DriverManager.getConnection(url, "u" + cpf + "u", senha);
            }
            
            return true;
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Erro: Driver JDBC não encontrado!", "Erro de Conexão", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao conectar ao banco: " + e.getMessage(), "Erro de Conexão", JOptionPane.ERROR_MESSAGE);
        }
        
        return false;
    }
    
    /**
     * Obtém a conexão atual com o banco de dados.
     * 
     * @return Objeto Connection representando a conexão ativa.
     */
    public Connection getConexao() {
        return conexao;
    }

    public String getCPF(){
        return this.cpf;
    }
    // Retorna a funcao do usuário com o cpf informado
    public String getCargo(String cpf){
        String cargo = "nulo";
        String sql = "SELECT fun_funcao FROM view_funcionarios_login WHERE fun_cpf = '" + cpf + "';";
        try 
        {
            PreparedStatement ps = conexao.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                cargo = rs.getString("fun_funcao");
            }
        }   catch(SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return cargo;
    }

    /**
     * Método responsável por fechar a conexão com o banco de dados.
     */
    public void disconnect() {
        if (conexao != null) {
            try {
                conexao.close();
                System.out.println("Conexão fechada com sucesso.");
            } catch (SQLException erro) {
                System.out.println("Erro ao fechar a conexão: " + erro.getMessage());
            }
        }
    }
}
