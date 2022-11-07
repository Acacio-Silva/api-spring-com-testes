package br.com.project.services.impl;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.project.models.User;
import br.com.project.models.dto.UserDTO;
import br.com.project.repositories.UserRepository;
import br.com.project.services.UserService;
import br.com.project.services.exceptions.ObjectNotFoundException;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository repository;

	@Autowired
	private ModelMapper mapper;
	
	@Override
	public User findById(Integer id) {
		Optional<User> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Usuario não encontrado"));
	}
	
	public List<User> findAll(){
		return repository.findAll();
	}

	@Override
	public User create(UserDTO obj) {
		return repository.save(mapper.map(obj, User.class));
	}

	
}
