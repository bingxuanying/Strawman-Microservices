package com.example.adminservice.config;

//import com.example.adminservice.security.filters.UsernamePasswordAuthFilter;
//import com.example.adminservice.security.providers.UsernamePasswordAuthProvider;
import com.example.adminservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

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
