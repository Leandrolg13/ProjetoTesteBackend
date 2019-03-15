package br.com.leandro.projeto.recursos;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

import br.com.leandro.projeto.dominio.Cliente;
import br.com.leandro.projeto.dto.ClienteDTO;
import br.com.leandro.projeto.servicos.ClienteServico;

@CrossOrigin
@RestController
@RequestMapping(value="/clientes")
public class ClienteRecursos {
	
	@Autowired
	private ClienteServico clienteServico;
		
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<Cliente>> listarClientes() {
		List<Cliente> lista = new ArrayList<>();
		lista = clienteServico.listarClientes();
		return ResponseEntity.ok().body(lista);
	}
	
	@RequestMapping(value="/vendedor", method=RequestMethod.GET)
	public ResponseEntity<List<ClienteDTO>> listarClientesComVendedor() {
		List<Cliente> lista = clienteServico.listarClientesComVendedor();
		List<ClienteDTO> listaDTO = lista.stream().map(obj -> new ClienteDTO(obj)).collect(Collectors.toList()); 
		return ResponseEntity.ok().body(listaDTO);
	}
	
	@RequestMapping(value="/{id}" , method=RequestMethod.GET)
	public ResponseEntity<ClienteDTO> buscaPorId(@PathVariable Integer id) {
		Cliente obj = clienteServico.buscarPorId(id);
		ClienteDTO objDTO = new ClienteDTO(obj);
		return ResponseEntity.ok().body(objDTO);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> inserir(@Valid @RequestBody Cliente obj) {
		obj = clienteServico.inserir(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> atualizar(@Valid @RequestBody Cliente obj, @PathVariable Integer id) {
		obj.setId(id);
		obj = clienteServico.atualizar(obj);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/vendedor/{id}" , method=RequestMethod.GET)
	public ResponseEntity<List<ClienteDTO>> listarClientesPorIdVendedor(@PathVariable Integer id) {
		List<Cliente> lista = clienteServico.listarClientesPorIdVendedor(id);
		List<ClienteDTO> listaDTO = lista.stream().map(obj -> new ClienteDTO(obj)).collect(Collectors.toList()); 
		return ResponseEntity.ok().body(listaDTO);
	}

}
