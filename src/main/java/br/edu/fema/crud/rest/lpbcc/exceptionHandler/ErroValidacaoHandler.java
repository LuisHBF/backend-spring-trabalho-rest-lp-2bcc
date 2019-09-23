package br.edu.fema.crud.rest.lpbcc.exceptionHandler;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.edu.fema.crud.rest.lpbcc.exception.AcaoNaoPermitidaException;
import br.edu.fema.crud.rest.lpbcc.exceptionHandler.dto.ErroValidacaoDTO;

@RestControllerAdvice
public class ErroValidacaoHandler {

	@Autowired
	private MessageSource messageSource;
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<List<ErroValidacaoDTO>> methodArgumentNotValidExceptionHandler (MethodArgumentNotValidException exception){
		List<ErroValidacaoDTO> listaDTO = new ArrayList<ErroValidacaoDTO>();
		List<FieldError> erros = exception.getBindingResult().getFieldErrors();
		erros.forEach(erro -> {
			String mensagem = this.messageSource.getMessage(erro, LocaleContextHolder.getLocale());
			listaDTO.add(new ErroValidacaoDTO(mensagem));
		});
		
		return ResponseEntity.badRequest().body(listaDTO);
	}
	
	@ExceptionHandler(EmptyResultDataAccessException.class)
	public ResponseEntity<?> emptyResultDataAccessExceptionHandler(EmptyResultDataAccessException exception){
		return ResponseEntity.notFound().build();
	}
	
	@ExceptionHandler(AcaoNaoPermitidaException.class)
	public ResponseEntity<ErroValidacaoDTO> acaoNaoPermitidaExceptionHandler(AcaoNaoPermitidaException exception){
		return ResponseEntity.status(exception.getStatus()).body(new ErroValidacaoDTO(exception.getMensagem()));
	}
}
