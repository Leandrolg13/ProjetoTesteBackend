package br.com.leandro.projeto.servicos.validacao;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.leandro.projeto.dominio.Cliente;
import br.com.leandro.projeto.recursos.excecoes.MensagemCampo;
import br.com.leandro.projeto.repositorio.ClienteRepositorio;

public class ClienteValidaInsercao implements ConstraintValidator<ClienteInsercao, Cliente> {

	@Autowired
	private ClienteRepositorio clienteRepositorio;
	
	@Override
	public void initialize(ClienteInsercao ann) {
	}

	@Override
	public boolean isValid(Cliente obj, ConstraintValidatorContext context) {
		
		List<MensagemCampo> list = new ArrayList<>();
		
		if(clienteRepositorio != null) {
			Cliente aux = clienteRepositorio.findByNome(obj.getNome());
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
