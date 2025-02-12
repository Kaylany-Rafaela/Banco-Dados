-- Database: BancoTrabalho2

-- DROP DATABASE IF EXISTS "BancoTrabalho2";

CREATE DATABASE "BancoTrabalho2"
    WITH
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'Portuguese_Brazil.1252'
    LC_CTYPE = 'Portuguese_Brazil.1252'
    LOCALE_PROVIDER = 'libc'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1
    IS_TEMPLATE = False;

DROP TABLE IF EXISTS tb_fornecedores cascade;
DROP TABLE IF EXISTS tb_produtos cascade;
DROP TABLE IF EXISTS tb_funcionarios cascade;
DROP TABLE IF EXISTS tb_vendas cascade;
DROP TABLE IF EXISTS tb_itens cascade;

-- Criação das Tabelas --

	-- Tabela de Fornecedores
CREATE TABLE tb_fornecedores (
    for_codigo BIGINT PRIMARY KEY,
    for_descricao VARCHAR(45) NOT NULL
);

	-- Tabela de Produtos
CREATE TABLE tb_produtos (
    pro_codigo BIGINT PRIMARY KEY,
    pro_descricao VARCHAR(45) NOT NULL,
    pro_valor DECIMAL(10, 2) NOT NULL,
    pro_quantidade INT NOT NULL,
    tb_fornecedores_for_codigo BIGINT NOT NULL,
    FOREIGN KEY (tb_fornecedores_for_codigo) REFERENCES tb_fornecedores (for_codigo)
);

	-- Tabela de Funcionários
CREATE TABLE tb_funcionarios (
    fun_codigo BIGINT PRIMARY KEY,
    fun_nome VARCHAR(45) NOT NULL,
    fun_cpf VARCHAR(45) NOT NULL UNIQUE,
    fun_senha VARCHAR(50) NOT NULL,
    fun_funcao VARCHAR(50) NOT NULL
);

	-- Tabela de Vendas
CREATE TABLE tb_vendas (
    ven_codigo BIGINT PRIMARY KEY,
    ven_horario TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    ven_valor_total DECIMAL(10, 2) NOT NULL,
    tb_funcionarios_fun_codigo BIGINT NOT NULL,
    FOREIGN KEY (tb_funcionarios_fun_codigo) REFERENCES tb_funcionarios (fun_codigo)
);

	-- Tabela de Itens
CREATE TABLE tb_itens (
    ite_codigo BIGINT PRIMARY KEY,
    ite_quantidade INT NOT NULL,
    ite_valor_parcial DECIMAL(10, 2) NOT NULL,
    tb_produtos_pro_codigo BIGINT NOT NULL,
    tb_vendas_ven_codigo BIGINT NOT NULL,
    FOREIGN KEY (tb_produtos_pro_codigo) REFERENCES tb_produtos (pro_codigo),
    FOREIGN KEY (tb_vendas_ven_codigo) REFERENCES tb_vendas (ven_codigo)
);

CREATE OR REPLACE VIEW view_funcionarios_login 
AS SELECT fun_codigo, fun_nome, fun_cpf, fun_funcao FROM tb_funcionarios
WITH CHECK OPTION;

-- Criação de Indexação --

	-- Índice de Buscas por Descrição de Produtos: 
-- Justificativa: Facilita buscas frequentes por fornecedores pelo nome ou descrição.
CREATE INDEX idx_fornecedores_descricao ON tb_fornecedores (for_descricao);

	-- Índice de Buscas para filtrar Produtos com uma faixa de Valor:
-- Justificativa: Acelera consultas que filtram produtos por faixa de valor, como relatórios financeiros.
CREATE INDEX idx_produtos_valor ON tb_produtos (pro_valor);


-- Criação do user admin e suas permissões
DROP OWNED by admin;
DROP user IF EXISTS admin;
CREATE USER admin WITH PASSWORD 'admin' CREATEROLE;
GRANT SELECT ON ALL TABLES IN SCHEMA public TO admin WITH GRANT OPTION;
GRANT INSERT ON ALL TABLES IN SCHEMA public TO admin WITH GRANT OPTION;
GRANT UPDATE ON ALL TABLES IN SCHEMA public TO admin WITH GRANT OPTION;
GRANT DELETE ON ALL TABLES IN SCHEMA public TO admin WITH GRANT OPTION;

--Criação do grupo vendedor e suas permissões
DROP OWNED BY vendedor;
DROP ROLE IF EXISTS vendedor;
CREATE ROLE vendedor;
GRANT SELECT ON tb_produtos TO vendedor;
GRANT SELECT ON tb_vendas TO vendedor;
GRANT SELECT ON view_funcionarios_login TO vendedor;

--Criação do grupo estoquista e suas permissões
DROP OWNED BY estoquista;
DROP ROLE IF EXISTS estoquista;
CREATE ROLE estoquista;
GRANT SELECT ON tb_produtos TO estoquista;
GRANT SELECT ON view_funcionarios_login TO estoquista;

GRANT vendedor to admin WITH ADMIN OPTION;
GRANT estoquista to admin WITH ADMIN OPTION;