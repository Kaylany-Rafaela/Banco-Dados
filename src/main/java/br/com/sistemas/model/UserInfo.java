package br.com.sistemas.model;

import br.com.sistemas.login.ConexaoBDPostgres;

public class UserInfo {
    
    private String username, senha;

    public UserInfo(String username, String senha) {
        this.username = username;
        this.senha = senha;
    }

    public String getUsername() {
        return username;
    }

    public void setUsernanme(String username) {
        this.username = username;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }  
}
