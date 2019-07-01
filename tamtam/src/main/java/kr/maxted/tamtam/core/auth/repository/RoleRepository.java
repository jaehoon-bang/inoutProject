package kr.maxted.tamtam.core.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.maxted.tamtam.core.auth.model.Role;
import kr.maxted.tamtam.core.auth.role.RoleName;

/**
 * 
 * @author devkimsj
 *
 */
public interface RoleRepository extends JpaRepository<Role, Long> {

	boolean existsRoleByName(RoleName name);
	Role findByName(RoleName name);
	Role findById(Long id);

}