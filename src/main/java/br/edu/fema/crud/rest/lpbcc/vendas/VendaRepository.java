package br.edu.fema.crud.rest.lpbcc.vendas;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.fema.crud.rest.lpbcc.funcionario.Funcionario;

public interface VendaRepository extends JpaRepository<Venda, Long>{

	public List<Venda> findAllByFuncionario(Funcionario funcionario);
}
