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
    
    /**
     * Atributo que armazena a conexão ativa com o banco de dados.
     * Protegido contra acessos externos diretos.
     */
    private Connection conexao;
    
    /**
     * Nome do driver JDBC do PostgreSQL, necessário para carregar a classe do driver.
     */
    private static final String DRIVER = "org.postgresql.Driver";
    
    /**
     * Define o endereço do servidor de banco de dados.
     */
    private static final String HOST = "localhost";
    
    /**
     * Número da porta padrão do PostgreSQL (5432).
     */
    private static final int PORTA = 5432;
    
    /**
     * Método responsável por estabelecer a conexão com o banco de dados.
     * 
     * @param cpf Nome do usuário do banco de dados.
     * @param senha Senha do usuário do banco de dados.
     * @param nomeBanco Nome do banco de dados a ser acessado.
     * @return true se a conexão for bem-sucedida, false caso contrário.
     */
    public boolean conectar(String cpf, String senha, String nomeBanco) {
        
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

    // Retorna a funcao do usuário com o cpf informado
    public String getFuncao(String cpf){
        String funcao = "nulo";
        String sql = "SELECT fun_funcao FROM tb_funcionarios WHERE fun_cpf = '" + cpf + "';";
        try (PreparedStatement ps = conexao.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                funcao = rs.getString("fun_funcao");
            }
        }   catch(SQLException e){
            funcao = "SQLException";
        }
        return funcao;
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
