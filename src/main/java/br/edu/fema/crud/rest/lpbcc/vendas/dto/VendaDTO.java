package br.edu.fema.crud.rest.lpbcc.vendas.dto;

import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import br.edu.fema.crud.rest.lpbcc.funcionario.Funcionario;
import br.edu.fema.crud.rest.lpbcc.funcionario.dto.FuncionarioDTO;
import br.edu.fema.crud.rest.lpbcc.vendas.Venda;
import br.edu.fema.crud.rest.lpbcc.vendas.VendaProduto;

public class VendaDTO {

	public FuncionarioDTO funcionario;
	public List<ProdutoVendaDTO> produtos = new ArrayList<ProdutoVendaDTO>();
	public BigDecimal valorTotal = new BigDecimal(0);
	public String dataVenda;
	
	public VendaDTO(Funcionario funcionario, List<VendaProduto> vendasProdutos, Venda venda) {
		this.funcionario = new FuncionarioDTO(funcionario);
		vendasProdutos.forEach(vp -> {
			ProdutoVendaDTO produtoVendaDTO = new ProdutoVendaDTO(vp.getProduto(), vp.getQuantidade());
			this.valorTotal = this.valorTotal.add(produtoVendaDTO.getValorTotal());
			System.out.println(produtoVendaDTO.getValorTotal());
			this.produtos.add(produtoVendaDTO);
		});
		
		DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		this.dataVenda = formatador.format(venda.getData());
	}
}
