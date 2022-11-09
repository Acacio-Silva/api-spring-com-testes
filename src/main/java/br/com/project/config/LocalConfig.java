package br.com.project.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.com.project.models.User;
import br.com.project.repositories.UserRepository;

@Configuration
@Profile("local")
public class LocalConfig{

	@Autowired
	private UserRepository userRepository;
	
	@Bean
	public void iniciaDB(){
		
		User user1 = new User(null, "Acacio", "acacio@email.com", "12345");
		User user2 = new User(null, "Francisco", "francisco@email.com", "1234567");
		
		userRepository.saveAll(List.of(user1, user2));
	}

	
}
