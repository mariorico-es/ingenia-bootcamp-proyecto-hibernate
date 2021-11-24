package com.example.demo.service;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.example.demo.dao.UserDAO;
import com.example.demo.domain.User;

@Service
public class UserService{

	private final UserDAO userDAO;
	
	public UserService(UserDAO userDAO) {
		this.userDAO = userDAO;
	}
	
	// Recuperando un usuario a través de su id usando DAO.
	public Optional<User> findById(Long id) {
		if(id == null || id == 0)
			return Optional.empty();	
		return this.userDAO.findById(id);
	}

	// Recuperando todos los usuarios usando JPA y DAO.
	public List<User> findAll() {
		return this.userDAO.findAllUsersFromEM();
		}
	
	// Recuperando todos los usuarios de un mismo nombre usando DAO.
	public List<User> findAllByName(String name) {
		return this.userDAO.findAllByName(name);
	}
	
	// Guardando un usuario usando DAO.
	public User save(User user) {
		if(user == null)
			return user;
		return this.userDAO.save(user);
	}
	
	// Actualizando un usuario existente a través de su id usando DAO.
	public User update(User user) {
		return this.userDAO.update(user);
	}
	
	// Eliminando un usuario mediante su id usando DAO.
	public Boolean deleteById(Long id) {
		if(id == null || id == 0)
			return false;	
		return this.userDAO.deleteById(id);
	}
}