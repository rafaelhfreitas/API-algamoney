package com.example.algamoneyapi.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.algamoneyapi.model.Categoria;
import com.example.algamoneyapi.model.Lancamento;
import com.example.algamoneyapi.model.Pessoa;
import com.example.algamoneyapi.repository.CategoriaRepository;
import com.example.algamoneyapi.repository.LancamentoRepository;
import com.example.algamoneyapi.repository.PessoaRepository;
import com.example.algamoneyapi.service.exception.CategoriaInexistenteException;
import com.example.algamoneyapi.service.exception.PessoaInexistenteOuInativaException;

@Service
public class LancamentoService {
	
	@Autowired
	private LancamentoRepository lancamentoRepository;
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	

	public Lancamento atualizar(Long codigo, Lancamento lancamento) {
		Lancamento lancamentoSalvo = BuscarLancamentoExistente(codigo);
		
		if (!lancamento.getCategoria().equals(lancamentoSalvo.getCategoria())) {
			validarCategoria(lancamento);
		}
		
		if (!lancamento.getPessoa().equals(lancamentoSalvo.getPessoa())) {
			validarPessoa(lancamento);
		}
		
		
		BeanUtils.copyProperties(lancamento,lancamentoSalvo,"codigo");
		return lancamentoRepository.save(lancamentoSalvo);		 
	}
		
	private void validarCategoria(Lancamento lancamento) {
	    Categoria categoria = null;
		if (lancamento.getCategoria().getCodigo() != null) {
			categoria = categoriaRepository.findOne(lancamento.getCategoria().getCodigo());
		}
		
		if (categoria == null ) {
			throw new CategoriaInexistenteException();
		}
		
	}

	private void validarPessoa(Lancamento lancamento) {
		Pessoa pessoa = null;
		if (lancamento.getPessoa().getCodigo() != null) {
			pessoa = pessoaRepository.findOne(lancamento.getPessoa().getCodigo());
		}
		
		if (pessoa == null || pessoa.isInativo()) {
			throw new PessoaInexistenteOuInativaException();
		}
		
	}

	private Lancamento BuscarLancamentoExistente(Long codigo) {
		Lancamento lancamentoSalvo = lancamentoRepository.findOne(codigo);
		if (lancamentoSalvo == null) {
			throw new IllegalArgumentException();
		}
		return lancamentoSalvo;
	}


	public Lancamento salvar(Lancamento lancamento) {
		validarPessoa(lancamento);
		return lancamentoRepository.save(lancamento);
	}	

}
