package com.example.demo.service;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.example.demo.dao.TaskDAO;
import com.example.demo.domain.Task;

@Service
public class TaskService{
	
	private final TaskDAO taskDAO;
	
	public TaskService(TaskDAO taskDAO) {
		this.taskDAO = taskDAO;
	}

	// Recuperando una tarea mediante su id usando DAO.
	public Optional<Task> findById(Long id) {
		if(id == null || id == 0)
			return Optional.empty();
		return this.taskDAO.findById(id);
	}

	// Recuperando todas las tareas usando JPA y DAO.
	public List<Task> findAll() {
		return this.taskDAO.findAllTasksFromEM();

	}
	
	// Recuperando todas las tareas de un mismo título usando DAO.
	public List<Task> findAllByTitle(String titulo) {
		return this.taskDAO.findAllByTitle(titulo);
	}

	// Guardando una tarea usando DAO.
	public Task save(Task task) {
		if(task == null)
			return task;
		return this.taskDAO.save(task);
	}
	
	// Actualizando una tarea existente mediante su id usando DAO.
	public Task update(Task task) {
		return this.taskDAO.update(task);
	}
	
	// Eliminando una tarea usando DAO.
	public Boolean deleteById(Long id) {
		if(id == null || id == 0)
			return false;
		return this.taskDAO.deleteById(id);
	}
}