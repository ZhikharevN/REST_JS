package ru.kata.spring.boot_security.demo.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import ru.kata.spring.boot_security.demo.service.MyUserDetailsService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private final SuccessUserHandler successUserHandler;
    private final MyUserDetailsService myUserDetailsService;

    public WebSecurityConfig(SuccessUserHandler successUserHandler, MyUserDetailsService myUserDetailsService) {
        this.successUserHandler = successUserHandler;
        this.myUserDetailsService = myUserDetailsService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers( "/css/**", "/getUser", "/getUsers", "**/webjars/jquery/3.3.1/jquery.min.js", "/js/**").permitAll()
                .antMatchers("/user").hasAnyRole("USER", "ADMIN")
                .anyRequest().hasAnyRole("ADMIN")
                .and()
                .formLogin().successHandler(successUserHandler)
                .permitAll()
                .and()
                .logout().logoutUrl("/logout").logoutSuccessUrl("/login")
                .permitAll();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myUserDetailsService)
                .passwordEncoder(getPasswordEncoder());
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }


}