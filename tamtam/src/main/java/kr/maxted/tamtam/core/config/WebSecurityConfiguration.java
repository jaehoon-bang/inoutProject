package kr.maxted.tamtam.core.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import kr.maxted.tamtam.core.auth.jwt.JwtAuthenticationEntryPoint;
import kr.maxted.tamtam.core.auth.jwt.JwtAuthorizationTokenFilter;
import kr.maxted.tamtam.core.auth.jwt.JwtCompanyService;
import kr.maxted.tamtam.core.auth.jwt.JwtEmployeeService;
import kr.maxted.tamtam.core.auth.jwt.JwtTokenUtil;

/**
 * 
 * @author devkimsj
 *
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

	private final JwtAuthenticationEntryPoint unauthorizedHandler;
	private final JwtTokenUtil jwtTokenUtil;
	private final JwtEmployeeService jwtEmployeeService;
	private final JwtCompanyService jwtCompanyService;

	@Value("${jwt.header}")
	private String tokenHeader;

	@Value("${jwt.route.authentication.path}")
	private String authenticationPath;

	@Autowired
	public WebSecurityConfiguration(JwtAuthenticationEntryPoint unauthorizedHandler, JwtTokenUtil jwtTokenUtil, JwtEmployeeService jwtEmployeeService, JwtCompanyService jwtCompanyService) {
		this.unauthorizedHandler = unauthorizedHandler;
		this.jwtTokenUtil = jwtTokenUtil;
		this.jwtEmployeeService = jwtEmployeeService;
		this.jwtCompanyService = jwtCompanyService;
    }

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth
		.userDetailsService(jwtEmployeeService)
		.passwordEncoder(passwordEncoderBean());
	}

	@Bean
	public PasswordEncoder passwordEncoderBean() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity
			// we don't need CSRF because our token is invulnerable
			.csrf().disable()
			.exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
			// don't create session
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
			.authorizeRequests()
			.antMatchers("/auth/**").permitAll()
			.antMatchers("/regist/**").permitAll()
			.anyRequest().authenticated();

		// 사용자 정의 JWT 기반 보안 필터
		JwtAuthorizationTokenFilter authenticationTokenFilter = new JwtAuthorizationTokenFilter(userDetailsService(), jwtEmployeeService, jwtTokenUtil, tokenHeader, jwtCompanyService);
		httpSecurity
			.addFilterBefore(authenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);

		// 페이지 캐싱 사용 안 함
		httpSecurity
			.headers()
			.frameOptions().sameOrigin()  // required to set for H2 else H2 Console will be blank.
			.cacheControl();
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		// AuthenticationTokenFilter가 다음 경로는 무시
		web
			.ignoring()
			.antMatchers(
					HttpMethod.POST,
					authenticationPath,
					"/regist",
					"/regist/*"
					)

			// 익명 리소스 요청 허용
			.and()
			.ignoring()
			.antMatchers(
					HttpMethod.GET,
					"/",
					"/*.html",
					"/*.jsp",
					"/admin/*",
					"/admin/**/*",
					"/admin/**/**/*",
					"/regist",
					"/regist/*",
					"/regist/**/*",
					"/regist/**/**/*",
					"/favicon.ico",
					"/**/*.html",
					"/**/*.jsp",
					"/**/*.css",
					"/**/*.js"
					);
	}
}