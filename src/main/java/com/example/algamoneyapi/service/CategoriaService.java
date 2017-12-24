package com.example.algamoneyapi.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.example.algamoneyapi.model.Categoria;
import com.example.algamoneyapi.repository.CategoriaRepository;

@Service
public class CategoriaService {
	
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public Categoria atualizar(Long codigo, Categoria categoria) {
		Categoria categoriaSalva = BuscarCategoriaPeloCodigo(codigo);
		BeanUtils.copyProperties(categoria,categoriaSalva,"codigo");
		return categoriaRepository.save(categoriaSalva);
		 
	}


	
	public Categoria BuscarCategoriaPeloCodigo(Long codigo) {
		Categoria categoriaSalva = categoriaRepository.findOne(codigo);
		if (categoriaSalva  == null) {
			throw new EmptyResultDataAccessException(1);
		}
		return categoriaSalva;
	}
	

}
