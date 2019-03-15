package br.com.leandro.projeto.recursos.excecoes;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.leandro.projeto.servicos.excecoes.ObjetoNaoEncontrado;

@ControllerAdvice
public class ManipuladorExcecoes {

	@ExceptionHandler(ObjetoNaoEncontrado.class)
	public ResponseEntity<PadraoErro> objetoNaoEncontrado(ObjetoNaoEncontrado obj, HttpServletRequest request){
		PadraoErro erro = new PadraoErro(HttpStatus.NOT_FOUND.value(), obj.getMessage(), System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<PadraoErro> validacaoCampo(MethodArgumentNotValidException obj, HttpServletRequest request){
		
		ValidacaoErros erro = new ValidacaoErros(HttpStatus.BAD_REQUEST.value(), "Erro de validação", System.currentTimeMillis());
		for (FieldError x : obj.getBindingResult().getFieldErrors()) {
			erro.adicionaErro(x.getField(), x.getDefaultMessage());
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
	}
}
