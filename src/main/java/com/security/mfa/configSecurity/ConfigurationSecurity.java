package com.security.mfa.configSecurity;


import com.security.mfa.userService.MyUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ConfigurationSecurity {


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http)throws Exception
    {
        http
                .cors(c->c.disable())
                .csrf(c->c.disable())
                .authorizeHttpRequests((auth)->auth
                        .requestMatchers("/").permitAll()
                        .requestMatchers("/login").permitAll()
                        .anyRequest().authenticated())
                .formLogin(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults())
                .userDetailsService(mfaUserDetailsService());
        return http.build();


    }
    @Bean
    public UserDetailsService mfaUserDetailsService()
    {
        return new MyUserDetailsService();
    }


}
