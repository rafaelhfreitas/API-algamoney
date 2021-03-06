package com.example.algamoneyapi.resource;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.algamoneyapi.event.RecursoCriadoEvent;
import com.example.algamoneyapi.model.Pessoa;
import com.example.algamoneyapi.repository.PessoaRepository;
import com.example.algamoneyapi.repository.filter.PessoaFilter;
import com.example.algamoneyapi.service.PessoaService;



@RestController
@RequestMapping("/pessoas")
public class PessoaResource {
	
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@Autowired
	private PessoaService pessoaService;
	
	// Mapeamento antigo do metodo GET
	//@GetMapping
	//public List<Pessoa> listar() {
	//	return pessoaRepository.findAll();
	//}	
	
	
	@PostMapping
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_PESSOA') and #oauth2.hasScope('write')")
	public ResponseEntity<Pessoa> criar(@Valid @RequestBody Pessoa pessoa, HttpServletResponse response) {
		Pessoa pessoaSalva = pessoaRepository.save(pessoa);	
				
		publisher.publishEvent(new RecursoCriadoEvent(this, response, pessoaSalva.getCodigo() ));
		
		return ResponseEntity.status(HttpStatus.CREATED).body(pessoaSalva);		   				
	}	
	
	
	@GetMapping
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_PESSOA') and #oauth2.hasScope('read')")
	public Page<Pessoa> pesquisar(PessoaFilter pessoaFilter, Pageable pageable ) {
		return pessoaRepository.filtrar(pessoaFilter, pageable);
		//Solução usando o metodo default do JPA Repository
		//return pessoaRepository.findByNomeContaining(nome, pageable)
	}

	
//  Aqui é o mapeamento usando o JPA repositoy para fazer a pesquisa por nome e utilizando a tag @RequestParam para pegar o nome passado	
//	@GetMapping
//	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_PESSOA')")
//	public Page<Pessoa> pesquisar(@RequestParam(required = false, defaultValue = "%") String nome, Pageable pageable) {
//		return pessoaRepository.findByNomeContaining(nome, pageable);
//	}	
	

	
	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PreAuthorize("hasAuthority('ROLE_REMOVER_PESSOA') and #oauth2.hasScope('write')")
	public void remover(@PathVariable Long codigo)  {
		pessoaRepository.delete(codigo);
		
	}
			
	@GetMapping("/{codigo}")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_PESSOA') and #oauth2.hasScope('read')")
	public ResponseEntity<Pessoa> buscarPeloCodigo(@PathVariable Long codigo) {
		 Pessoa pessoa = pessoaRepository.findOne(codigo);
		 return pessoa != null ? ResponseEntity.ok(pessoa) : ResponseEntity.notFound().build();
	}		
	
	
	@PutMapping("/{codigo}")
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_PESSOA') and #oauth2.hasScope('write')")
    public ResponseEntity<Pessoa> atualizar(@PathVariable Long codigo, @Valid @RequestBody Pessoa pessoa){
        Pessoa pessoaSalva = pessoaService.atualizar(codigo, pessoa);
		return ResponseEntity.ok(pessoaSalva);
	}
	
	
	@PutMapping("/{codigo}/ativo")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_PESSOA') and #oauth2.hasScope('write')")
	public void atualizarPropriedadeAtivo(@PathVariable Long codigo,@RequestBody Boolean ativo) {
		pessoaService.AtualizarPropriedadeAtivo(codigo,ativo);
		
	}

}
