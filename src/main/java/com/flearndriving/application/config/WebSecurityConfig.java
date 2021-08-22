/**
 * 
 */
package com.flearndriving.application.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import com.flearndriving.application.component.Oauth2LoginSuccessHandler;
import com.flearndriving.application.services.CustomOAuth2AccountService;
import com.flearndriving.application.services.UserDetailsServiceImpl;

import nz.net.ultraq.thymeleaf.LayoutDialect;
import nz.net.ultraq.thymeleaf.decorators.strategies.GroupingRespectLayoutTitleStrategy;

/**
 * @author tamdu
 *
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private Oauth2LoginSuccessHandler oauth2LoginSuccessHandler;

    @Autowired
    private CustomOAuth2AccountService oAuth2AccountService;

    // Config thymeleaf dialect
    @Bean
    public LayoutDialect layoutDialect() {
        return new LayoutDialect(new GroupingRespectLayoutTitleStrategy());
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

        // Sét đặt dịch vụ để tìm kiếm User trong Database.
        // Và sét đặt PasswordEncoder.
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        // Các trang không yêu cầu login
        http.authorizeRequests().antMatchers("/oauth2/**").permitAll().antMatchers("/", 
                "/index", 
                "/login",
                "/register",
                "/create-account",
                "/document",
                "/list-question",
                "/detail-document").permitAll();
        // Trang yêu cầu phải login với vai trò ROLE_USER hoặc ROLE_ADMIN.
        // Nếu chưa login, nó sẽ redirect tới trang /login.
        http.authorizeRequests().antMatchers("/learn/**", 
                "/view-profile", 
                "/update-profile", 
                "/view-profile-registed-exam",
                "/view-profile-learning-progress",
                "/view-history-trial-test",
                "/detail-history-trial-test",
                "/upload-avatar",
                "/select-chapter",
                "/register-exam",
                "/search-exam",
                "/load-district",
                "/load-ward",
                "/load-page-learn",
                "/submit-answer",
                "/trial-exam",
                "/select-exam-question",
                "/init-select-exam-question",
                "/select-driving-license",
                "/init-trial-exam",
                "/post-answer").access("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')");
        http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/403");
        // Cấu hình cho Login Form.
        http.authorizeRequests().and().formLogin()
                // Submit URL của trang login
                .loginProcessingUrl("/j_spring_security_check") // Submit URL
                .loginPage("/login")//
                .defaultSuccessUrl("/index")//
                .failureUrl("/login?error=true")//
                .usernameParameter("username")//
                .passwordParameter("password")
                // Cấu hình cho Logout Page.
                .and().oauth2Login().loginPage("/login").userInfoEndpoint().userService(oAuth2AccountService).and()
                .successHandler(oauth2LoginSuccessHandler).and().logout().logoutUrl("/logout")
                .logoutSuccessUrl("/login");
        // Cấu hình Remember Me 24h
        http.authorizeRequests().and() //
                .rememberMe().tokenRepository(this.persistentTokenRepository()) //
                .tokenValiditySeconds(1 * 24 * 60 * 60); //
    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl db = new JdbcTokenRepositoryImpl();
        db.setDataSource(dataSource);
        return db;
    }

}
