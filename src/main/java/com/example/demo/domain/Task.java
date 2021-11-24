package com.example.demo.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="it_tasks")
public class Task implements Serializable {

	private static final long serialVersionUID = 1L;
	
	// Propiedades
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String title;
	@Column(name="description", length = 350)
	private String description;
	private Boolean finished;
	private LocalDateTime deliveryDate;
	
	// Relaciones
	@JsonIgnore
	@ManyToOne
    @JoinColumn(name="user_id")
    private User user;
	
	@JsonIgnore
	@ManyToMany
	@JoinTable(name = "tasks_tags", 
			   joinColumns = @JoinColumn(name="id_task", referencedColumnName = "id"),
			   inverseJoinColumns = @JoinColumn(name="id_tag", referencedColumnName = "id"))
	private List<Tag> tags = new ArrayList<>();
	
	// Constructor
	public Task() {/*constructor vacío que usará Hibernate*/}
	
	// Getters & Setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getFinished() {
		return finished;
	}

	public void setFinished(Boolean finished) {
		this.finished = finished;
	}

	public LocalDateTime getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(LocalDateTime deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Tag> getTags() {
		return tags;
	}

	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}	
}