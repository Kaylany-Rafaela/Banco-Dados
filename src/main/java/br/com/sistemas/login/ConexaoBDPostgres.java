
package br.com.sistemas.login;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;


public class ConexaoBDPostgres {
    private String url;
    private String usuario;
    private String senha;
    private String nomeBanco;
    private Connection conexao;
    private boolean status;

    public int ConectarAoBanco(String user, String senha, String nomeBanco) {
        this.usuario = user;
        this.senha = senha;
        this.nomeBanco = nomeBanco;        
          
         url = "jdbc:postgresql://localhost:5432/"+nomeBanco;         
         
        try {
            Class.forName("org.postgresql.Driver");
            conexao = DriverManager.getConnection(url, usuario, senha);
            return 0;

        } catch (ClassNotFoundException | SQLException e) {
            return -1;
        }
        
    } 

    public Connection getConexao() {
        return conexao;
    }

    public ConexaoBDPostgres (){}
    
   
    public void disconnect() {
        try {
            conexao.close();
            status = false;
            System.out.println("Fechando a conexão");
        } catch (SQLException erro) {
            System.out.println("Erro no fechamento");

        }
    }
}
