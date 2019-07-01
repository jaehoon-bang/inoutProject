package kr.maxted.tamtam.core.auth.service;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.maxted.tamtam.core.auth.model.Role;
import kr.maxted.tamtam.core.auth.repository.RoleRepository;
import kr.maxted.tamtam.core.auth.role.RoleName;

/**
 * 
 * @author devkimsj
 *
 */
@Service
public class RoleService {
	private RoleRepository roleRepository;

	public RoleService(RoleRepository roleRepository) {
		this.roleRepository = roleRepository;
	}

	public List<Role> findAll(){return roleRepository.findAll();}

	public Role findByName(RoleName name) {
		return roleRepository.findByName(name);
	}

	public boolean existsRoleByName (RoleName name){
		return roleRepository.existsRoleByName(name);
	}

	public Role findById(Long id) {
		return roleRepository.findById(id);
	}

	public void save(Role role) {
	}

}