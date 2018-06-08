package com.victorcruz.aula.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.victorcruz.aula.domain.Categoria;
import com.victorcruz.aula.repositories.CategoriaRepository;
import com.victorcruz.aula.services.exception.ObjectNotFoundException;


@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repo;
	
	public Categoria find(Integer id) {
		Optional<Categoria> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto nÃo encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));
	}
	
}
