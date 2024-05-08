package SE2.Project.Backend.service;

import SE2.Project.Backend.model.User;
import SE2.Project.Backend.repository.UserRepository;
import jakarta.servlet.ServletContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.DelegatingFilterProxy;

import javax.sql.DataSource;

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
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.authenticationProvider(authenticationProvider());
//    }
    @Autowired
    private UserRepository userRepository;


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
        http.csrf(c -> c.disable())
                .authorizeHttpRequests(auth ->
                    auth
                            .requestMatchers("/admin/**").hasRole("Admin")
                            .requestMatchers("/login*").permitAll()
                            .anyRequest().authenticated()
                )
                .formLogin(login -> login
                        .loginPage("/login")
                        .loginProcessingUrl("/login")
                        .successHandler((request, response, authentication) -> {
                            // Get the username of the authenticated user
                            String username = authentication.getName();

                            // Retrieve the user from the database based on the username
                            User user = userRepository.findByUsername(username); // Adjust this to your repository method

                            // Retrieve the role of the user
                            String role = user.getRole();

                            if ("Admin".equals(role)) {
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
        return new SimpleUrlAuthenticationFailureHandler("/login?error=true");
    }

    private LogoutSuccessHandler logoutSuccessHandler() {
        SimpleUrlLogoutSuccessHandler logoutSuccessHandler = new SimpleUrlLogoutSuccessHandler();
        logoutSuccessHandler.setDefaultTargetUrl("/login");
        return logoutSuccessHandler;
    }

//    public class AppInitializer implements WebApplicationInitializer {
//
//        @Override
//        public void onStartup(ServletContext sc) {
//
//            AnnotationConfigWebApplicationContext root = new AnnotationConfigWebApplicationContext();
//            root.register(SecurityCfg.class);
//
//            sc.addListener(new ContextLoaderListener(root));
//
//            sc.addFilter("securityFilter", new DelegatingFilterProxy("springSecurityFilterChain"))
//                    .addMappingForUrlPatterns(null, false, "/*");
//        }
//    }
}