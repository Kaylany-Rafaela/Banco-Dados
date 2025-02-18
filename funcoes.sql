-- Função para simular ROLLBACK
CREATE OR REPLACE FUNCTION teste_rollback()
RETURNS void AS $$
BEGIN
    -- Início da transação
    INSERT INTO tb_produtos (pro_codigo, pro_descricao, pro_valor, pro_quantidade, tb_fornecedores_for_codigo)
    VALUES (999, 'Produto Inválido', 100.00, 10, 1);

    -- Erro proposital para testar o Rollback
    PERFORM 1 / 0; -- Divisão por zero

    -- Este código nunca será alcançado devido ao erro acima
    COMMIT;
EXCEPTION
    WHEN OTHERS THEN
        RAISE NOTICE 'Erro encontrado. Rollback realizado.';
        -- O Rollback já foi executado automaticamente pelo PostgreSQL
END;
$$ LANGUAGE plpgsql;

SELECT teste_rollback();

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
    
    IF(var_valor <= 0) THEN
        RAISE EXCEPTION 'Valor do produto nao pode ser menor ou igual a zero!';
    ELSIF (var_quantidade < 0) THEN
        RAISE EXCEPTION 'Quantidade do produto nao pode ser menor que zero!';
    END IF;

    SELECT max(pro_codigo) FROM tb_produtos INTO var_codigo;

    IF var_codigo IS NULL THEN
        var_codigo := 1;
    ELSE
        var_codigo := var_codigo + 1;
    END IF;

    INSERT INTO tb_produtos VALUES (var_codigo, var_descricao, var_valor, var_quantidade, var_fornecedores_codigo);
END;
$$ LANGUAGE plpgsql SECURITY DEFINER;

CREATE OR REPLACE PROCEDURE remove_tb_produtos(inputCodigo text)
    AS $$
DECLARE
    var_codigo BIGINT;
BEGIN   
	IF inputCodigo IS NULL THEN
        RAISE EXCEPTION 'Codigo nao pode ser nulo';
    END IF;
    BEGIN
        var_codigo := inputCodigo::BIGINT;
    EXCEPTION WHEN others THEN
        RAISE EXCEPTION 'ID do Produto nao e um numero valido: %', inputCodigo;
    END;

    DELETE FROM tb_produtos WHERE pro_codigo = var_codigo;
END;
$$ LANGUAGE plpgsql SECURITY DEFINER;

CREATE OR REPLACE PROCEDURE entrada_produto(inputCodigo text, inputQuantidade text)
	AS $$
DECLARE
    var_codigo BIGINT;
    var_quantidade INT;
BEGIN
IF inputCodigo IS NULL THEN
        RAISE EXCEPTION 'Codigo nao pode ser nulo';
    ELSIF inputQuantidade IS NULL THEN
        RAISE EXCEPTION 'Quantidade nao pode ser nula';
    END IF;

    BEGIN
        var_codigo := inputCodigo::BIGINT;
    EXCEPTION WHEN others THEN
        RAISE EXCEPTION 'ID do Produto nao e um numero valido: %', inputCodigo;
    END;

    BEGIN
        var_quantidade := inputQuantidade::INT;
    EXCEPTION WHEN others THEN
        RAISE EXCEPTION 'Quantidade do Produto nao e um numero valido: %', inputQuantidade;
    END;

    UPDATE tb_produtos SET pro_quantidade = pro_quantidade + var_quantidade WHERE pro_codigo = var_codigo;
END;
$$ LANGUAGE plpgsql SECURITY DEFINER;

CREATE OR REPLACE PROCEDURE vender_produto(inputCodigo text, inputDescricao text, inputQuantidade text)
    AS $$
DECLARE
    var_codigo BIGINT;
    var_quantidadeVenda INT;
    var_quantidadeEstoque INT;
    var_codVenda BIGINT;
BEGIN   
    -- Null check pra todas as entradas
	IF inputCodigo IS NULL THEN
        RAISE EXCEPTION 'Codigo nao pode ser nulo';
    ELSIF inputDescricao IS NULL THEN
        RAISE EXCEPTION 'Descricao nao pode ser nula';
    ELSIF inputQuantidade IS NULL THEN
        RAISE EXCEPTION 'Quantidade nao pode ser nula';
    END IF;

    -- Type check pra todas as entradas
    BEGIN
        var_codigo := inputCodigo::BIGINT;
    EXCEPTION WHEN others THEN
        RAISE EXCEPTION 'ID do Produto nao e um numero valido: %', inputCodigo;
    END;

    BEGIN
        var_quantidadeVenda := inputQuantidade::INT;
    EXCEPTION WHEN others THEN
        RAISE EXCEPTION 'Quantidade do Produto nao e um numero valido: %', inputQuantidade;
    END;

    IF (var_quantidadeVenda <= 0) THEN
        RAISE EXCEPTION 'Quantidade de produtos vendidos não pode ser menor ou igual a zero!';
    END IF;

    -- Check se há suficiente no estoque
    SELECT pro_quantidade FROM tb_produtos WHERE pro_codigo = var_codigo INTO var_quantidadeEstoque;
    IF (var_quantidadeEstoque - var_quantidadeVenda) < 0 THEN
        RAISE EXCEPTION 'Quantidade do produto % indisponivel no estoque', inputDescricao;
    END IF;

    -- Atualizar a quantidade do produto
    UPDATE tb_produtos SET pro_quantidade = pro_quantidade - var_quantidadeVenda WHERE pro_codigo = var_codigo;
END;
$$ LANGUAGE plpgsql SECURITY DEFINER;

CREATE OR REPLACE PROCEDURE registrar_venda(inputValorTotal text, inputCPFVendedor text)
    AS $$
DECLARE
    var_valorTotal DECIMAL(10, 2);
    var_codVendedor BIGINT;
    var_codVenda BIGINT;
BEGIN   
    -- Null check pra todas as entradas
	IF inputValorTotal IS NULL THEN
        RAISE EXCEPTION 'Valor total nao pode ser nulo';
    ELSEIF inputCPFVendedor IS NULL THEN
        RAISE EXCEPTION 'Nome do vendedor nao pode ser nulo';
    END IF;

    -- Type check pra todas as entradas
    BEGIN
        var_valorTotal := inputValorTotal::DECIMAL(10, 2);
    EXCEPTION WHEN others THEN
        RAISE EXCEPTION 'Valor total da venda nao e um numero valido: %', inputValorTotal;
    END;

    IF(var_valorTotal <= 0) THEN
        RAISE EXCEPTION 'Valor total da venda não pode ser menor ou igual a zero!';
    END IF;

    -- Registrar a venda na tabela

    -- Atribui o codigo do funcionario
    SELECT fun_codigo from view_funcionarios_login WHERE fun_cpf = inputCPFVendedor INTO var_codVendedor;
    SELECT max(ven_codigo) FROM tb_vendas INTO var_codVenda;
    IF var_codVenda IS NULL THEN
        var_codVenda := 1;
    ELSE
        var_codVenda := var_codVenda + 1;
    END IF;
    INSERT INTO tb_vendas(ven_codigo, ven_valor_total, tb_funcionarios_fun_codigo) VALUES (var_codVenda, var_valorTotal, var_codVendedor);
END;
$$ LANGUAGE plpgsql SECURITY DEFINER;