package com.example.QAapp.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

import static com.example.QAapp.security.SystemUserRoles.*;

@Configuration
public class SystemApplicationConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    SystemUserService systemUserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .csrf().disable()
            .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/author").hasRole(ADMIN.name())
                .antMatchers(HttpMethod.GET, "/author/profile").hasRole(AUTHOR.name())
                .antMatchers(HttpMethod.POST, "/author").permitAll()
                .antMatchers(HttpMethod.DELETE, "/author").hasRole(AUTHOR.name())

                .antMatchers(HttpMethod.PUT, "/questions/*/flag").hasAuthority(SystemUserPermissions.FLAG.name())
                .antMatchers(HttpMethod.POST, "/questions").hasAnyRole(AUTHOR.name(), ADMIN.name())
                .antMatchers(HttpMethod.DELETE, "/questions/**").hasAnyRole(AUTHOR.name(), ADMIN.name())
                .antMatchers(HttpMethod.PUT, "/questions/**").hasAnyRole(AUTHOR.name(), ADMIN.name())

                .antMatchers(HttpMethod.POST, "/answers").hasRole(AUTHOR.name())
                .antMatchers(HttpMethod.PUT, "/answers/**").hasRole(AUTHOR.name())
                .antMatchers(HttpMethod.DELETE, "/answers/**").hasAnyRole(AUTHOR.name(), ADMIN.name())

                .antMatchers("/management/**").hasRole(ADMIN.name())
            .anyRequest()
            .authenticated()
            .and()
            .httpBasic();

//        http
//                .csrf().disable()//.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
////                .and()
//                .authorizeRequests()
////                .antMatchers(HttpMethod.GET,"/management/questions").hasAnyRole(SystemUserRoles.ADMIN.name(), SystemUserRoles.ASSISTANT.name())
////                .antMatchers(HttpMethod.POST,"/management/questions").hasRole(SystemUserRoles.ADMIN.name())
////                .antMatchers("/management/answer").hasAuthority(SystemUserPermissions.BLOCK.name())
////                .antMatchers("/**").permitAll()
//                .anyRequest()
//                .authenticated()
//                .and()
//                .httpBasic();
////                .formLogin()
////                    .loginPage("/login")
////                    .permitAll()
////                .and()
////                .oauth2Login()
////                    .loginPage("/login").permitAll()
////                    .userInfoEndpoint()
////                        .userService(systemUserService)
        ;
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
