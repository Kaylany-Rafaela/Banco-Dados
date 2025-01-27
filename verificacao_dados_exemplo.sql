-- Verifica e insere os dados na tabela
CREATE OR REPLACE FUNCTION insert_tb_fornecedores(inputCodigo text,inputDescricao text)
    RETURNS void AS $$
DECLARE
    codigo BIGINT;
	descricao VARCHAR(45);
BEGIN

	IF inputCodigo IS NULL THEN
        RAISE EXCEPTION 'Código não pode ser nulo';
	ELSIF inputDescricao IS NULL THEN
		RAISE EXCEPTION 'Descrição não pode ser nula';
    END IF;
	
    BEGIN
        codigo := inputCodigo::BIGINT;
    EXCEPTION WHEN others THEN
        RAISE EXCEPTION 'Código não é um número válido: %', inputCodigo;
    END;

	BEGIN
        descricao := inputDescricao::VARCHAR(45);
    EXCEPTION WHEN others THEN
        RAISE EXCEPTION 'Descrição não é um texto válido: %', inputDescricao;
    END;

    INSERT INTO tb_fornecedores VALUES (codigo, descricao);
END;
$$ LANGUAGE plpgsql;

-- Verifica e altera os dados da tabela
CREATE OR REPLACE FUNCTION update_tb_fornecedores(inputCodigo text,inputDescricao text)
    RETURNS void AS $$
DECLARE
    codigo BIGINT;
	descricao VARCHAR(45);
BEGIN

	IF inputCodigo IS NULL THEN
        RAISE EXCEPTION 'Código não pode ser nulo';
	ELSIF inputDescricao IS NULL THEN
		RAISE EXCEPTION 'Descrição não pode ser nula';
    END IF;
	
    BEGIN
        codigo := inputCodigo::BIGINT;
    EXCEPTION WHEN others THEN
        RAISE EXCEPTION 'Código não é um número válido: %', inputCodigo;
    END;

	BEGIN
        descricao := inputDescricao::VARCHAR(45);
    EXCEPTION WHEN others THEN
        RAISE EXCEPTION 'Descrição não é um texto válido: %', inputDescricao;
    END;

    UPDATE TABLE tb_fornecedores
	set (codigo, descricao);
END;
$$ LANGUAGE plpgsql;

SELECT insert_tb_fornecedores('abc','razer');
