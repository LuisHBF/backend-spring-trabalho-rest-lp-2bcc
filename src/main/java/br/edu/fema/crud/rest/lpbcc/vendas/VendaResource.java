package br.edu.fema.crud.rest.lpbcc.vendas;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.edu.fema.crud.rest.lpbcc.vendas.dto.VendaDTO;
import br.edu.fema.crud.rest.lpbcc.vendas.form.ProdutoVendaFORM;

@RestController
@RequestMapping("/vendas")
public class VendaResource {

	@Autowired
	private VendaService vendaService;
	
	@GetMapping("/{idFuncionario}")
	public ResponseEntity<List<VendaDTO>> recuperarVendasFuncionario(@PathVariable Long idFuncionario){
		return ResponseEntity.ok().body(this.vendaService.recuperarVendasFuncionario(idFuncionario));
	}
	
	@PostMapping("/{idFuncionario}")
	@ResponseStatus(HttpStatus.CREATED)
	public void cadastrarVendaFuncionario(@PathVariable Long idFuncionario, @RequestBody List<ProdutoVendaFORM> produtos){
		this.vendaService.cadastrarVenda(idFuncionario, produtos);
	}
	
	

}
