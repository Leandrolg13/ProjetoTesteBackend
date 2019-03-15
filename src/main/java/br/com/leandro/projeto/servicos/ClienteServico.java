package br.com.leandro.projeto.servicos;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.leandro.projeto.dominio.Cliente;
import br.com.leandro.projeto.dominio.Vendedor;
import br.com.leandro.projeto.repositorio.ClienteRepositorio;
import br.com.leandro.projeto.repositorio.VendedorRepositorio;
import br.com.leandro.projeto.servicos.excecoes.ObjetoNaoEncontrado;

@Service
public class ClienteServico {
	
	@Autowired
	private ClienteRepositorio clienteRepositorio;
	
	@Autowired
	private VendedorRepositorio vendedorRepositorio; 
	
	public List<Cliente> listarClientes(){
		List<Cliente> lista = new ArrayList<Cliente>();
		lista = clienteRepositorio.listarClientes();
		return lista;
	}
	
	public List<Cliente> listarClientesComVendedor(){
		List<Cliente> lista = new ArrayList<Cliente>();
		lista = clienteRepositorio.findAll();
		return lista;
	}
	
	public Cliente buscarPorId(Integer id) {
		Optional<Cliente> obj = clienteRepositorio.findById(id);
		return obj.orElseThrow(() -> new ObjetoNaoEncontrado("Objeto não encontrado. ID:" + id));
	}
	
	public Cliente inserir(Cliente obj) {
		return clienteRepositorio.save(obj);
	}
	
	public Cliente atualizar(Cliente obj) {
		return clienteRepositorio.save(obj);
	}
	
	public List<Cliente> listarClientesPorIdVendedor(Integer id) {
		List<Cliente> lista = new ArrayList<>();
		Optional<Vendedor> vendedor = vendedorRepositorio.findById(id);
		lista = clienteRepositorio.findByVendedorIn(vendedor);
		return lista;
	}
}
