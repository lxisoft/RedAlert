package com.lxisoft.redalert.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
/*@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
@Import(SecurityProblemSupport.class)*/
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	
	@Autowired
	private DataSource datasource;
	
	@Bean
	PasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}

	@Bean
	BCryptPasswordEncoder bCryptPasswordEncoder() {
	    return new BCryptPasswordEncoder();
	}
		
    /*private final SecurityProblemSupport problemSupport;
    public SecurityConfiguration(SecurityProblemSupport problemSupport) {
        this.problemSupport = problemSupport;
    }*/

    @Override
    public void configure(HttpSecurity http) throws Exception {
      /*  http
            .csrf()
            .disable()
            .exceptionHandling()
          //  .authenticationEntryPoint(problemSupport)
            //.accessDeniedHandler(problemSupport)
        .and()
            .headers()
            .frameOptions()
            .disable()
        .and()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
            .authorizeRequests()
            .antMatchers("/api/**").authenticated()
            .antMatchers("/management/health").permitAll()
            .antMatchers("/management/info").permitAll()
            .antMatchers("/management/**").hasAuthority(AuthoritiesConstants.ADMIN);
        
        
        */
    	http
        .csrf()
        .disable()
        .authorizeRequests()

        .antMatchers("/redAlertUi/home","/crime-stopper/*")
        .hasAnyRole("USER")
        .and()
        
        
        .formLogin().loginPage("/redAlertUiIndex/index").permitAll();

    	http
    	.logout()
        .logoutUrl("/redAlertUiLogout/logout")
     
        .permitAll();
    	
    	

    	
    }
    
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) 
      throws Exception {

        
       /* auth.inMemoryAuthentication()      
        .withUser("abhina").password("{noop}pass1").roles("USER"); 
      */
        auth.jdbcAuthentication().dataSource(datasource).passwordEncoder(new BCryptPasswordEncoder()).
	      
	      usersByUsernameQuery("select id,password,activated from jhi_user where id=?")
	      
         .authoritiesByUsernameQuery("select user_id,authority_name from jhi_user_authority where user_id=?");
        
    }

   
    
    /**
     * This OAuth2RestTemplate is only used by AuthorizationHeaderUtil that is currently used by TokenRelayRequestInterceptor
     */
  /*  @Bean
    public OAuth2RestTemplate oAuth2RestTemplate(OAuth2ProtectedResourceDetails oAuth2ProtectedResourceDetails,
        OAuth2ClientContext oAuth2ClientContext) {
        return new OAuth2RestTemplate(oAuth2ProtectedResourceDetails, oAuth2ClientContext);
    }*/

}