//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.web.SecurityFilterChain;
//@Configuration
//public class SecurityConfig {
//    @Bean
//    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        return http
//                .authorizeHttpRequests(
//                        req->req
//                                .requestMatchers("/", "/signin").permitAll()
//                                .anyRequest().authenticated()
//                ).formLogin(form -> form
//                .loginPage("/custom-login") // Specify the URL of your custom login page
//                .permitAll()
//        ).build();
//    }
//}