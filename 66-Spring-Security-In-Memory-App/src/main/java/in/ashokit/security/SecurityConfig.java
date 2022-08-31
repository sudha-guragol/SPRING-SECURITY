package in.ashokit.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.AntPathMatcher;

@Configuration // to represent this class as configration class
@EnableWebSecurity // to enable spring security for this class
//WebSecurityConfigurerAdapter is an abstract class used to implement authentication and authorisation logic
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	/**
	 * In this method we will configure authentication Credentials
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		// {noop}--->NO operation for password encoder(dont apply any encoding for
		// password technique)
		// authorities represent role (record-1)
		auth.inMemoryAuthentication()
		     .withUser("devs")
		     .password("{noop}devs")
		     .authorities("ADMIN");
		
		//record -2
		
		auth.inMemoryAuthentication()
		    .withUser("ns")
		    .password("{noop}devs")
		    .authorities("EMPLOYEE");
		
		
		//Record-3
		auth.inMemoryAuthentication()
		    .withUser("vs")
		    .password("{noop}devs")
		    .authorities("MANAGER");
	}

	/**
	 * In this method we will configure Authorization roles
	 */

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		//permitAll-->all users can access home page
		//authenticated -->wen user login with credentials he can access welcome page(authentication ppl can access)
		//antMatchers -->decides for which url,which user can access(used to decide permission for the page
		//hasAuthority (EMPLOYEE)-->CHECK FOR USER HAVING  role manager so he can access "/emp" url
		//hasAnyAuthority("EMPLOYEE","MANAGER") -->manager or employee can have an access
		//defaultSuccessUrl -->default success url
		http.authorizeRequests()
		    .antMatchers("/home").permitAll()
		    .antMatchers("/welcome").authenticated()
	        .antMatchers("/admin").hasAuthority("ADMIN")
		    .antMatchers("/emp").hasAuthority("EMPLOYEE")
		    .antMatchers("/manager").hasAuthority("MANAGER")
		    .antMatchers("/common").hasAnyAuthority("EMPLOYEE","MANAGER")
		    
		    //checking for any other url comes as request which is not configured in application
		    .anyRequest().authenticated()
		    
		    .and()
		    .formLogin()
		    .defaultSuccessUrl("/welcome",true)
		
		    .and()
		    .logout()
		    .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
		    
		    
		    .and()
		    .exceptionHandling()
		    .accessDeniedPage("/accessDenied");
	}
	
}















