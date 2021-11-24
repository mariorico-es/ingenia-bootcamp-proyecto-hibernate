package com.example.demo.dao;

import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.example.demo.domain.User;

@Repository
public class UserDAO{
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	private Session session;
	
	// Recuperando un usuario a través de su id.
	public Optional<User> findById(Long id) {
		try {
			var user = session.find(User.class, id);
			if (user != null)
				return Optional.of(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Optional.empty();
	}	

	// Recuperando todos los usuarios usando JPA.
	public List<User> findAllUsersFromEM() {
		return entityManager.createQuery("from User", User.class).getResultList();
	}
	
	// Recuperando todos los usuarios de un mismo nombre usando Criteria con Entity Manager.
	public List<User> findAllByName(String name) {
		var builder = entityManager.getCriteriaBuilder();
		var criteria = builder.createQuery(User.class);
		var root = criteria.from(User.class);
		criteria.select(root);
		criteria.where(builder.like(root.get("name"), "%" + name + "%"));
		return entityManager.createQuery(criteria).getResultList();	
	}
	
	// Guardando un usuario usando Hibernate.
	public User save(User user) {
		try {
			session.beginTransaction();
			session.save(user);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return user;
	}
	
	// Actualizando un usuario existenten usando Hibernate.
	public User update(User user) {
		try {
			session.beginTransaction();
			session.merge(user);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return user;
	}
	
	// Eliminando un usuario a través de su id usando Hibernate.
	public Boolean deleteById(Long id) {
		var optUser = this.findById(id);
		if (optUser.isEmpty())
			return false;
		try {
			session.beginTransaction();
			session.delete(optUser.get());
			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return false;
	}
}