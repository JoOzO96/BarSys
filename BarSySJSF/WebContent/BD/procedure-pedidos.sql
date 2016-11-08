CREATE OR REPLACE FUNCTION pedidoitem_estoque() returns trigger as $$
	declare
		v_quantidadeatual FLOAT;
		r  materiaprima%rowtype;
		v_quantidadecomposicao FLOAT;
	begin
		if (new.finalizado = true) then
		for r in (Select mp.codmateriaprima
		from pedidoproduto pp 
		inner join produtocomposicao pc on pc.produto_codproduto = new.produto_codproduto
		inner join materiaprima mp on pc.materiaprima_codmateriaprima = mp.codmateriaprima
		order by mp.codmateriaprima)
		loop
			v_quantidadeatual = (SELECT quantidade from materiaprima where codmateriaprima = r.codmateriaprima);
			v_quantidadecomposicao = (SELECT p.quantidade from produtocomposicao p 
			where p.materiaprima_codmateriaprima = r.codmateriaprima);

			v_quantidadeatual = v_quantidadeatual - v_quantidadecomposicao;
			update materiaprima set quantidade = v_quantidadeatual where codmateriaprima = r.codmateriaprima;
		end loop;
		
		end if;
	return new;
	end;
$$ LANGUAGE plpgsql;

update pedidoproduto set finalizado = true where codpedidoproduto = 1;
update pedidoproduto set finalizado = false where codpedidoproduto = 1;

CREATE TRIGGER pedidoitem_estoque after UPDATE ON pedidoproduto
FOR EACH ROW EXECUTE PROCEDURE pedidoitem_estoque();