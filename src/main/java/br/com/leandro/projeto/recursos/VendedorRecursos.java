package br.com.leandro.projeto.recursos;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.leandro.projeto.dominio.Vendedor;
import br.com.leandro.projeto.servicos.VendedorServico;

@CrossOrigin
@RestController
@RequestMapping(value="/vendedores")
public class VendedorRecursos {
	
	@Autowired
	private VendedorServico vendedorServico;
		
	@RequestMapping( method=RequestMethod.GET)
	public ResponseEntity<List<Vendedor>> listarVendedores() {
		List<Vendedor> lista = vendedorServico.listarVendedor();
		return ResponseEntity.ok().body(lista);
	}
	
	@RequestMapping(value="/{id}" , method=RequestMethod.GET)
	public ResponseEntity<Vendedor> buscaPorId(@PathVariable Integer id) {
		Vendedor obj = vendedorServico.buscarPorId(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> inserir(@Valid @RequestBody Vendedor obj) {
		obj = vendedorServico.inserir(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> atualizar(@Valid @RequestBody Vendedor obj, @PathVariable Integer id) {
		obj.setId(id);
		obj = vendedorServico.atualizar(obj);
		return ResponseEntity.noContent().build();
	}

}
