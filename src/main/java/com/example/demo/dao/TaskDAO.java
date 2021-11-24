package com.example.demo.dao;

import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.example.demo.domain.Task;

@Repository
public class TaskDAO{
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	private Session session;
	
	// Recuperando una tarea a través de su id.
	public Optional<Task> findById(Long id) {
		
		try {
			var task = session.find(Task.class, id);
			if (task != null)
				return Optional.of(task);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Optional.empty();
	}
	
	// Recuperando todas las tareas usando JPA.
	public List<Task> findAllTasksFromEM() {
		return entityManager.createQuery("from Task", Task.class).getResultList();
	}
	
	// Recuperando todas las tareas de un mismo título usando Criteria con Entity Manager.
	public List<Task> findAllByTitle(String title) {
		var builder = entityManager.getCriteriaBuilder();
		var criteria = builder.createQuery(Task.class);
		var root = criteria.from(Task.class);
		criteria.select(root);
		criteria.where(builder.like(root.get("title"), "%" + title + "%"));
		return entityManager.createQuery(criteria).getResultList();
	}
	
	// Guardando una tarea usando Hibernate.
	public Task save(Task task) {
		try {
			session.beginTransaction();
			session.save(task);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return task;
	}

	// Actualizando una tarea usando Hibernate.
	public Task update(Task task) {
		try {
			session.beginTransaction();
			session.merge(task);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return task;
	}

	// Eliminando una tarea a través de su id usando Hibernate.
	public Boolean deleteById(Long id) {
		var optTask = this.findById(id);
		if (optTask.isEmpty()) {
			return false;
		}
		try {
			session.beginTransaction();
			session.delete(optTask.get());
			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return false;
	}
}