package br.com.elo.configuracao;
import jakarta.servlet.DispatcherType;
import org.springframework.context.annotation.*;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
// -------------------------------------------------------------------------------------
@Configuration
public class SegurancaConfiguracao {
    @Bean
    PasswordEncoder passwordEncoder() { return new BCryptPasswordEncoder(12); }
    // -------------------------------------------------------------------------------------
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> auth
                .dispatcherTypeMatchers(DispatcherType.ERROR).permitAll()
                .requestMatchers("/", "/login", "/cadastro", "/acesso-negado", "/css/**", "/temas/**").permitAll()
                .requestMatchers("/aluno/**").hasRole("ALUNO")
                .requestMatchers("/professor/**").hasRole("PROFESSOR")
                .requestMatchers("/administrador/**").hasRole("ADMINISTRADOR")
                .requestMatchers("/inicio").authenticated()
                .anyRequest().denyAll())
            .formLogin(login -> login.loginPage("/login").usernameParameter("email")
                .passwordParameter("senha").defaultSuccessUrl("/inicio", true)
                .failureUrl("/login?erro").permitAll())
            .logout(logout -> logout.logoutUrl("/logout").logoutSuccessUrl("/login?saiu")
                .invalidateHttpSession(true).clearAuthentication(true).deleteCookies("SESSION"))
            .exceptionHandling(error -> error.accessDeniedPage("/acesso-negado"))
            .sessionManagement(session -> session.sessionFixation(fixation -> fixation.changeSessionId()))
            .headers(headers -> headers.contentSecurityPolicy(csp -> csp.policyDirectives(
                "default-src 'self'; style-src 'self'; img-src 'self' data:; script-src 'none'; frame-ancestors 'none'; form-action 'self'; base-uri 'self'")));
        return http.build();
    }
}
