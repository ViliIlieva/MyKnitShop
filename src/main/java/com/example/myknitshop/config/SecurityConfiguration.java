package com.example.myknitshop.config;

import com.example.myknitshop.models.enums.UserRoleEnum;
import com.example.myknitshop.repository.UserRepository;
import com.example.myknitshop.service.AppUserDetailsService;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfiguration {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new Pbkdf2PasswordEncoder ();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.
                        authorizeHttpRequests ().
                        requestMatchers ("/", "/css/**", "/lib/**", "/images/**", "/js/**", "/scss/**", "/mail/**").permitAll().
                        requestMatchers (PathRequest.toStaticResources ().atCommonLocations ()).permitAll ().
                        requestMatchers ("/", "/login", "/register", "/about", "/product",
                                "/login-error", "/product/info/{id}", "/gallery", "/service").permitAll ().

                        requestMatchers ("/products/add", "/user/admin", "/product/delete/{id}",
                                "/message/delete/{id}", "/order/close/{id}", "/product/edit/{id}").hasRole(UserRoleEnum.ADMIN.name ()).

                        requestMatchers("/purchase/{id}", "/cart",
                                "/cart/remove-product-from-list/{id}", "/order/details/{id}").hasRole(UserRoleEnum.CLIENT.name()).
                // all other pages are available for logger in users
                        anyRequest ().authenticated ().
                and ().
                // configuration of form login
                        formLogin ().
                // the custom login form
                        loginPage ("/login").
                // the name of the username form field
                        usernameParameter (UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY).
                // the name of the password form field
                        passwordParameter (UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_PASSWORD_KEY).
                // where to go in case that the login is successful
                        defaultSuccessUrl ("/", true).
                // where to go in case that the login failed
                        failureForwardUrl ("/login-error").//в контролера в логин връща грешките
                and ().
                // configure logout
                        logout ().
                        logoutUrl ("/logout").//не е необходимо в контролера да правим POST заявка, това я генерира само
                // which is the logout url
                        logoutSuccessUrl ("/").
                // invalidate the session and delete the cookies
                        invalidateHttpSession (true).
                deleteCookies ("JSESSIONID");

        return http.build ();
    }

    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository) {
        return new AppUserDetailsService (userRepository);
    }
}
