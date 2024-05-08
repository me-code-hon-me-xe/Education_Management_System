package SE2.Project.Backend.service;

//import SE2.Project.Backend.model.User;
import SE2.Project.Backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
import org.springframework.security.core.userdetails.User;
import java.util.logging.Logger;
@Configuration
@EnableWebSecurity
public class SecurityCfg {
    @Bean
    public UserDetailsService userDetailsService() {
        return new CustomUserDetailsService();
    }
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

//@Bean
//public InMemoryUserDetailsManager userDetailsManager() {
//    UserDetails user1 = User.withUsername("duong")
//            .password(passwordEncoder().encode("123456"))
//            .roles("Student")
//            .build();
//    UserDetails admin = User.withUsername("admin")
//            .password(passwordEncoder().encode("123456"))
//            .roles("Admin")
//            .build();
//
//    return new InMemoryUserDetailsManager(user1, admin);
//}

    private CustomUserDetailsService customUserDetailsService;


//    @Bean
//    public UserDetailsManager UserDetailsManager(DataSource dataSource) {
//        JdbcUserDetailsManager manager = new JdbcUserDetailsManager(dataSource);
//        return manager;
//    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authenticationProvider(authenticationProvider());
        http.csrf(AbstractHttpConfigurer::disable)
                //.userDetailsService(customUserDetailsService)
                .authorizeHttpRequests(auth ->
                        auth
                                .requestMatchers("/admin/**").hasAuthority("Admin")
                                .requestMatchers("/login*").permitAll()
                                .anyRequest().authenticated()
                )
                .formLogin(login -> login
                        .loginPage("/login")
                        .loginProcessingUrl("/login")
                        .successHandler((request, response, authentication) -> {
                            //potential risk
                            boolean isAdmin = authentication.getAuthorities().stream()
                                    .anyMatch(grantedAuthority -> "Admin".equalsIgnoreCase(grantedAuthority.getAuthority()));


                            // Get the username of the authenticated user
                            //String username = authentication.getName();

                            // Retrieve the user from the database based on the username
                            //SE2.Project.Backend.model.User user = userRepository.findByUsername(username); // Adjust this to your repository method

                            // Retrieve the role of the user
                            //String role = user.getRole();

                            if (isAdmin) {
                                response.sendRedirect("/admin/listAdmin");
                            } else {
                                response.sendRedirect("/");
                            }
                        })

                        .failureUrl("/login?error=true")
                        .failureHandler(authenticationFailureHandler())
                )
                .logout(logout -> logout
                        .logoutUrl("/")
                        .deleteCookies("JSESSIONID")
                        .logoutSuccessHandler(logoutSuccessHandler())
                );
        return http.build();
    }

    private AuthenticationFailureHandler authenticationFailureHandler() {
        return new SimpleUrlAuthenticationFailureHandler("/");
    }

    private LogoutSuccessHandler logoutSuccessHandler() {
        SimpleUrlLogoutSuccessHandler logoutSuccessHandler = new SimpleUrlLogoutSuccessHandler();
        logoutSuccessHandler.setDefaultTargetUrl("/login");
        return logoutSuccessHandler;
    }


}