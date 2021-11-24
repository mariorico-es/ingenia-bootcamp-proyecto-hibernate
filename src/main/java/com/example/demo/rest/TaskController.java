package com.example.demo.rest;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.domain.Task;
import com.example.demo.service.TaskService;

@RestController
@RequestMapping("/api")
public class TaskController {
	
	private TaskService taskService;
	
	public TaskController(TaskService taskService) {
		this.taskService = taskService;
	}
	
	/**
	 * http://localhost:8080/api/task/1
	 */
	@GetMapping("/task/{id}")
	public ResponseEntity<Task> findById(@PathVariable Long id) {
		var optTask = this.taskService.findById(id);
		if (optTask.isPresent()) {
			return ResponseEntity.ok(optTask.get());
		}else {
			return ResponseEntity.notFound().build();
		}
	}
	
	/**
	 * http://localhost:8080/api/tasks
	 */
	@GetMapping("/tasks")
	public List<Task> findAll() {
		return this.taskService.findAll();
	}
	
	/**
	 * http://localhost:8080/api/tasks/title/Backend BBVA
	 * http://localhost:8080/api/tasks/title/Frontend Unicaja
	 * http://localhost:8080/api/tasks/title/...
	 */
	 @GetMapping("tasks/title/{title}")
	 public List<Task> findAllByTitle(@PathVariable String title) {
		 return this.taskService.findAllByTitle(title);
	 }

	/**
	 * http://localhost:8080/api/task/create
	 */
	@PostMapping("/task/create")
	public ResponseEntity<Task> create(@RequestBody Task task) {
		if (task.getId() != null) {
			return ResponseEntity.badRequest().build();
		}
		var result = this.taskService.save(task);
		if (result != null && result.getId() != null) {
			return ResponseEntity.ok(result);
		}else {
			return ResponseEntity.internalServerError().build();
		}
	}
	
	/**
	 * http://localhost:8080/api/tasks
	 */
	@PutMapping("/task/update")
	public ResponseEntity<Task> update(@RequestBody Task task) {
		if (task.getId() == null) {
			return ResponseEntity.badRequest().build();
		}else {
			return ResponseEntity.ok(this.taskService.update(task));
		}
	}
	
	/**
	 * http://localhost:8080/api/task/delete/1
	 */
	 @DeleteMapping("/task/delete/{id}")
	 public ResponseEntity<Task> delete(@PathVariable Long id) {
		 if(Boolean.TRUE.equals(this.taskService.deleteById(id))){
			 return ResponseEntity.noContent().build();
		 }else {
			 return ResponseEntity.internalServerError().build();
		 }
	 }
}