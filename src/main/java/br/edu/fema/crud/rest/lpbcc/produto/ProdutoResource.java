package br.edu.fema.crud.rest.lpbcc.produto;

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
@RequestMapping("/produtos")
public class ProdutoResource {

	@Autowired
	private ProdutoRepository produtoRepository;
	
	@GetMapping
	public ResponseEntity<List<Produto>> listarProdutos(){
		return ResponseEntity.ok(this.produtoRepository.findAll());
	}
	
	@ResponseStatus(code = HttpStatus.CREATED)
	@PostMapping
	public void cadastrarProduto(@RequestBody @Valid Produto produto){
		this.produtoRepository.save(produto);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletarProduto(@PathVariable Long id){
		this.produtoRepository.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Produto> atualizarProduto(@PathVariable Long id, @RequestBody @Valid Produto produto){
		Optional<Produto> produtoSalvo = this.produtoRepository.findById(id);
		if(!produtoSalvo.isPresent())
			throw new EmptyResultDataAccessException(1);
		BeanUtils.copyProperties(produto, produtoSalvo.get(),"id");
		return ResponseEntity.ok().body(this.produtoRepository.save(produtoSalvo.get()));
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Produto> recuperarProduto(@PathVariable Long id){
		Optional<Produto> produto = this.produtoRepository.findById(id);
		if(!produto.isPresent())
			throw new EmptyResultDataAccessException(1);

		return ResponseEntity.ok(produto.get());
	}
}
