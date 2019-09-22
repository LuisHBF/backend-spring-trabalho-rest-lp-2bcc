package br.edu.fema.crud.rest.lpbcc.vendas;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import br.edu.fema.crud.rest.lpbcc.produto.Produto;

@Entity
@Table(name="vendas_has_produtos")
public class VendaProduto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@NotNull(message = "Informe o produto para a venda!")
	private Produto produto;
	
	@NotNull(message = "Informe a quantidade de produtos a serem vendidos!")
	@Min(value = 0, message = "A quantidade minima de produtos a serem vendidas é 0!")
	private Long quantidade;
	
	@ManyToOne
	@NotNull
	private Venda venda;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Long getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Long quantidade) {
		this.quantidade = quantidade;
	}

	public Venda getVenda() {
		return venda;
	}

	public void setVenda(Venda venda) {
		this.venda = venda;
	}
	
}
