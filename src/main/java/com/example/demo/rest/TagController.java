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
import com.example.demo.domain.Tag;
import com.example.demo.service.TagService;

@RestController
@RequestMapping("/api")
public class TagController {
	
	private TagService tagService;
	
	public TagController(TagService tagService) {
		this.tagService = tagService;
	}
	/**
	 * http://localhost:8080/api/tag/1
	 */
	@GetMapping("/tag/{id}")
	public ResponseEntity<Tag> findById(@PathVariable Long id) {
		var optTag = this.tagService.findById(id);
		if (optTag.isPresent()) {
			return ResponseEntity.ok(optTag.get());
		}else {
			return ResponseEntity.notFound().build();
		}
	}
	
	/**
	 * http://localhost:8080/api/tags
	 */
	@GetMapping("/tags")
	public List<Tag> findAll() {
		return this.tagService.findAll();
	}
	
	/**
	 * http://localhost:8080/api/tag/name/Backend
	 * http://localhost:8080/api/tag/name/Frontend
	 */
	@GetMapping("tag/name/{name}")
	public List<Tag> findAllByName(@PathVariable String name) {
		return this.tagService.findAllByNameCriteria(name);
	}
	
	/**
	 * http://localhost:8080/api/tag/create
	 * Pasando un tag para crear sin id, en el body en formato Json.
	 */
	@PostMapping("/tag/create")
	public ResponseEntity<Tag> create(@RequestBody Tag tag) {
		if(tag.getId() != null) {
			return ResponseEntity.badRequest().build();
		}
		Tag result = this.tagService.save(tag);
		if (result != null && result.getId() != null)
			return ResponseEntity.ok(result);
		return ResponseEntity.internalServerError().build();
	}
	
	/**
	 * http://localhost:8080/api/tag/update
	 * Pasando un tag para crear con id que exista, en el body en formato Json.
	 */
	@PutMapping("/tag/update")
	public ResponseEntity<Tag> update(@RequestBody Tag tag) {
			if (tag.getId() == null) {
				return ResponseEntity.badRequest().build();
			}else {
				return ResponseEntity.ok(this.tagService.update(tag));
			}
	}

	/**
	 * http://localhost:8080/api/tag/delete/1
	 */
	@DeleteMapping("tag/delete/{id}")
	public ResponseEntity<Tag> delete(@PathVariable Long id) {
		if(Boolean.TRUE.equals(this.tagService.deleteById(id))) {
			return ResponseEntity.noContent().build();
		}else {
			return ResponseEntity.internalServerError().build();
		}
	}
}