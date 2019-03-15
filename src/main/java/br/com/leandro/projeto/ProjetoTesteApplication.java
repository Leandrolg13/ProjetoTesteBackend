package br.com.leandro.projeto;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.leandro.projeto.dominio.Cliente;
import br.com.leandro.projeto.dominio.Vendedor;
import br.com.leandro.projeto.repositorio.ClienteRepositorio;
import br.com.leandro.projeto.repositorio.VendedorRepositorio;

@SpringBootApplication
public class ProjetoTesteApplication  implements CommandLineRunner{

	@Autowired
	private ClienteRepositorio clienteRepositorio;
	
	@Autowired
	private VendedorRepositorio vendedorRepositorio; 
	
	public static void main(String[] args) {
		SpringApplication.run(ProjetoTesteApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Vendedor vend1 = new Vendedor(null, "Rogério Silva", "36874275283");
		Vendedor vend2 = new Vendedor(null, "Lindinalva Santos", "08517111893");
		
		Cliente cli1 = new Cliente(null, "João Ricardo", "25146563209", "M", vend2);
		Cliente cli2 = new Cliente(null, "Ronaldo Gaúcho", "70523272529", "M", vend2);
		Cliente cli3 = new Cliente(null, "Maria Rosário", "59568797424", "F", vend1);
		
		vend1.getClientes().addAll(Arrays.asList(cli3));
		vend2.getClientes().addAll(Arrays.asList(cli1, cli2));
		
		vendedorRepositorio.saveAll(Arrays.asList(vend1, vend2));
		clienteRepositorio.saveAll(Arrays.asList(cli1, cli2, cli3));
	}

}
