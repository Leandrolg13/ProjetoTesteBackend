package br.com.leandro.projeto.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.leandro.projeto.dominio.Vendedor;

@Repository
public interface VendedorRepositorio extends JpaRepository<Vendedor, Integer>{
	
	@Transactional(readOnly=true)
	Vendedor findByNome(String nome);

}
