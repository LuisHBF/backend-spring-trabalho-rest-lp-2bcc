package br.edu.fema.crud.rest.lpbcc.vendas;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface VendaProdutoRepository extends JpaRepository<VendaProduto, Long>{

	public List<VendaProduto> findAllByVenda(Venda venda);
}
