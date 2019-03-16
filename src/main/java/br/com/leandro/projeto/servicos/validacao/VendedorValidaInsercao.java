package br.com.leandro.projeto.servicos.validacao;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.leandro.projeto.dominio.Vendedor;
import br.com.leandro.projeto.recursos.excecoes.MensagemCampo;
import br.com.leandro.projeto.repositorio.VendedorRepositorio;

public class VendedorValidaInsercao implements ConstraintValidator<VendedorInsercao, Vendedor> {

	@Autowired
	private VendedorRepositorio vendedorRepositorio;
	
	@Override
	public void initialize(VendedorInsercao ann) {
	}

	@Override
	public boolean isValid(Vendedor obj, ConstraintValidatorContext context) {
		
		List<MensagemCampo> list = new ArrayList<>();
		
		if(vendedorRepositorio != null) {
			Vendedor aux = vendedorRepositorio.findByNome(obj.getNome());
			if (aux != null) {
				list.add(new MensagemCampo("nome", "Já existe cadastro com esse nome"));
			}
		}
		for (MensagemCampo e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMensagem()).addPropertyNode(e.getNomeCampo())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}
