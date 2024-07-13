// package com.org.customer.configuration;

// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.http.HttpMethod;
// import
// org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import
// org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import org.springframework.security.web.SecurityFilterChain;

// @Configuration
// @EnableWebSecurity
// public class SecurityConfig {

// @Bean
// public SecurityFilterChain securityFilterChain(HttpSecurity http) throws
// Exception {
// http
// .authorizeHttpRequests(authorizeRequests ->
// authorizeRequests
// .requestMatchers(HttpMethod.GET, "/public/**").permitAll() // Permit GET
// requests to public resources
// .requestMatchers(HttpMethod.POST, "/customer/register").permitAll() // Permit
// POST requests to customer register
// .requestMatchers(HttpMethod.POST, "/customer/login").permitAll() // Permit
// POST requests to customer login
// .requestMatchers(HttpMethod.PUT, "/customer/update").permitAll() // Permit
// PUT requests to customer update
// .requestMatchers(HttpMethod.DELETE, "/customer/delete/**").permitAll() //
// Permit DELETE requests to customer delete
// .requestMatchers(HttpMethod.GET, "/customer/getAllCustomers").permitAll() //
// Permit GET requests to get all customers
// .requestMatchers(HttpMethod.GET, "/customer/getCustomerById/**").permitAll()
// // Permit GET requests to get customer by ID
// .anyRequest().authenticated() // All other requests must be authenticated
// )
// .formLogin(formLogin ->
// formLogin
// .loginPage("/customer/login").permitAll() // Custom login page
// )
// .logout(logout ->
// logout
// .logoutUrl("/customer/register") // Custom logout URL
// .logoutSuccessUrl("/login?logout") // Redirect to login page after logout
// .invalidateHttpSession(true) // Invalidate HTTP session
// .deleteCookies("JSESSIONID") // Delete cookies
// );

// return http.build();
// }
// }
