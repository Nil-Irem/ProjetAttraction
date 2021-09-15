package ParcAttractionBoot.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import ParcAttractionBoot.services.CustomUserDetails;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("static/img/**");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// @formatter:off
		http.antMatcher("/api/**")
			.csrf().ignoringAntMatchers("/api/**")
			.and()
			.authorizeRequests()
				.antMatchers(HttpMethod.OPTIONS,"api/**").anonymous()
				.anyRequest()
				.permitAll()
			.and()
			.formLogin();
//				.loginPage("/login")
//				.defaultSuccessUrl("/")
//				.failureUrl("/login?error")
//				.permitAll()
//			.and()
//			.logout()
//				.logoutUrl("/logout")
//				.logoutSuccessUrl("/?logout");				
		// @formatter:on
	}
	
	
	@Autowired
	private CustomUserDetails userDetailsService;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
