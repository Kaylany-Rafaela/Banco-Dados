-- Verifica e insere os dados na tabela
CREATE OR REPLACE PROCEDURE insert_tb_fornecedores(inputDescricao text)
    AS $$
DECLARE
    codigo BIGINT;
	descricao VARCHAR(45);
BEGIN

    IF inputDescricao IS NULL THEN
		RAISE EXCEPTION 'Descricao nao pode ser nula';
    END IF;

    SELECT max(for_codigo) FROM tb_fornecedores INTO codigo;

    IF codigo IS NULL THEN
        codigo := 1;
    ELSE
        codigo := codigo + 1;
    END IF;

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
	
    SELECT max(fun_codigo) FROM tb_funcionarios INTO var_codigo;

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

CREATE OR REPLACE PROCEDURE insert_tb_produtos(inputDescricao text, inputValor text, inputQuantidade text, inputIDFornecedor text)
    AS $$
DECLARE
    var_codigo BIGINT;
    var_descricao VARCHAR(45);
    var_valor DECIMAL(10, 2);
    var_quantidade INT;
    var_fornecedores_codigo BIGINT;
BEGIN   
	IF inputDescricao IS NULL THEN
        RAISE EXCEPTION 'Descricao nao pode ser nula';
	ELSIF inputValor IS NULL THEN
		RAISE EXCEPTION 'Valor nao pode ser nulo';
    ELSIF inputQuantidade IS NULL THEN
		RAISE EXCEPTION 'Quantidade nao pode ser nula';
    ELSIF inputIDFornecedor IS NULL THEN
		RAISE EXCEPTION 'ID do Fornecedor nao pode ser nulo';
    END IF;
	
    BEGIN
        var_descricao := inputDescricao::VARCHAR(45);
    EXCEPTION WHEN others THEN
        RAISE EXCEPTION 'Descricao nao e um texto valido: %', inputDescricao;
    END;

    BEGIN
        var_valor := inputValor::DECIMAL(10, 2);
    EXCEPTION WHEN others THEN
        RAISE EXCEPTION 'Valor nao e um numero valido: %', inputValor;
    END;

    BEGIN
        var_quantidade := inputQuantidade::INT;
    EXCEPTION WHEN others THEN
        RAISE EXCEPTION 'Quantidade nao e um numero valido: %', inputQuantidade;
    END;

    BEGIN
        var_fornecedores_codigo := inputIDFornecedor::BIGINT;
    EXCEPTION WHEN others THEN
        RAISE EXCEPTION 'ID Fornecedor nao e um numero valido: %', inputIDFornecedor;
    END;
    

    SELECT max(pro_codigo) FROM tb_produtos INTO var_codigo;

    IF var_codigo IS NULL THEN
        var_codigo := 1;
    ELSE
        var_codigo := var_codigo + 1;
    END IF;

    INSERT INTO tb_produtos VALUES (var_codigo, var_descricao, var_valor, var_quantidade, var_fornecedores_codigo);
END;
$$ LANGUAGE plpgsql;