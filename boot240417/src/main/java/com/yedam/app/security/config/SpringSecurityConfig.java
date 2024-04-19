package com.yedam.app.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {
	// 암호화 처리
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	// 메모리상 인증정보 활용 <- 실개발에선 사용X(test용)
	//@Bean
	public InMemoryUserDetailsManager inMemoryUserDetailsService() {
		UserDetails user = User.builder()
							.username("user1")
							.password(passwordEncoder().encode("1234"))
							.roles("USER", "SALES") //== .authorities("ROLE_USER", "ROLE_SALES")
							.build();
		return new InMemoryUserDetailsManager(user);
	}
	
	//인증 및 인가 설정
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		http.authorizeHttpRequests()
//							 인가경로			인가대상
				.antMatchers("/", "/all").permitAll()
//				.antMatchers("/user/**").hasRole("USER") //ROLE_USER
				.antMatchers("/user/**").hasAnyRole("USER", "ADMIN") //ROLE_USER
				.antMatchers("/admin/**").hasAuthority("ROLE_ADMIN")
				.anyRequest().authenticated() //;
//					모든경로대상
/*		permitAll -> 모든대상허용, hasRole -> 특정롤요구, 
		hasAnyrole -> 복수의 특정롤요구(LIKE), 									*/
			.and()
			.formLogin()
				.defaultSuccessUrl("/all")
			.and()
			.logout()
				.logoutSuccessUrl("/all");
		
//		http.formLogin().defaultSuccessUrl("/all");
//		http.logout();
		http.csrf();//.disable();
		
		return http.build();
	}
}
