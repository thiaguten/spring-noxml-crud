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
		auth
			.inMemoryAuthentication()
			.passwordEncoder(passwordEncoder())
				.withUser("admin")
                //123456
				.password("$2a$05$uYs.4IMu07yj68Oy9KRWoOEHOos2WIFUNk5bg5eGRRhWF/n2Skmwq")
				.roles("ADMIN");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.antMatchers("/", "/resources/**", "/usuario/pesquisar", "/usuario/listar").permitAll()
				.anyRequest().authenticated()
				.and()
			.httpBasic();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(5);
	}
}
