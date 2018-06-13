package com.victorcruz.aula;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

import com.victorcruz.aula.domain.Categoria;
import com.victorcruz.aula.domain.Cidade;
import com.victorcruz.aula.domain.Cliente;
import com.victorcruz.aula.domain.Endereco;
import com.victorcruz.aula.domain.Estado;
import com.victorcruz.aula.domain.Produto;
import com.victorcruz.aula.domain.enums.TipoCliente;
import com.victorcruz.aula.repositories.CategoriaRepository;
import com.victorcruz.aula.repositories.CidadeRepository;
import com.victorcruz.aula.repositories.ClienteRepository;
import com.victorcruz.aula.repositories.EnderecoRepository;
import com.victorcruz.aula.repositories.EstadoRepository;
import com.victorcruz.aula.repositories.ProdutoRepository;

@SpringBootApplication
@ComponentScan({"com.victorcruz.aula.services", "com.victorcruz.aula.resources"})
@EntityScan( basePackages = {"com.victorcruz.aula.domain"} )
public class AulaApplication implements CommandLineRunner {

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(AulaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Categoria cat1 = new Categoria (null, "Informatica");
		Categoria cat2 = new Categoria (null, "Escritorio");
		
		
		Produto p1 = new Produto(null,"Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto (null, "Mouse", 80.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "Sao Paulo");
		
		Cidade c1 = new Cidade(null, "Uberlandia", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);
		
		
		
		Cliente cli1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "36378912377", TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("27363323", "93838393"));
		
		
		Endereco e1 = new Endereco(null, "Rua Flores", 300, "Apto 303", "Jardim", "38220834", cli1, c1);
		Endereco e2 = new Endereco(null, "Avenida Matos", 300, "Sala 800", "Centro", "38777012", cli1, c2);
		
		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2,c3));
		
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3));
		
		estadoRepository.saveAll(Arrays.asList(est1, est2));
		
		cidadeRepository.saveAll(Arrays.asList(c1,c2,c3));
		
		clienteRepository.saveAll(Arrays.asList(cli1));
		
		enderecoRepository.saveAll(Arrays.asList(e1, e2));
		
	}
	
	
	
}
