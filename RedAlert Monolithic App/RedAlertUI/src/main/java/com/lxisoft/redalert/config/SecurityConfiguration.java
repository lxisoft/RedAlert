package com.lxisoft.redalert.config;

import com.lxisoft.redalert.security.*;

import io.github.jhipster.security.*;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.filter.CorsFilter;
import org.zalando.problem.spring.web.advice.security.SecurityProblemSupport;

@Configuration
/*@EnableOAuth2Sso*/
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
@Import(SecurityProblemSupport.class)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

   /* private final CorsFilter corsFilter;

    private final SecurityProblemSupport problemSupport;
*/
   /* public SecurityConfiguration(CorsFilter corsFilter, SecurityProblemSupport problemSupport) {
        this.corsFilter = corsFilter;
        this.problemSupport = problemSupport;
    }
*/
    @Bean
    public AjaxLogoutSuccessHandler ajaxLogoutSuccessHandler() {
        return new AjaxLogoutSuccessHandler();
    }
    @Autowired
    DataSource datasource;

   /* @Override
   public void configure(WebSecurity web) throws Exception {
        web.ignoring()
            .antMatchers(HttpMethod.OPTIONS, "/**")
          //  .antMatchers("/app/**///*.{js,html}")
        //    .antMatchers("/i18n/**")
      //      .antMatchers("/content/**")
    //        .antMatchers("/swagger-ui/index.html")
  //          .antMatchers("/test/**");
//    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
       /* http
            .csrf()
            .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())

        http
            .csrf().disable()
            /*.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())

        .and()
            .addFilterBefore(corsFilter, CsrfFilter.class)
            .exceptionHandling()
            .authenticationEntryPoint(problemSupport)
            .accessDeniedHandler(problemSupport)*/
        
           /* .logout()
            .logoutUrl("/api/logout")
            .logoutSuccessHandler(ajaxLogoutSuccessHandler())
            .permitAll()
        .and()
            .headers()
            .frameOptions()
            .disable()
        .and()
            .authorizeRequests()
            .antMatchers("/api/**").authenticated()
            .antMatchers("/management/health").permitAll()
            .antMatchers("/management/info").permitAll()
            .antMatchers("/management/**").hasAuthority(AuthoritiesConstants.ADMIN);
        */
        
        
       
        /*http
        .csrf()
        .disable()
        .authorizeRequests()
        .antMatchers("/","/login").permitAll()
       .anyRequest().authenticated()
       .and().formLogin().loginPage("/index").defaultSuccessUrl("/test").permitAll();*/
       

    	
    	http
        .csrf()
        .disable()
        .authorizeRequests()
        .antMatchers("/redAlertUi/**")
        .hasAnyRole("USER")
        .and()
        .formLogin().loginPage("/loginController/login").permitAll();
    	
    }
    
    
    
    
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) 
      throws Exception {
       System.out.println("configuration abhina password=========================="+new BCryptPasswordEncoder().encode("abhina"));
        
       /* auth.inMemoryAuthentication()      
        .withUser("abhina").password("{noop}pass1").roles("USER"); 
      */
        auth.jdbcAuthentication().dataSource(datasource).passwordEncoder(new BCryptPasswordEncoder()).
	      
	      usersByUsernameQuery("select id,password,activated from jhi_user where id=?")
	      
         .authoritiesByUsernameQuery("select user_id,authority_name from jhi_user_authority where user_id=?");
    }
}
