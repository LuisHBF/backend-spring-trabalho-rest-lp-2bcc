package br.edu.fema.crud.rest.lpbcc.produto;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="produtos")
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull(message = "Informe o nome do produto!")
	@Size(min=4, max=100, message = "O nome do produto deve ter entre 4 e 100 carácteres!")
	private String nome;
	
	@NotNull(message = "Informe a quantidade em estoque!")
	@Min(value = 0, message="A menor quantidade em estoque possível é zero!")
	@Column(name="quantidade_estoque")
	private Long quantidadeEstoque;
	
	@NotNull(message = "Informe o valor do produto!")
	@Min(value = 0, message="Informe um valor válido para o produto!")
	private BigDecimal valor;

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Long getQuantidadeEstoque() {
		return quantidadeEstoque;
	}

	public void setQuantidadeEstoque(Long quantidadeEstoque) {
		this.quantidadeEstoque = quantidadeEstoque;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	
	
	
}
