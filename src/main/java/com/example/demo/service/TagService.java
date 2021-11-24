package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.dao.TagDAO;
import com.example.demo.domain.Tag;

@Service
public class TagService{

	private final TagDAO tagDAO;
	
	public TagService(TagDAO tagDAO) {
		this.tagDAO = tagDAO;
	}

	// Recuperando un tag a través de su id mediante DAO.
	public Optional<Tag> findById(Long id) {
		if(id == null || id == 0) {
			return Optional.empty();
		}else {
			return this.tagDAO.findById(id);
		}
	}

	// Recuperando todos los tags usando JPA y DAO.
	public List<Tag> findAll() {
		return this.tagDAO.findAllFromEM();
	}
	
	// Recupero todos los tags de un mismo nombre usando DAO y Criteria.
	public List<Tag> findAllByNameCriteria(String name){
		return this.tagDAO.findAllByName(name);
	}
	
	// Guardando un nuevo tag usando DAO.
	public Tag save(Tag tag) {
		if(tag == null) {
			return tag;
		}else {
			return this.tagDAO.save(tag);
		}
	}
	
	// Actualizando un tag usando DAO.
	public Tag update(Tag tag) {
		return this.tagDAO.update(tag);
	}

	// Eliminando un tag existente a través de su id usando DAO.
	public Boolean deleteById(Long id) {
		if(id == null || id == 0) {
			return false;
		}else {
			return this.tagDAO.deleteById(id);
		}
	}
}