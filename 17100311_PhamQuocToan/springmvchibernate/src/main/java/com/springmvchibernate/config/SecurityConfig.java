package com.springmvchibernate.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("toan").password("123").roles("ADMIN");

	}
/*loginPage trong requestmaping("login")
 * loginProcessingUrl trong formlogin
 * */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests().antMatchers("/home/**").hasRole("ADMIN")
		.anyRequest().permitAll().and().formLogin().loginPage("/login").loginProcessingUrl("/login")
		.usernameParameter("user").passwordParameter("password").defaultSuccessUrl("/").failureUrl("/login")
		.and().exceptionHandling().accessDeniedPage("/login");
		
	}

}
