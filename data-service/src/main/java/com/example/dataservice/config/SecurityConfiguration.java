package com.example.dataservice.config;

//import com.example.dataservice.security.filters.UsernamePasswordAuthFilter;
//import com.example.dataservice.security.providers.UsernamePasswordAuthProvider;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

//    @Autowired
//    private UsernamePasswordAuthProvider usernamePasswordAuthProvider;
//
//    @Autowired
//    private UserService userService;
//
//    @Bean
//    public BCryptPasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    @Override
//    public AuthenticationManager authenticationManagerBean() throws Exception {
//        return super.authenticationManagerBean();
//    }
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.authenticationProvider(usernamePasswordAuthProvider);
//    }
//
//    @Bean
//    public UsernamePasswordAuthFilter usernamePasswordAuthFilter() {
//        return new UsernamePasswordAuthFilter();
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and()
                .csrf()
                .disable()
                .authorizeRequests().antMatchers("/**").permitAll()
                .anyRequest().authenticated();

//        http.addFilterAt(usernamePasswordAuthFilter(),
//                BasicAuthenticationFilter.class);
    }
}
