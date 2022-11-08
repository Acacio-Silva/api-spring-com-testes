package br.com.project.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.project.models.dto.UserDTO;
import br.com.project.services.UserService;

@RestController
@RequestMapping(value = "/user")
public class UserResource {
	
	private static final String ID = "/{id}";

	@Autowired
	private UserService service;
	
	@Autowired
	private ModelMapper mapper;
		
	@GetMapping(value = ID)
	public ResponseEntity<UserDTO> findById(@PathVariable Integer id){
		return ResponseEntity.ok().body(mapper.map(service.findById(id), UserDTO.class));
	}
	
	@GetMapping
	public ResponseEntity<List<UserDTO>> findAll(){
		return ResponseEntity.ok()
				.body(service.findAll()
						.stream().map(x -> mapper.map(x, UserDTO.class)).collect(Collectors.toList()));
	}
	
	@PostMapping
	public ResponseEntity<UserDTO> create(@RequestBody UserDTO userDTO){
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path(ID).buildAndExpand(service.create(userDTO)).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping(value = ID)
	public ResponseEntity<UserDTO> update(@PathVariable Integer id, @RequestBody UserDTO dto){
		dto.setId(id);
		return ResponseEntity.ok().body(mapper.map(service.update(dto), UserDTO.class));
	}
	
	@DeleteMapping(value = ID)
	public ResponseEntity<UserDTO> delete(@PathVariable Integer id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

}
