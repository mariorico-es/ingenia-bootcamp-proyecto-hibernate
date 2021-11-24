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
import com.example.demo.domain.User;
import com.example.demo.service.UserService;

@RestController
@RequestMapping("/api")
public class UserController {
	
	private UserService userService;
	
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	/**
	 * http://localhost:8080/api/user/1
	 */
	@GetMapping("/user/{id}")
	public ResponseEntity<User> findById(@PathVariable Long id) {
		var optUser = this.userService.findById(id);
		if (optUser.isPresent())
			return ResponseEntity.ok(optUser.get());
		return ResponseEntity.notFound().build();
	
	}
	
	/**
	 * http://localhost:8080/api/users
	 */
	@GetMapping("/users")
	public List<User> findAll() {
		return this.userService.findAll();
	}
	
	/**
	 * http://localhost:8080/api/users/name/Paloma
	 */
	@GetMapping("users/name/{name}")
	public List<User> findAllByName(@PathVariable String name) {
		return this.userService.findAllByName(name);
	}
	
	/**
	 * http://localhost:8080/api/user/create
	 */
	@PostMapping("/user/create")
	public ResponseEntity<User> create(@RequestBody User user) {
		if (user.getId() != null) {
			return ResponseEntity.badRequest().build();
		}

		/**
		 * http://localhost:8080/api/user/save
		 */
		var result = this.userService.save(user);
		if (result != null && result.getId() != null) {
			return ResponseEntity.ok(result);
		}else {
			return ResponseEntity.internalServerError().build();
		}
	}
	
	/**
	 * http://localhost:8080/api/user/update
	 */
	@PutMapping("/user/update")
	public ResponseEntity<User> update(@RequestBody User user) {
		if (user.getId() == null) {
			return ResponseEntity.badRequest().build();
		}else {
			return ResponseEntity.ok(this.userService.update(user));
		}
	}
	
	/**
	 * http://localhost:8080/api/user/delete/1
	 */
	@DeleteMapping("user/delete/{id}")
	public ResponseEntity<User> delete(@PathVariable Long id) {
		if(Boolean.TRUE.equals(this.userService.deleteById(id))) {
			return ResponseEntity.noContent().build();
		}else {
			return ResponseEntity.internalServerError().build();
		}	
	}
}