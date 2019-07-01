package kr.maxted.tamtam.core.auth.service;

import org.springframework.stereotype.Service;

import kr.maxted.tamtam.core.auth.model.User;
import kr.maxted.tamtam.core.auth.repository.UserRepository;

/**
 * 
 * @author devkimsj
 *
 */
@Service
public class UserService {
	private UserRepository userRepository;

	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	public void save(User user) {
		userRepository.save(user);
	}

	public boolean existUserByEmail(String email){
		return userRepository.existsUsersByEmail(email);
	}
}
