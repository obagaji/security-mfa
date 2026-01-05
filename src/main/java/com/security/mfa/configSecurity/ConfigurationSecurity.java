package com.security.mfa.configSecurity;


import com.security.mfa.userService.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authorization.AuthorizationManagerFactories;
import org.springframework.security.authorization.AuthorizationManagerFactory;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authorization.EnableMultiFactorAuthentication;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.authority.FactorGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration( proxyBeanMethods = false)
@EnableWebSecurity(debug = true)
@EnableMethodSecurity
@EnableMultiFactorAuthentication(authorities =
        {FactorGrantedAuthority.PASSWORD_AUTHORITY,FactorGrantedAuthority.OTT_AUTHORITY})
public class ConfigurationSecurity {
    @Autowired
    MyUserDetailsService myUserDetailsService;
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http)throws Exception
    {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorize -> authorize
                                .requestMatchers("/add","/ott/sent").permitAll()
                                .requestMatchers("/all").permitAll()
                             //   .requestMatchers("/view/**").authenticated()
                                .anyRequest().authenticated()

                )
               // .userDetailsService(mfaUserDetailsService())
                .userDetailsService( myUserDetailsService)
                .formLogin(Customizer.withDefaults())
                .oneTimeTokenLogin(Customizer.withDefaults())
                .build();

    }

 /*   @Bean
    public MyUserDetailsService mfaUserDetailsService()
    {
        return new MyUserDetailsService();
    }*/
    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return NoOpPasswordEncoder.getInstance();
    }

}
