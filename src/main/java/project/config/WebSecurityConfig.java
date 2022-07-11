package project.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    private final UserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf()
                .disable()
                .authorizeRequests()
                //доступ только для пользователей с ролью Администратор
                .antMatchers("/*").hasAnyAuthority("ADMIN")
                .antMatchers("/*").hasAnyAuthority("USER", "ADMIN")
                .antMatchers("/index").permitAll()
                //все остальные страницы требуют аутентификации.AnyRequest.authenticated()
                .and()
                //настройка для входа в систему
                .formLogin(form -> {
                    try {
                        form
                                .loginPage("/login")
                                .permitAll()
                        //перенаправление на главную страниц после успешного входа
                        .defaultSuccessUrl("/", true)
                        //.loginProcessingUrl("/checkUser")
                        //.permitAll()
                        .and()
                        .logout()
                        .permitAll()
                        .logoutSuccessUrl("/login");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });

    }

    @Autowired
    protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }
}
