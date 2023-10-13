package ru.putilin.hibernate.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {


    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withUsername("Evgeniy")
                .password(encoder().encode("qwert"))
                .roles("ADMIN")
                .build());
        manager.createUser(User.withUsername("user")
                .password(encoder().encode("asdf"))
                .roles("USER")
                .build());
        manager.createUser(User.withUsername("Sergey")
                .password(encoder().encode("zxcv"))
                .roles("USER")
                .build());
        return manager;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests((req) -> req.requestMatchers("/persons/by-city/city").permitAll()
                        .requestMatchers("/persons/by-city/age").hasRole("USER")
                        .requestMatchers("/persons/by-city/name").hasAnyRole("ADMIN", "USER")
                        .anyRequest().authenticated())
                .formLogin(Customizer.withDefaults());
        return httpSecurity.build();
    }

}
