package br.com.project.resources.exceptions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;

import br.com.project.services.exceptions.ObjectNotFoundException;

class ResourceExceptionHandlerTest {

	@InjectMocks
	private ResourceExceptionHandler exceptionHandler;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testObjectNotFoundException() {
		ResponseEntity<StandardError> response = exceptionHandler
				.objectNotFoundException(new ObjectNotFoundException("objeto não encontrado"), new MockHttpServletRequest());
		
		assertNotNull(response);
		assertNotNull(response.getBody());
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
		assertEquals(ResponseEntity.class, response.getClass());
		assertEquals(StandardError.class, response.getBody().getClass());
		assertEquals("objeto não encontrado", response.getBody().getError());
		assertEquals(404, response.getBody().getStatus());
	}

	@Test
	void testDataIntegratyException() {
	
	}

}
