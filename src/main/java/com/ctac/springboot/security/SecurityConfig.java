package com.ctac.springboot.security;

import com.ctac.springboot.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserService userService;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(userService);
        auth.setPasswordEncoder(passwordEncoder());
        return auth;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()   // if enabled must use post method to logout and additional configuration needed
                .headers().frameOptions().disable() // fix H2 console access
                .and()
                .authorizeRequests()
//                .antMatchers("/createNewPost/**", "/editPost/**").hasRole("USER")
                .antMatchers("/create-post/**", "/editPost/**", "/comment/**").hasRole("USER")
                .antMatchers("/deletePost/**").hasRole("USER")
//                .antMatchers("/deletePost/**").hasRole("ADMIN")
                .antMatchers("/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login").loginProcessingUrl("/login") // same as default implicit configuration
                .usernameParameter("username").passwordParameter("password") // same as default implicit configuration
                .defaultSuccessUrl("/").failureUrl("/login?error") // same as default implicit configuration
                .permitAll()
                .and()
                .rememberMe().rememberMeParameter("remember-me")
                .and()
                .logout().logoutUrl("/logout").logoutSuccessUrl("/login?logout") // same as default implicit configuration
                .permitAll()
                .and()
                .sessionManagement().maximumSessions(1);
    }
}
