package br.com.project.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.project.models.User;
import br.com.project.repositories.UserRepository;
import br.com.project.services.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository repository;

	@Override
	public User findById(Integer id) {
		Optional<User> obj = repository.findById(id);
		return obj.orElse(null);
	}

	
}
