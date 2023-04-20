package com.example.ders1_2.config;

import com.example.ders1_2.config.jwt.jwtauthfilterconfigurationadapter;
import com.example.ders1_2.config.jwt.jwtfilter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;

@EnableWebSecurity
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(
        prePostEnabled = true,
        securedEnabled = true,
        jsr250Enabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

//    @Autowired
//    UserDetailsService userDetailsService;

//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http
//                .csrf()
//                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
//        return http.build();
//    }
//    public final jwtauthfilterconfigurationadapter jwtfilter;
    @Override
    protected void configure(HttpSecurity http) throws Exception {

//        http.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
        http.csrf().disable(); //BU TEHLLUKELIDIR
//        http.csrf();
       // http.httpBasic();
       // http.formLogin();

      //  http.formLogin().loginPage("/login").permitAll();


//        http.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());


//        http.authorizeHttpRequests().antMatchers("/login")
//                .permitAll()
//                .antMatchers("/login2")
//                .permitAll()
//                .antMatchers("/index")
//                .permitAll();

        http.authorizeHttpRequests()
//                .antMatchers("/login")
//                .permitAll()

                .antMatchers("/index")
                .permitAll()

                .antMatchers("/dashboard")
                .authenticated()
                .and()
                .authorizeHttpRequests()



//                .antMatchers("/hello/**")
//                .authenticated()
//                .and()
//                .authorizeHttpRequests()

                .antMatchers("/hello/**")
                .hasAnyAuthority("user","admin")


                .antMatchers("/check")
                .authenticated()
                .and()
                .authorizeHttpRequests()





                .antMatchers(HttpMethod.GET,"/books/**")
                .authenticated()
                .and()
                .authorizeHttpRequests()

                .antMatchers("/author/**")
                .authenticated()
                .and()
                .authorizeHttpRequests()

                .antMatchers("/publisher/**")
                .authenticated()
                .and()
                .authorizeHttpRequests()

                .antMatchers("/user2")
                .authenticated()
                .and()
                .authorizeHttpRequests()

                .antMatchers(HttpMethod.POST, "/books/post")//.hasAnyRole("ROLE_Publisher","ROLE_ADMIN")
                .authenticated()
                .and()
                .authorizeHttpRequests()


                .antMatchers(HttpMethod.POST, "/books/post2")//.hasAnyRole("ROLE_Publisher","ROLE_ADMIN")
                .authenticated()
                .and()
                .authorizeHttpRequests()

                .antMatchers(HttpMethod.PUT, "/books/updatemy")//.hasAnyRole("ROLE_Publisher","ROLE_ADMIN")
                .authenticated()
                .and()
                .authorizeHttpRequests()




                .antMatchers(HttpMethod.GET, "/books/post")//.hasAnyRole("ROLE_Publisher","ROLE_ADMIN")
                .authenticated()
                .and()
                .authorizeHttpRequests()


                .antMatchers(HttpMethod.PUT, "/books")//.hasAnyRole("ROLE_Publisher","ROLE_ADMIN")
                .authenticated()
                .and()
                .authorizeHttpRequests()

                .antMatchers("/publisher/user2")
                .authenticated()
                .and()
                .authorizeHttpRequests()

                .antMatchers("/role")
                .authenticated()
                .and()
                .authorizeHttpRequests()
        ;


       // http.apply(jwtfilter);

    }
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userDetailsService);
//    }

    //Deprecated olan bir metot ve artık kullanmamıza gerek yok noop derken yetiyor.
    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}
