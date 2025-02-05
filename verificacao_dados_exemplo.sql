-- Verifica e insere os dados na tabela
CREATE OR REPLACE PROCEDURE insert_tb_fornecedores(inputCodigo text,inputDescricao text)
    AS $$
DECLARE
    codigo BIGINT;
	descricao VARCHAR(45);
BEGIN

	IF inputCodigo IS NULL THEN
        RAISE EXCEPTION 'Codigo nao pode ser nulo';
	ELSIF inputDescricao IS NULL THEN
		RAISE EXCEPTION 'Descricao nao pode ser nula';
    END IF;
	
    BEGIN
        codigo := inputCodigo::BIGINT;
    EXCEPTION WHEN others THEN
        RAISE EXCEPTION 'Codigo nao e um numero valido: %', inputCodigo;
    END;

	BEGIN
        descricao := inputDescricao::VARCHAR(45);
    EXCEPTION WHEN others THEN
        RAISE EXCEPTION 'Descricao nao e um texto valido: %', inputDescricao;
    END;

    INSERT INTO tb_fornecedores VALUES (codigo, descricao);
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE PROCEDURE insert_tb_funcionarios(inputNome text, inputCPF text, inputSenha text, inputFuncao text)
    AS $$
DECLARE
    var_codigo BIGINT;
    var_nome VARCHAR(45);
    var_cpf VARCHAR(45);
    var_senha VARCHAR(50);
    var_funcao VARCHAR(50);
BEGIN   
	IF inputNome IS NULL THEN
        RAISE EXCEPTION 'Nome nao pode ser nulo';
	ELSIF inputCPF IS NULL THEN
		RAISE EXCEPTION 'CPF nao pode ser nulo';
    ELSIF inputSenha IS NULL THEN
		RAISE EXCEPTION 'Senha nao pode ser nula';
    ELSIF inputFuncao IS NULL THEN
		RAISE EXCEPTION 'Funcao nao pode ser nula';
    END IF;

    IF inputFuncao != 'Vendedor' AND inputFuncao != 'Estoquista' AND inputFuncao != 'vendedor' AND inputFuncao != 'estoquista' THEN
        RAISE EXCEPTION 'Nome invalido para Funcao';
    END IF;
	
    SELECT min(fun_codigo) FROM tb_funcionarios INTO var_codigo;

    IF var_codigo IS NULL THEN
        var_codigo := 1;
    ELSE
        var_codigo := var_codigo + 1;
    END IF;

    var_nome = inputNome;
    var_cpf = inputCPF;
    var_senha = inputSenha;
    var_funcao = inputFuncao;

    INSERT INTO tb_funcionarios VALUES (var_codigo, var_nome, var_cpf, var_senha, var_funcao);
END;
$$ LANGUAGE plpgsql;
