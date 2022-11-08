package br.com.project.services.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import br.com.project.models.User;
import br.com.project.models.dto.UserDTO;
import br.com.project.repositories.UserRepository;
import br.com.project.services.exceptions.DataIntegratyViolationException;
import br.com.project.services.exceptions.ObjectNotFoundException;

class UserServiceImplTest {

	private static final String SENHA = "12345";

	private static final String EMAIL = "acacio@gmail.com";

	private static final String NOME = "Acácio";

	private static final Integer _ID = 1;

	@InjectMocks
	private UserServiceImpl serviceImpl;

	@Mock
	private UserRepository repository;
	@Mock
	private ModelMapper mapper;

	private User user;

	private UserDTO userDTO;

	private Optional<User> optionalUser;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
		startUser();
	}

	@Test
	void testFindById() {
		when(repository.findById(Mockito.anyInt())).thenReturn(optionalUser);
		User response = serviceImpl.findById(_ID);

		assertNotNull(response);
		assertEquals(User.class, response.getClass());
		assertEquals(_ID, response.getId());
		assertEquals(NOME, response.getName());
		assertEquals(EMAIL, response.getEmail());
		assertEquals(SENHA, response.getSenha());

	}

	@Test
	void findByIdObjectNotFoundException() {

		when(repository.findById(Mockito.anyInt())).thenThrow(new ObjectNotFoundException("usuario não encontrado!"));

		try {
			serviceImpl.findById(_ID);
		} catch (Exception e) {
			assertEquals(ObjectNotFoundException.class, e.getClass());
			assertEquals("usuario não encontrado!", e.getMessage());
		}

	}

	@Test
	void testFindAll() {
		when(repository.findAll()).thenReturn(List.of(user));

		List<User> response = serviceImpl.findAll();

		assertNotNull(response);
		assertEquals(1, response.size());
		assertEquals(User.class, response.get(0).getClass());
		assertEquals(_ID, response.get(0).getId());
	}

	@Test
	void testCreate() {

		when(repository.save(Mockito.any())).thenReturn(user);

		User response = serviceImpl.create(userDTO);

		assertNotNull(response);
		assertEquals(User.class, response.getClass());
		assertEquals(_ID, response.getId());
		assertEquals(NOME, response.getName());
		assertEquals(EMAIL, response.getEmail());
		assertEquals(SENHA, response.getSenha());

	}

	@Test
	void testCreateDataIntegratyViolationException() {

		when(repository.findByEmail(Mockito.anyString())).thenReturn(optionalUser);

		try {
			optionalUser.get().setId(2);
			serviceImpl.create(userDTO);
		} catch (Exception e) {
			assertEquals(DataIntegratyViolationException.class, e.getClass());
			assertEquals("Email já cadastrado!", e.getMessage());
		}

	}

	@Test
	void testUpdate() {

		when(repository.save(Mockito.any())).thenReturn(user);

		User response = serviceImpl.update(userDTO);

		assertNotNull(response);
		assertEquals(User.class, response.getClass());
		assertEquals(_ID, response.getId());
		assertEquals(NOME, response.getName());
		assertEquals(EMAIL, response.getEmail());
		assertEquals(SENHA, response.getSenha());

	}
	
	
	@Test
	void testUpdateDataIntegratyViolationException() {

		when(repository.findByEmail(Mockito.anyString())).thenReturn(optionalUser);

		try {
			optionalUser.get().setId(2);
			serviceImpl.create(userDTO);
		} catch (Exception e) {
			assertEquals(DataIntegratyViolationException.class, e.getClass());
			assertEquals("Email já cadastrado!", e.getMessage());
		}

	}

	@Test
	void testDeleteSucess() {
		
		when(repository.findById(Mockito.anyInt())).thenReturn(optionalUser);
		doNothing().when(repository).deleteById(Mockito.anyInt());
		serviceImpl.delete(_ID);
		verify(repository, times(1)).deleteById(Mockito.anyInt());
		
	}

	@Test
	void testDeleteFail() {
	
		
	}

	
	private void startUser() {
		user = new User(_ID, NOME, EMAIL, SENHA);
		userDTO = new UserDTO(1, NOME, EMAIL, SENHA);
		optionalUser = Optional.of(new User(1, NOME, EMAIL, SENHA));
	}

}
