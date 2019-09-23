package br.edu.fema.crud.rest.lpbcc.exceptionHandler.dto;

public class ErroValidacaoDTO {

	private String mensagem;

	public ErroValidacaoDTO(String mensagem) {
		this.mensagem = mensagem;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	
	
}
