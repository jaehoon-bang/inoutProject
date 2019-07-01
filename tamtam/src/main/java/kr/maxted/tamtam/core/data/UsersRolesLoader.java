package kr.maxted.tamtam.core.data;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import kr.maxted.tamtam.core.auth.model.Role;
import kr.maxted.tamtam.core.auth.model.User;
import kr.maxted.tamtam.core.auth.role.RoleName;
import kr.maxted.tamtam.core.auth.service.RoleService;
import kr.maxted.tamtam.core.auth.service.UserService;

/**
 * 
 * @author devkimsj
 *
 */
@Component
public class UsersRolesLoader implements ApplicationListener<ContextRefreshedEvent> {

	private boolean alreadySetup = false;
	private RoleService roleService;
	private UserService userService;

	public UsersRolesLoader(RoleService roleService, UserService userService) {
		this.roleService = roleService;
		this.userService = userService;
	}

	@Override
	@Transactional
	public void onApplicationEvent(final ContextRefreshedEvent contextRefreshedEvent) {

		if(alreadySetup){
			return;
		}

		//region Creating roles
		//================================================================================
		List<Role> adminRoles = Collections.singletonList(createRoleIfNotFound(RoleName.ROLE_ADMIN));
		List<Role> userRoles = Collections.singletonList(createRoleIfNotFound(RoleName.ROLE_USER));
		//================================================================================
		//endregion

		//region Creating users
		//================================================================================
		createUserIfNotFound("admin", "$2a$08$lDnHPz7eUkSi6ao14Twuau08mzhWrL4kyZGGU5xfiGALO/Vxd5DOi",
							"admin", "admin", "admin@admin.com",
							true, new Date(1514764800000L), adminRoles);

		createUserIfNotFound("sksk", "$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC",
							"sksk", "sksk", "sksk@maxted.com",
							true, new Date(1514764800000L), userRoles);

		createUserIfNotFound("disabled", "$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC",
							"user", "user", "disabled@user.com",
							false, new Date(1514764800000L), userRoles);

		//================================================================================
		//endregion

		alreadySetup = true;
	}

	@Transactional
	Role createRoleIfNotFound(RoleName name) {
		boolean existsRoleByName = roleService.existsRoleByName(name);
		if (!existsRoleByName) {
			Role role = new Role(name);
			roleService.save(role);
			return role;
		}
		return null;
	}

	@Transactional
	void createUserIfNotFound(String username, String password, String firstname, String lastname, String email, boolean enabled, Date lastPasswordResetDate, List<Role> roles) {
		boolean existsUsersByEmail = userService.existUserByEmail(email);
		if (!existsUsersByEmail) {
			User user = new User();
			user.setUsername(username);
			user.setPassword(password);
			user.setFirstname(firstname);
			user.setLastname(lastname);
			user.setEmail(email);
			user.setEnabled(enabled);
			user.setLastPasswordResetDate(lastPasswordResetDate);
			user.setRoles(roles);
			userService.save(user);
		}
	}
	
}