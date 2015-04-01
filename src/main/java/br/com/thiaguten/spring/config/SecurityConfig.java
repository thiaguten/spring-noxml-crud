package br.com.thiaguten.spring.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
				.passwordEncoder(getPasswordEncoder())
				.withUser("admin")
				.password("$2a$05$uYs.4IMu07yj68Oy9KRWoOEHOos2WIFUNk5bg5eGRRhWF/n2Skmwq")
				.roles("ADMIN");
	}

	@Bean(name = "passwordEncoder")
	public PasswordEncoder getPasswordEncoder() {
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(5);
		return passwordEncoder;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/", "/usuario/pesquisar", "/usuario/listar")
				.permitAll().anyRequest().authenticated().and().formLogin();
	}

}
