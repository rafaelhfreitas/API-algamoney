package com.example.algamoneyapi.repository.pessoa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import com.example.algamoneyapi.model.Endereco_;
import com.example.algamoneyapi.model.Pessoa;
import com.example.algamoneyapi.model.Pessoa_;
import com.example.algamoneyapi.repository.filter.PessoaFilter;

public class PessoaRepositoryImpl implements PessoaRepositoryQuery {
	
	
	@PersistenceContext
    private EntityManager manager;
	
	@Override
	public Page<Pessoa> filtrar (PessoaFilter pessoaFilter , Pageable pageable) {
	  CriteriaBuilder builder = manager.getCriteriaBuilder();
	  CriteriaQuery<Pessoa> criteria = builder.createQuery(Pessoa.class);
	  Root<Pessoa> root = criteria.from(Pessoa.class);
	  
	  Predicate[] predicates = criarRestricoes(pessoaFilter, builder, root);
	  criteria.where(predicates);
	  
	  TypedQuery<Pessoa> query = manager.createQuery(criteria);
	  
	  adicionarRestricoesPaginacao(query, pageable);
	  return new PageImpl<>(query.getResultList(),pageable, total(pessoaFilter));
	}
	
	
	private Predicate[] criarRestricoes(PessoaFilter pessoaFilter, CriteriaBuilder builder,
			Root<Pessoa> root) {		
		
		List<Predicate> predicates = new ArrayList<>();
		
        if (!StringUtils.isEmpty(pessoaFilter.getNome())) {
        	predicates.add(builder.like(
        			builder.lower(root.get(Pessoa_.nome)), "%" + pessoaFilter.getNome().toLowerCase() + "%"));
        } 
        
        
//        if(!StringUtils.isEmpty(pessoaFilter.getEstado())){
//			predicates.add(builder.like(
//					builder.lower(root.get(Endereco_.estado), pessoaFilter.getEstado())));
//        }
        
        
        if(!StringUtils.isEmpty(pessoaFilter.isAtivo())){
			predicates.add(builder.isTrue(builder.equal(root.get(Pessoa_.ativo), pessoaFilter.isAtivo())));
        }

		return predicates.toArray(new Predicate[predicates.size()]);
	}
	

	private void adicionarRestricoesPaginacao(TypedQuery<?> query, Pageable pageable) {
		int paginaAtual = pageable.getPageNumber();
		int totalRegistrosPorPagina = pageable.getPageSize();
		int primeiroRegistroDaPagina = paginaAtual * totalRegistrosPorPagina;
		
		query.setFirstResult(primeiroRegistroDaPagina);
		query.setMaxResults(totalRegistrosPorPagina);
	}

	private Long total(PessoaFilter pessoaFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<Pessoa> root = criteria.from(Pessoa.class);
		
		
		Predicate[] predicates = criarRestricoes(pessoaFilter, builder, root);
		criteria.where(predicates);
		
		
		criteria.select(builder.count(root));
		return manager.createQuery(criteria).getSingleResult();
	}

}
