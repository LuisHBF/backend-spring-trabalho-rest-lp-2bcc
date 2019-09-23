package br.edu.fema.crud.rest.lpbcc.vendas;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.edu.fema.crud.rest.lpbcc.exception.AcaoNaoPermitidaException;
import br.edu.fema.crud.rest.lpbcc.funcionario.Funcionario;
import br.edu.fema.crud.rest.lpbcc.funcionario.FuncionarioRepository;
import br.edu.fema.crud.rest.lpbcc.produto.Produto;
import br.edu.fema.crud.rest.lpbcc.produto.ProdutoRepository;
import br.edu.fema.crud.rest.lpbcc.vendas.dto.VendaDTO;
import br.edu.fema.crud.rest.lpbcc.vendas.form.ProdutoVendaFORM;

@Service
public class VendaService {

	@Autowired
	private VendaProdutoRepository vendaProdutoRepository;

	@Autowired
	private VendaRepository vendaRepository;

	@Autowired
	private FuncionarioRepository funcionarioRepository;

	@Autowired
	private ProdutoRepository produtoRepository;

	public List<VendaDTO> recuperarVendasFuncionario(Long idFuncionario) {
		Optional<Funcionario> funcionario = this.funcionarioRepository.findById(idFuncionario);

		if (!funcionario.isPresent())
			throw new AcaoNaoPermitidaException("Funcionário não encontrado!", HttpStatus.BAD_REQUEST);

		List<Venda> vendas = this.vendaRepository.findAllByFuncionario(funcionario.get());
		List<VendaDTO> retorno = new ArrayList<VendaDTO>();

		vendas.forEach(venda -> {
			List<VendaProduto> vendasProdutos = this.vendaProdutoRepository.findAllByVenda(venda);
			retorno.add(new VendaDTO(funcionario.get(), vendasProdutos, venda));
		});

		return retorno;
	}

	public void cadastrarVenda(Long idFuncionario, List<ProdutoVendaFORM> produtosFORM) {
		Optional<Funcionario> funcionario = this.funcionarioRepository.findById(idFuncionario);

		if (!funcionario.isPresent())
			throw new AcaoNaoPermitidaException("Funcionário não encontrado!", HttpStatus.BAD_REQUEST);

		if (produtosFORM.size() == 0)
			throw new AcaoNaoPermitidaException("Adicione ao menos um item na venda!", HttpStatus.BAD_REQUEST);

		List<Produto> produtos = new ArrayList<Produto>();
		produtosFORM.forEach(produto -> {
			Optional<Produto> produtoSalvo = this.produtoRepository.findById(produto.getId());

			if (!produtoSalvo.isPresent())
				throw new AcaoNaoPermitidaException(
						"Produto de código " + produto.getId()
								+ " não encontrado! Nenhuma alteração foi realizada no sistema.",
						HttpStatus.BAD_REQUEST);

			if (produto.getQuantidade() > produtoSalvo.get().getQuantidadeEstoque())
				throw new AcaoNaoPermitidaException("Você está solicitando " + produto.getQuantidade() + " do produto "
						+ produtoSalvo.get().getNome() + "(" + produto.getId() + "), porém só possuimos "
						+ produtoSalvo.get().getQuantidadeEstoque() + " em estoque!", HttpStatus.BAD_REQUEST);

			produtoSalvo.get()
					.setQuantidadeEstoque(produtoSalvo.get().getQuantidadeEstoque() - produto.getQuantidade());

			this.produtoRepository.save(produtoSalvo.get());

			produtos.add(produtoSalvo.get());
		});

		Venda venda = new Venda();
		venda.setData(LocalDate.now());
		venda.setFuncionario(funcionario.get());
		this.vendaRepository.save(venda);

		List<VendaProduto> vendasProduto = new ArrayList<VendaProduto>();

		for (int i = 0; i < produtos.size(); i++) {
			VendaProduto vendaProduto = new VendaProduto();
			vendaProduto.setProduto(produtos.get(i));
			vendaProduto.setVenda(venda);
			vendaProduto.setQuantidade(produtosFORM.get(i).getQuantidade());
			vendasProduto.add(vendaProduto);
		}

		this.vendaProdutoRepository.saveAll(vendasProduto);

	}
}
