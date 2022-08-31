package in.ashokit.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration // to represent this class as configration class
@EnableWebSecurity // to enable spring security for this class
//WebSecurityConfigurerAdapter is an abstract class used to implement authentication and authorisation logic
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	//contains db details to interact with db
	@Autowired
	private DataSource dataSource;
	
	
	//used to specify which encoder v used to specify pwd to store in db
	@Autowired
	private BCryptPasswordEncoder encode;

	/**
	 * In this method we will configure authentication Credentials
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		//used to configure jdbc aunthentication
		
		auth.jdbcAuthentication()
		    .dataSource(dataSource)
		    .usersByUsernameQuery("select user_name,user_pwd,user_enabled from user_dtls where user_name=?") //based on the query this method is going to get the data from db(used to check user details are available in db or not,check by username)
		    .authoritiesByUsernameQuery("select user_name,user_role from user_dtls where user_name=?") //used to check whether userhaving authorizaton or not(will check by user role)
		    .passwordEncoder(encode);
		    
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















