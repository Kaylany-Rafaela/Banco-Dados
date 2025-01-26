-- Verifica e insere os dados na tabela
CREATE OR REPLACE FUNCTION public.insert_tb_fornecedores(input1 text,input2 text)
    RETURNS void AS $$
DECLARE
    codigo BIGINT;
	descricao VARCHAR(45);
BEGIN

	IF input1 IS NULL THEN
        RAISE EXCEPTION 'Entrada 1 não pode ser nula';
	ELSIF input2 IS NULL THEN
		RAISE EXCEPTION 'Entrada 2 não pode ser nula';
    END IF;
	
    BEGIN
        codigo := input1::BIGINT;
    EXCEPTION WHEN others THEN
        RAISE EXCEPTION 'Entrada 1 não é um número válido: %', input1;
    END;

	BEGIN
        descricao := input2::VARCHAR(45);
    EXCEPTION WHEN others THEN
        RAISE EXCEPTION 'Entrada 3 não é um texto válido: %', input2;
    END;

    INSERT INTO tb_fornecedores VALUES (codigo, descricao);
END;
$$ LANGUAGE plpgsql;

SELECT insert_tb_fornecedores('abc','razer');
