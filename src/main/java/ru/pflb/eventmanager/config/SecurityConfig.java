package ru.pflb.eventmanager.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.boot.autoconfigure.security.oauth2.resource.PrincipalExtractor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ru.pflb.eventmanager.entity.User;
import ru.pflb.eventmanager.repository.UserRepository;
import ru.pflb.eventmanager.security.jwt.JwtConfigurer;
import ru.pflb.eventmanager.security.jwt.JwtTokenProvider;

/**
 * Security configuration class for JWT based Spring Security application.
 */

@Configuration
@EnableWebSecurity
//@EnableOAuth2Sso
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtTokenProvider jwtTokenProvider;
    private final BCryptPasswordEncoder passwordEncoder;

    private static final String ADMIN_ENDPOINT = "/api/v1/admin/**";
    private static final String LOGIN_ENDPOINT = "/api/v1/auth/login";

    @Autowired
    public SecurityConfig(JwtTokenProvider jwtTokenProvider, BCryptPasswordEncoder passwordEncoder) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(
                        LOGIN_ENDPOINT ,
                        "/api/v1/user/register",
                        "/api/v1/event/all/*/size/*",
                        "/api/v1/event/*",
                        "/api/v1/city/*"
                )
                .permitAll()
                .antMatchers(ADMIN_ENDPOINT).hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .apply(new JwtConfigurer(jwtTokenProvider));
    }
/*
    @Bean
    public PrincipalExtractor principalExtractor(UserRepository userRepository) {
        return map -> {
            String id = (String)map.get("sub");

            if (userRepository.findByUsername(id) == null){
                User newUser = new User();

                String name = (String) map.get("name");
                String lastName = "";
                String firstName= "";
                if(name.split("\\w+").length>1){

                    lastName = name.substring(name.lastIndexOf(" ")+1);
                    firstName = name.substring(0, name.lastIndexOf(' '));
                }
                else{
                    firstName = name;
                }
                newUser.setFirstName(firstName);
                newUser.setLastName(lastName);
                newUser.setEmail((String) map.get("email"));
                newUser.setUsername(id);
                newUser.setPassword(passwordEncoder.encode(id));

                return userRepository.save(newUser);
            }else{
                User user = userRepository.findByUsername(id);
                return userRepository.save(user);
            }
        };
    }*/
}

