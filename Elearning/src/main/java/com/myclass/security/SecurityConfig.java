package com.myclass.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.myclass.filter.JwtRequestFilter;
import com.myclass.jwt.JwtAccessDeniedHandler;
import com.myclass.jwt.JwtAuthenticationEntryPoint;

@Configuration
@EnableWebSecurity
@ComponentScan(basePackages = "com.myclass")
public class SecurityConfig {

	@Order(1)
	@Configuration
	public static class RestConfiguration extends WebSecurityConfigurerAdapter {

		@Autowired
		private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

		@Autowired
		private JwtAccessDeniedHandler jwtAccessDeniedHandler;

		@Autowired
		private JwtRequestFilter jwtRequestFilter;

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http.antMatcher("/api/**").csrf().disable().authorizeRequests()
					.antMatchers("/api/authenticate", "/api/register").permitAll()
					//.antMatchers(HttpMethod.DELETE, "/api/role/**").hasAnyRole("ADMIN")										
					.anyRequest().authenticated();
		
			http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

			http.exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint)
					.accessDeniedHandler(jwtAccessDeniedHandler);

			http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

		}
	}

	@Order(2)
	@Configuration
	public static class WebConfiguration extends WebSecurityConfigurerAdapter {

		@Autowired
		private UserDetailsService userDetailsService;

		@Bean
		public PasswordEncoder passwordEncoder() {
			return new BCryptPasswordEncoder();
		}

		@Override
		protected void configure(AuthenticationManagerBuilder auth) throws Exception {
			auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
		}

		@Override
		protected void configure(HttpSecurity http) throws Exception {

			http.csrf().disable().antMatcher("/**")

					.authorizeRequests().antMatchers("/admin/login","/admin/logout").permitAll()
					.antMatchers("/admin/**").hasAnyRole("ADMIN").anyRequest().authenticated();

			http.formLogin().loginPage("/admin/login").loginProcessingUrl("/admin/login").usernameParameter("email")
					.passwordParameter("password").defaultSuccessUrl("/dashboard")
					.failureUrl("/admin/login?state=error");

			http.exceptionHandling().accessDeniedPage("/error/403");

			http.logout().logoutUrl("/admin/logout").logoutSuccessUrl("/admin/login").deleteCookies("JSESSIONID");

		}

		@Bean
		@Override
		public AuthenticationManager authenticationManagerBean() throws Exception {
			return super.authenticationManagerBean();
		}

		@Override
		public void configure(WebSecurity web) throws Exception {
			web.ignoring().antMatchers("/v2/api-docs", "/configuration/ui", "/swagger-resources/**",
					"/configuration/security", "/swagger-ui.html", "/webjars/**");
		}
	}

}
