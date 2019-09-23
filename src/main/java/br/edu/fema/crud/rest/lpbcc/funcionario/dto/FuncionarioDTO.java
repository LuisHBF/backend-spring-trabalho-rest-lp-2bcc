package br.edu.fema.crud.rest.lpbcc.funcionario.dto;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

import br.edu.fema.crud.rest.lpbcc.funcionario.Funcionario;

public class FuncionarioDTO {

	private Long id;
	private String nome;
	private String dataNascimento;
	private Long idade;
	
	
	public FuncionarioDTO(Funcionario funcionario) {
		DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		this.id = funcionario.getId();
		this.nome = funcionario.getNome();
		this.dataNascimento = formatador.format(funcionario.getDataNascimento());
		this.idade = (Long) funcionario.getDataNascimento().until(LocalDate.now(), ChronoUnit.YEARS);
	}
	
	public static List<FuncionarioDTO> converter(List<Funcionario> funcionarios){
		return funcionarios.stream().map(FuncionarioDTO::new).collect(Collectors.toList());
	}
	
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
	public String getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	public Long getIdade() {
		return idade;
	}
	public void setIdade(Long idade) {
		this.idade = idade;
	}
	
	
}
