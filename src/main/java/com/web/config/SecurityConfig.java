package com.web.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.web.config.sec.UserLoginSuccessHandler;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {
	/*
	 * remove later
	 */
	@Autowired
	UserDetailsService userDetailsService;

	@Autowired
	private DataSource dataSource;

	@Value("${spring.queries.users-query}")
	private String usersQuery = "select email, passwords, actived from users where email=?";
	// private String usersQuery;

	// @Value("${spring.queries.roles-query}")
	private String rolesQuery = "SELECT email, role FROM users WHERE email=?";

	// @Autowired
	public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
		System.out.println(usersQuery);

		auth.userDetailsService(userDetailsService);

		/*
		 * auth.jdbcAuthentication() .passwordEncoder(new MyPasswordEncoder())
		 * .dataSource(dataSource) //
		 * .usersByUsernameQuery("select email as username , passwords as password from users where email=?"
		 * ) .usersByUsernameQuery(usersQuery)
		 * 
		 * .authoritiesByUsernameQuery(rolesQuery)
		 * 
		 * ;
		 */

	}

	@Configuration
	@Order(1)
	public static class AdminConfigurationAdapter {

		@Autowired
		private UserLoginSuccessHandler successHandler;

		@Bean
		public SecurityFilterChain filterChainApp1(HttpSecurity http) throws Exception {
			System.out.println("filter 1");

			http
					// .userDetailsService(new MyUserDetailService())
					.antMatcher("/admin/**").csrf().disable()
					.authorizeRequests()
					.antMatchers("/admin/**", "/account/**")
					// .authenticated()
					.hasRole("ADMIN")
					.and()
					.formLogin()
					.usernameParameter("email")
					.passwordParameter("passwords")
					.loginPage("/login/manage")
					.loginProcessingUrl("/admin/auth")
					// .loginProcessingUrl("/login/auth")
					// .failureUrl("/login/manage?error=loginError")
					.defaultSuccessUrl("/admin").successHandler(successHandler)
					.and()
					.logout()
					.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
					.logoutSuccessUrl("/login1")

					.deleteCookies("JSESSIONID")

					.and().exceptionHandling().accessDeniedPage("/404")
			// .and().rememberMe().key("uniqueAndSecret").tokenValiditySeconds(1296000)
			;

			return http.build();
		}
	}

	@Configuration
	@Order(2)
	public static class UserConfigurationAdapter {
		@Autowired
		private UserLoginSuccessHandler successHandler;

		@Bean
		public SecurityFilterChain filterChainApp2(HttpSecurity http) throws Exception {

			http.csrf().disable()
					.authorizeRequests()
					.antMatchers("/account/**")
					.hasAnyRole("ADMIN", "USER")
					.and()
					.formLogin()
					.usernameParameter("email")
					.passwordParameter("passwords")
					.loginPage("/login")
					.loginProcessingUrl("/login/auth2")
					.failureUrl("/login?error=loginError")
					.defaultSuccessUrl("/").successHandler(successHandler)
					.and()
					.logout()
					.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
					.logoutSuccessUrl("/")
					.deleteCookies("JSESSIONID")

					.and().exceptionHandling().accessDeniedPage("/404")
			// .and().rememberMe().key("uniqueAndSecret").tokenValiditySeconds(1296000)
			;

			// http
			// .authorizeRequests()
			// .antMatchers("/account/**").authenticated()
			// .and()
			// .formLogin()
			// .loginPage("/login")
			// .permitAll()
			// .and()
			// .logout()
			// .logoutUrl("/myapp/web/logout")
			// .permitAll();
			//// so
			// System.out.println("user ault");
			// http
			// .antMatcher("/admin/**")
			// .formLogin().loginPage("/admin/login")
			// .and()
			// .authorizeRequests().antMatchers("/admin/**").authenticated();

			// http.requestMatcher(new AntPathRequestMatcher("/account/**"))
			// .authorizeRequests().anyRequest().authenticated()
			// .and()
			// .formLogin()
			// .loginPage("/login")
			// .usernameParameter("email")
			//// .loginProcessingUrl("/account/login")
			//// .defaultSuccessUrl("/user/home")
			// .permitAll()
			// .and()
			// .logout()
			// .logoutUrl("/user/logout")
			// .logoutSuccessUrl("/");
			//
			// return http.build();
			return http.build();
		}
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new MyPasswordEncoder();
		// PasswordEncoder encoder = new BCryptPasswordEncoder();
		// return encoder;
		// return NoOpPasswordEncoder.getInstance();
	}

	// @Bean
	// public AuthenticationProvider authProvider() {
	// DaoAuthenticationProvider privider = new DaoAuthenticationProvider();
	// provide
	//
	// }

}
