package br.com.leandro.projeto.servicos;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.leandro.projeto.dominio.Vendedor;
import br.com.leandro.projeto.repositorio.VendedorRepositorio;
import br.com.leandro.projeto.servicos.excecoes.ObjetoNaoEncontrado;

@Service
public class VendedorServico {
	
	@Autowired
	private VendedorRepositorio vendedorRepositorio;
	
	public List<Vendedor> listarVendedor(){
		List<Vendedor> lista = new ArrayList<Vendedor>();
		lista = vendedorRepositorio.findAll();
		return lista;
	}
	
	public Vendedor buscarPorId(Integer id) {
		Optional<Vendedor> obj = vendedorRepositorio.findById(id);
		return obj.orElseThrow(() -> new ObjetoNaoEncontrado("Objeto não encontrado. ID:" + id));
	}
	
	public Vendedor inserir(Vendedor obj) {
		return vendedorRepositorio.save(obj);
	}
	
	public Vendedor atualizar(Vendedor obj) {
		buscarPorId(obj.getId());
		return vendedorRepositorio.save(obj);
	}
	
}
