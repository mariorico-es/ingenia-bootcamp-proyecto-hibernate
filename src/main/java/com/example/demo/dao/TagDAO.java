package com.example.demo.dao;

import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.example.demo.domain.Tag;

@Repository
public class TagDAO{
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	private Session session;
	
	// Recuperando una etiqueta a través de su id.
	public Optional<Tag> findById(Long id) {
		try {
			var tag = session.find(Tag.class, id);
			if (tag != null) {
				return Optional.of(tag);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Optional.empty();
	}
	
	// Recuperando todas las etiquetas usando JPA.
	public List<Tag> findAllFromEM() {
		return entityManager.createQuery("from Tag", Tag.class).getResultList();
	}
	
	// Recuperando todas las etiquetas con un mismo nombre usando Criteria con Entity Manager.
	public List<Tag> findAllByName(String name) {
		var builder = entityManager.getCriteriaBuilder();
		var criteria = builder.createQuery(Tag.class);
		var root = criteria.from(Tag.class);
		criteria.select(root);
		criteria.where(builder.like(root.get("name"), "%" + name + "%"));
		return entityManager.createQuery(criteria).getResultList();
	}

	// Guardando una etiqueta usando Hibernate.
	public Tag save(Tag tag) {
		try {
			session.beginTransaction();
			session.save(tag);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return tag;
	}
	
	// Actualizando una etiqueta usando Hibernate.
	public Tag update(Tag tag) {
		try {
			session.beginTransaction();
			session.merge(tag);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return tag;
	}

	// Eliminando una etiqueta a través de su id usando Hibernate.
	public Boolean deleteById(Long id) {
		var optTag = this.findById(id);
		if (optTag.isEmpty()) {
			return false;
		}
		try {
			session.beginTransaction();
			session.delete(optTag.get());
			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return false;
	}	
}