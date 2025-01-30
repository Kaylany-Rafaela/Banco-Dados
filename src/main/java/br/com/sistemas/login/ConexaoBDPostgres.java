package br.com.sistemas.login;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoBDPostgres {

    private static final String URL = "jdbc:postgresql://localhost:5432/trabalhobd2"; // Nome do banco fixo
    private static final String USUARIO = "postgres";
    private static final String SENHA = "postgres";
    
    private Connection conexao;

    // Construtor vazio - conexão não é aberta automaticamente
    public ConexaoBDPostgres() {
        this.conexao = null;
    }

    // Método para conectar ao banco de dados
    public boolean conectar() {
        try {
            // Registrar o driver (opcional em versões mais novas do Java)
            Class.forName("org.postgresql.Driver");
            
            // Estabelecer conexão
            this.conexao = DriverManager.getConnection(URL, USUARIO, SENHA);
            System.out.println("✅ Conexão realizada com sucesso!");
            return true;
        } catch (ClassNotFoundException e) {
            System.out.println("❌ Driver do PostgreSQL não encontrado.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("❌ Erro ao conectar ao banco de dados.");
            e.printStackTrace();
        }
        return false;
    }

    // Método para obter a conexão
    public Connection getConexao() {
        return this.conexao;
    }

    // Método para fechar a conexão
    public void desconectar() {
        if (this.conexao != null) {
            try {
                this.conexao.close();
                System.out.println("🔌 Conexão fechada com sucesso.");
            } catch (SQLException e) {
                System.out.println("⚠️ Erro ao fechar a conexão.");
                e.printStackTrace();
            }
        }
    }
}
