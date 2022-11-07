package br.com.project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.project.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

	
}
