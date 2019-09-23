package br.edu.fema.crud.rest.lpbcc.vendas.dto;

import java.math.BigDecimal;

import br.edu.fema.crud.rest.lpbcc.produto.Produto;

public class ProdutoVendaDTO {

	private Long id;
	private String nome;
	private Long quantidadeVendida;
	private BigDecimal valorUnitario;
	private BigDecimal valorTotal;
	
	public ProdutoVendaDTO(Produto produto, Long quantidadeVendida) {
		this.id = produto.getId();
		this.nome = produto.getNome();
		this.valorUnitario = produto.getValor();
		this.valorTotal = produto.getValor().multiply(new BigDecimal(quantidadeVendida));
		this.quantidadeVendida = quantidadeVendida;
	}
	
	public void setValorUnitario(BigDecimal valorUnitario) {
		this.valorUnitario = valorUnitario;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getQuantidadeVendida() {
		return quantidadeVendida;
	}

	public void setQuantidadeVendida(Long quantidadeVendida) {
		this.quantidadeVendida = quantidadeVendida;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public BigDecimal getValorUnitario() {
		return valorUnitario;
	}
	
}
