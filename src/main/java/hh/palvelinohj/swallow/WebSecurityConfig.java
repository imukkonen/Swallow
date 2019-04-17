package hh.palvelinohj.swallow;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import hh.palvelinohj.swallow.web.UserDetailServiceImpl;



@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	 @Autowired
	    private UserDetailServiceImpl userDetailsService;	
	
	@Override
    protected void configure(HttpSecurity http) throws Exception {
        http
        .authorizeRequests().antMatchers("/css/**", "/index", "/**list/", "*/lang*").permitAll() // Enable css when logged out
        .and()
        .authorizeRequests().antMatchers("/delete*/*").hasAuthority("ADMIN")
        .and()
        .authorizeRequests().antMatchers("/edit*/*").hasAuthority("ADMIN")
        .and()
        .authorizeRequests().antMatchers("/add*").hasAuthority("ADMIN")
        /*
        .and()
        .authorizeRequests().anyRequest().authenticated()
        */
        .and()
        
      .formLogin()
          .loginPage("/login")
          .defaultSuccessUrl("/grouplist")
          .permitAll()
          .and()
      .logout()
      		.logoutSuccessUrl("/index")
      		.permitAll();
    }
    

	@Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }

}
