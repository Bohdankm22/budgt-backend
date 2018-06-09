package com.trio.budgt;

import com.trio.budgt.auth.JWTAuthenticationFilter;
import com.trio.budgt.auth.JWTAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JWTAuthenticationProvider provider;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http    .addFilterBefore(new JWTAuthenticationFilter(), BasicAuthenticationFilter.class)
////                .authenticationProvider(provider)
////                .authorizeRequests()
////                .antMatchers("/").permitAll()
////                .antMatchers("/api/v1/**").hasRole("USER")
////                .and()
////                .formLogin()
////                .loginPage("/api/v1/auth/sign-in").failureUrl("/login-error");
        http.addFilterBefore(new JWTAuthenticationFilter(), BasicAuthenticationFilter.class);
        http.authenticationProvider(provider);
        http.csrf().disable();
        http.authorizeRequests().anyRequest().permitAll();
    }

}
