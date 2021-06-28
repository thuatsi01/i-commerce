package com.icommerceapis.restapis.configuration;

import com.icommerceapis.common.Routes;
import com.icommerceapis.restapis.configuration.jwt.JwtAuthenticationSuccessHandler;
import com.icommerceapis.restapis.configuration.jwt.JwtAuthenticationTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private static final String[] unAuthorizedPath = new String[]{
            Routes.User.BASE_URL + Routes.User.LOGIN,
            Routes.HEATH_CHECK
    };

    @Autowired
    private AuthenticationManager authenticationManager;

    @Bean
    public JwtAuthenticationTokenFilter authenticationTokenFilterBean() {
        var skipMatcher = new SkipPathRequestMatcher(unAuthorizedPath);
        var authenticationTokenFilter = new JwtAuthenticationTokenFilter(skipMatcher);
        authenticationTokenFilter.setAuthenticationManager(authenticationManager);
        authenticationTokenFilter.setAuthenticationSuccessHandler(new JwtAuthenticationSuccessHandler());
        return authenticationTokenFilter;
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf()
                .disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .anyRequest().permitAll()
                .and()
                .addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);
    }
}
