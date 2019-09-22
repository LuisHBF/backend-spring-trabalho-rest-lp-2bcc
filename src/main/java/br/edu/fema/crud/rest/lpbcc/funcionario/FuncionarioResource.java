package br.edu.fema.crud.rest.lpbcc.funcionario;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/funcionarios")
public class FuncionarioResource {

	@Autowired
	private FuncionarioRepository funcionarioRepository;
	
	@GetMapping
	public ResponseEntity<List<Funcionario>> listarFuncionarios(){
		return ResponseEntity.ok(this.funcionarioRepository.findAll());
	}
	
	@ResponseStatus(code = HttpStatus.CREATED)
	@PostMapping
	public void cadastrarFuncionario(@RequestBody @Valid Funcionario funcionario){
		this.funcionarioRepository.save(funcionario);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletarFuncionario(@PathVariable Long id){
		this.funcionarioRepository.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Funcionario> atualizarFuncionario(@PathVariable Long id, @RequestBody @Valid Funcionario funcionario){
		Optional<Funcionario> funcionarioSalvo = this.funcionarioRepository.findById(id);
		if(!funcionarioSalvo.isPresent())
			throw new EmptyResultDataAccessException(1);
		BeanUtils.copyProperties(funcionario, funcionarioSalvo.get(),"id");
		return ResponseEntity.ok().body(this.funcionarioRepository.save(funcionarioSalvo.get()));
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Funcionario> recuperarFuncionario(@PathVariable Long id){
		Optional<Funcionario> funcionario = this.funcionarioRepository.findById(id);
		if(!funcionario.isPresent())
			throw new EmptyResultDataAccessException(1);

		return ResponseEntity.ok(funcionario.get());
	}
	
}
