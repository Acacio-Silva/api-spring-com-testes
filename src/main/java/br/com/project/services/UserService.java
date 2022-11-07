package br.com.project.services;

import java.util.List;

import br.com.project.models.User;

public interface UserService {
	
	User findById(Integer id);
	List<User> findAll();

}
