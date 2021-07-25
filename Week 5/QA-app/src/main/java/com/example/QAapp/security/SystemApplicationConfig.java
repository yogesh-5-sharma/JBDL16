package com.example.QAapp.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.proxy.NoOp;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
public class SystemApplicationConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    SystemUserService systemUserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()//.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
//                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET,"/management/questions").hasAnyRole(SystemUserRoles.ADMIN.name(), SystemUserRoles.ASSISTANT.name())
                .antMatchers(HttpMethod.POST,"/management/questions").hasRole(SystemUserRoles.ADMIN.name())
                .antMatchers("/management/answer").hasAuthority(SystemUserPermissions.BLOCK.name())
//                .antMatchers("/**").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin().permitAll();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);
        daoAuthenticationProvider.setUserDetailsService(systemUserService);

        auth.authenticationProvider(daoAuthenticationProvider);
    }

    //    @Bean
//    public UserDetailsService userDetailsService() {
//        UserDetails ajay = User.builder()
//                .username("ajay")
//                .password(passwordEncoder.encode("ajay_pwd"))
//                .authorities(SystemUserRoles.ADMIN.getAuthorities())
//                .build();
//
//        UserDetails raj = User.builder()
//                .username("raj")
//                .password(passwordEncoder.encode("raj_pwd"))
//                .authorities(SystemUserRoles.ASSISTANT.getAuthorities())
//                .build();
//
//        UserDetails shubham = User.builder()
//                .username("shubham")
//                .password(passwordEncoder.encode("shubham_pwd"))
//                .authorities(SystemUserRoles.PROGRAMMER.getAuthorities())
//                .build();
//
//        return new InMemoryUserDetailsManager(
//                ajay,
//                raj,
//                shubham
//        );
//    }


}
