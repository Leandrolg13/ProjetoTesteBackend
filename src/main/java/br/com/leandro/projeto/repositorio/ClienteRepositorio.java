package br.com.leandro.projeto.repositorio;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.leandro.projeto.dominio.Cliente;
import br.com.leandro.projeto.dominio.Vendedor;

@Repository
public interface ClienteRepositorio extends JpaRepository<Cliente, Integer>{
	
	@Transactional(readOnly=true)
	Cliente findByNome(String nome);
	
	@Transactional(readOnly=true)
	List<Cliente> findByVendedorIn(Optional<Vendedor> vendedor);
	
	@Query("SELECT id, nome, cpf, sexo FROM Cliente")
	List<Cliente> listarClientes();

}
