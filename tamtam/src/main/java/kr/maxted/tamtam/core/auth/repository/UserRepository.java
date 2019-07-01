package kr.maxted.tamtam.core.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kr.maxted.tamtam.core.auth.model.User;

/**
 * 
 * @author devkimsj
 *
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long > {

	User findByUsername(String username);
	
	boolean existsUsersByEmail(String email);

	User findByEmail(String email);
	
}
    