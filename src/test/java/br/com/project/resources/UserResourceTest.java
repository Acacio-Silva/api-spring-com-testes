package br.com.project.resources;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import br.com.project.models.User;
import br.com.project.models.dto.UserDTO;
import br.com.project.services.impl.UserServiceImpl;

@SpringBootTest
class UserResourceTest {
	
	private static final String SENHA = "12345";

	private static final String EMAIL = "acacio@gmail.com";

	private static final String NOME = "Acácio";

	private static final Integer _ID = 1;

	private User user;

	private UserDTO userDTO;
	
	@InjectMocks
	private UserResource resource;
	@Mock
	private UserServiceImpl service;
	@Mock
	private ModelMapper mapper;
	
	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
		startUser();
	}

	@Test
	void testFindByIdSucess() {
		when(service.findById(anyInt())).thenReturn(user);
		when(mapper.map(any(), any())).thenReturn(userDTO);
		
		ResponseEntity<UserDTO> response = resource.findById(_ID);
		
		assertNotNull(response);
		assertNotNull(response.getBody());
		assertEquals(ResponseEntity.class, response.getClass());
		assertEquals(UserDTO.class, response.getBody().getClass());
		
		assertEquals(_ID, response.getBody().getId());
		assertEquals(NOME, response.getBody().getName());
		assertEquals(EMAIL, response.getBody().getEmail());
		assertEquals(SENHA, response.getBody().getSenha());
		
	}

	@Test
	void testFindAll() {
	}

	@Test
	void testCreate() {
	}

	@Test
	void testUpdate() {
	}

	@Test
	void testDelete() {
	}
	
	private void startUser() {
		user = new User(_ID, NOME, EMAIL, SENHA);
		userDTO = new UserDTO(1, NOME, EMAIL, SENHA);
	
	}

}
