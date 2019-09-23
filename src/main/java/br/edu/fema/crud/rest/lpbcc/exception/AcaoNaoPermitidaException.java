package br.edu.fema.crud.rest.lpbcc.exception;

import org.springframework.http.HttpStatus;

public class AcaoNaoPermitidaException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	private String mensagem;
	private HttpStatus status;

	public AcaoNaoPermitidaException(String mensagem, HttpStatus status) {
		this.mensagem = mensagem;
		this.status = status;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}
	
	

}
