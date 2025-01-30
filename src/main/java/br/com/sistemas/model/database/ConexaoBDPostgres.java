package br.com.sistemas.model.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
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
     * @param usuario Nome do usuário do banco de dados.
     * @param senha Senha do usuário do banco de dados.
     * @param nomeBanco Nome do banco de dados a ser acessado.
     * @return true se a conexão for bem-sucedida, false caso contrário.
     */
    public boolean conectar(String usuario, String senha, String nomeBanco) {
        if (!validarEntradas(usuario, senha)) {
            return false;
        }
        
        String url = "jdbc:postgresql://" + HOST + ":" + PORTA + "/" + nomeBanco;
        
        try {
            Class.forName(DRIVER);
            conexao = DriverManager.getConnection(url, usuario, senha);
            return true;
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Erro: Driver JDBC não encontrado!", "Erro de Conexão", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao conectar ao banco: " + e.getMessage(), "Erro de Conexão", JOptionPane.ERROR_MESSAGE);
        }
        
        return false;
    }
    
    /**
     * Método privado para validar as credenciais antes de tentar conectar.
     * 
     * @param usuario Nome do usuário.
     * @param senha Senha do usuário.
     * @return true se as credenciais forem válidas, false caso contrário.
     */
    private boolean validarEntradas(String usuario, String senha) {
        if (usuario.isBlank() && senha.isBlank()) {
            JOptionPane.showMessageDialog(null, "Erro: insira um usuário e uma senha", "Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        } else if (usuario.isBlank()) {
            JOptionPane.showMessageDialog(null, "Erro: insira um usuário", "Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        } else if (senha.isBlank()) {
            JOptionPane.showMessageDialog(null, "Erro: insira uma senha", "Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }
    
    /**
     * Obtém a conexão atual com o banco de dados.
     * 
     * @return Objeto Connection representando a conexão ativa.
     */
    public Connection getConexao() {
        return conexao;
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
