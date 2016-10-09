CREATE OR REPLACE FUNCTION entradaitem_estoque() returns trigger as $$
	declare
		v_quantidade_antiga 	FLOAT;
		v_valorultimacompra	FLOAT;
		v_valorcustomedio		FLOAT;
		v_quantidadecomprada	FLOAT;
	begin
		if (TG_OP = 'INSERT') then
		--altera quantidade
		v_quantidade_antiga = (SELECT quantidade FROM materiaprima where codMateriaPrima = new.materiaprima_codMateriaPrima);
		v_quantidade_antiga = v_quantidade_antiga + new.quantidade;
		update materiaprima set quantidade = v_quantidade_antiga where codMateriaPrima = new.materiaprima_codMateriaPrima;
		--altera valor custo
		v_valorultimacompra = (SELECT valorultimacompra FROM materiaprima where codMateriaPrima = new.materiaprima_codMateriaPrima);
		if (v_valorultimacompra < new.custounitario) then
			update materiaprima set valorultimacompra = new.custounitario where codMateriaPrima = new.materiaprima_codMateriaPrima;
		end if;
		--altera valormedio
		v_valorcustomedio = (SELECT avg(custounitario) FROM entradaitem where materiaprima_codMateriaPrima = new.materiaprima_codMateriaPrima);
		update materiaprima set valorcustomedio = v_valorcustomedio where codMateriaPrima = new.materiaprima_codMateriaPrima;	
						
			 
		
		return new;
		ELSIF (TG_OP = 'UPDATE') then
		--altera quantidade
		v_quantidade_antiga = (SELECT quantidade FROM materiaprima where codMateriaPrima = new.materiaprima_codMateriaPrima);
		v_quantidade_antiga = v_quantidade_antiga - old.quantidade + new.quantidade;
		update materiaprima set quantidade = v_quantidade_antiga where codMateriaPrima = new.materiaprima_codMateriaPrima;

		--altera valor cust
		update materiaprima set valorultimacompra = new.custounitario where codMateriaPrima = new.materiaprima_codMateriaPrima;			
		--altera valormedio
		v_valorcustomedio = (SELECT avg(custounitario) FROM entradaitem where materiaprima_codMateriaPrima = new.materiaprima_codMateriaPrima);
		update materiaprima set valorcustomedio = v_valorcustomedio where codMateriaPrima = new.materiaprima_codMateriaPrima;		
			
		return new;
		
		ELSIF (TG_OP = 'DELETE') then
		--altera quantidade
		v_quantidade_antiga = (SELECT quantidade FROM materiaprima where codMateriaPrima = old.materiaprima_codMateriaPrima);			
				
		v_quantidade_antiga = v_quantidade_antiga - old.quantidade;
		update materiaprima set quantidade = v_quantidade_antiga where codMateriaPrima = old.materiaprima_codMateriaPrima;
		--altera valor custo
		update materiaprima set valorultimacompra = old.custounitario where codMateriaPrima = old.materiaprima_codMateriaPrima;
		--altera valormedio
		v_valorcustomedio = (SELECT avg(custounitario) FROM entradaitem where materiaprima_codMateriaPrima = old.materiaprima_codMateriaPrima);
		update materiaprima set valorcustomedio = v_valorcustomedio where codMateriaPrima = old.materiaprima_codMateriaPrima;			

		return old;
		END IF;
	end;
$$ LANGUAGE plpgsql;

CREATE TRIGGER entradaitem_estoque after INSERT OR UPDATE OR DELETE ON entradaitem
FOR EACH ROW EXECUTE PROCEDURE entradaitem_estoque();
