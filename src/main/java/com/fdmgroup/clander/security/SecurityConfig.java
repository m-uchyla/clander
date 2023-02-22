package com.fdmgroup.clander.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private final AuthProvider authProvider;
	
	public SecurityConfig(AuthProvider authProvider) {
		this.authProvider = authProvider;
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{

		http.csrf().disable()
		.authorizeRequests()
		.antMatchers("/admin").hasRole("ADMIN")
		.antMatchers("/auth/**","/", "/resources/**" ).permitAll()
		.anyRequest().hasAnyRole("USER","ADMIN")
		.and()
		.formLogin().loginPage("/auth/login")
		.loginProcessingUrl("/process_login")
		.defaultSuccessUrl("/match", true)
		.failureUrl("/auth/login?error")
		.and()
		.csrf()
		.disable()
		.logout()
		.logoutUrl("/logout")
		.logoutSuccessUrl("/auth/login");
		
	}
	
	protected void configure(AuthenticationManagerBuilder auth) {
		auth.authenticationProvider(authProvider);
	}
}
