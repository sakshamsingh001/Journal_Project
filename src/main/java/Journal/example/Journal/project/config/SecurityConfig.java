package Journal.example.Journal.project.config;

import Journal.example.Journal.project.Service.UserDetailServiceImpl;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@EnableWebSecurity
@EnableWebMvc
public class SecurityConfig {


    @Autowired
    private UserDetailServiceImpl userDetailsService;
    private final  PasswordEncoder passwordEncoder;

    @Autowired
    public SecurityConfig(UserDetailServiceImpl userDetailsService, PasswordEncoder passwordEncoder) {
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

//basic code for enabling authorization
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {


        return http.authorizeHttpRequests(request -> request
                        .requestMatchers("/user/save","/user/register").permitAll()
                        .requestMatchers("/journal/**", "/user/**").authenticated()
                        .requestMatchers("/admin/**").hasAnyRole("ADMIN")
                        .anyRequest().authenticated())
                .httpBasic(Customizer.withDefaults())
                .csrf(AbstractHttpConfigurer::disable)
                .build();
    }
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


//    @Bean
//    @Lazy
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }




}
