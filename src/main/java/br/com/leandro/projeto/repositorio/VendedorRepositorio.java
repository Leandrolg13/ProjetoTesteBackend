package br.com.leandro.projeto.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.leandro.projeto.dominio.Vendedor;

@Repository
public interface VendedorRepositorio extends JpaRepository<Vendedor, Integer>{

}
