CREATE TRIGGER pedidoproduto_itens after update on pedidoproduto 
FOR EACH ROW EXECUTE PROCEDURE pedidoproduto_estoque();

CREATE OR REPLACE FUNCTION pedidoproduto_estoque RETURNS trigger AS
declare 
	declare
		quantidade_antiga 	FLOAT;
	begin
		quantidade_antiga = (SELECT quantidade FROM materiaprima where codMateriaPrima = new.materiaprima_codMateriaPrima);
		quantidade_antiga = quantidade_antiga - old.quantidade + new.quantidade;
		update materiaprima set quantidade = quantidade_antiga where codMateriaPrima = new.materiaprima_codMateriaPrima;
		return new;
	end;
  LANGUAGE plpgsql 
