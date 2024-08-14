package com.app.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

	@Autowired
	private PasswordEncoder enc;

	@Autowired
	private JwtAuthenticationFilter jwtFilter;

	@Autowired
	private CustomAuthenticationEntryPoint authEntry;

//	@Bean
//    public SecurityFilterChain authorizeRequests(HttpSecurity http) throws Exception {
//        http.cors().and().csrf().disable() // Disable CSRF as we use JWT
//            .authorizeRequests()
//                .antMatchers("/api/users/register","api/doctors/register", "/api/authenticate", "/v2/api-docs", "/swagger-resources/**", "/swagger-ui/**","/api/users/signin") // Add Swagger endpoints
//                .permitAll() // Allow unrestricted access to these endpoints
//                .antMatchers(HttpMethod.OPTIONS).permitAll() // Allow pre-flight requests for CORS
//                .antMatchers("/api/doctors/**").permitAll()
//                .antMatchers("/api/patients/**").permitAll()
//                .antMatchers("/api/users/**").hasAnyRole("DOCTOR", "PATIENT")
//                .anyRequest().authenticated() // All other requests require authentication
//            .and()
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // Use stateless session
//            .and()
//                .exceptionHandling().authenticationEntryPoint(authEntry) // Handle unauthorized access
//            .and()
//                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class); // Add JWT filter before Spring Security's filter
//
//        return http.build();
//    }

	@Bean
	public SecurityFilterChain authorizeRequests(HttpSecurity http) throws Exception {
	    http.cors().and().csrf().disable() // Disable CSRF as we use JWT
	        .authorizeRequests()
	            .anyRequest().permitAll() // Allow unrestricted access to all requests
	        .and()
	            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // Use stateless session
	        .and()
	            .exceptionHandling().authenticationEntryPoint(authEntry) // Handle unauthorized access
	        .and()
	            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class); // Add JWT filter before Spring Security's filter

	    return http.build();
	}


//	@Bean
//	public CorsConfigurationSource corsConfigurationSource() {
//	    CorsConfiguration configuration = new CorsConfiguration();
//	    configuration.setAllowedOriginPatterns(Arrays.asList("*")); // Allow all origins
//	    configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS")); // Allow all HTTP methods
//	    configuration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type")); // Allow specific headers
//	    configuration.setAllowCredentials(true); // Allow credentials
//
//	    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//	    source.registerCorsConfiguration("/**", configuration); // Apply CORS settings to all endpoints
//	    return source;
//	}






	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}
}
