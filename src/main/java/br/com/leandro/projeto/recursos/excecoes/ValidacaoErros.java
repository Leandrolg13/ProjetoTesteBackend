package br.com.leandro.projeto.recursos.excecoes;

import java.util.ArrayList;
import java.util.List;

public class ValidacaoErros extends PadraoErro{
	private static final long serialVersionUID = 1L;
	
	private List<MensagemCampo> erros = new ArrayList<>();
	
	public ValidacaoErros(Integer status, String msg, Long timeStamp) {
		super(status, msg, timeStamp);
		
	}
	
	public List<MensagemCampo> getErros(){
		return erros;
	}
	
	public void adicionaErro(String nomeCampo, String mensagem) {
		erros.add(new MensagemCampo(nomeCampo, mensagem));
	}
	
}
