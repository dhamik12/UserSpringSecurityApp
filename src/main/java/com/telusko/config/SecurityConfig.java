package com.telusko.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	@Autowired
	private UserDetailsService userDetailsService;

	//Configuring as stateless
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		System.out.println("securityFilterChain method is running.");
		http.csrf(cus->cus.disable());
		http.authorizeHttpRequests(authorizeHttp->authorizeHttp.requestMatchers("user/add-user")
				.permitAll().anyRequest().authenticated());
		http.httpBasic(Customizer.withDefaults());
		http.sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		return http.build();
	}
	
//	@Bean
//	public UserDetailsService userDetails() {
//		
//		UserDetails user=User.withDefaultPasswordEncoder()
//				.username("Shivam")
//				.password("shiva100")
//				.roles("user")
//				.build();
//		
//		UserDetails user2=User.withDefaultPasswordEncoder()
//				.username("Ravi")
//				.password("ravi100")
//				.roles("admin")
//				.build();
//		return new InMemoryUserDetailsManager(user,user2);
//		
//		
//	}
//	
	@Bean
	public AuthenticationProvider authProvider() {
		System.out.println("DaoAuthenticationProvider is setting up.");
		//fetch info from db & authenticate
		DaoAuthenticationProvider daoProvider=new DaoAuthenticationProvider(userDetailsService);
		//what variable need to be decoded.
		//userDetailsService interface holds user information.
		//daoProvider.setUserDetailsService(userDetailsService);
		//should know encoder format so that it can decome it.
		daoProvider.setPasswordEncoder(new BCryptPasswordEncoder(12));
		//Disable Decoding in DaoAuthenticationProvider
		//daoProvider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
		return daoProvider;
		
		
	}

}
