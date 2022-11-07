package br.com.project.services;

import java.util.List;

import br.com.project.models.User;
import br.com.project.models.dto.UserDTO;

public interface UserService {
	
	User findById(Integer id);
	List<User> findAll();
	User create (UserDTO obj);
	User update (UserDTO obj);
	void delete (Integer id);

}
