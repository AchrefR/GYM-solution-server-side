package gym_solution.demo.config;

import gym_solution.demo.JWT.JwtFilter;
import gym_solution.demo.pattern.factory.MemberRole;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtFilter jwtFilter;

    // Authorities are stored as the raw role name (e.g. "ADMIN"), so we match with
    // hasAuthority(...) rather than hasRole(...) which would expect the "ROLE_" prefix.
    private static final String ADMIN = MemberRole.ADMIN.getRoleName();
    private static final String TRAINER = MemberRole.TRAINER.getRoleName();
    private static final String MEMBER = MemberRole.MEMBER.getRoleName();

    public SecurityConfig(JwtFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        // Public auth: login only. Account creation is admin-only.
                        .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
                        .requestMatchers(HttpMethod.POST, "/auth/register").hasAuthority(ADMIN)
                        .requestMatchers("/auth/me").authenticated()

                        // admin-only management
                        .requestMatchers("/administrator/**", "/user/**").hasAuthority(ADMIN)

                        // ---- Member accounts & self-service ----
                        // Only admins may create member accounts; admins may list every member.
                        .requestMatchers(HttpMethod.POST, "/member/").hasAuthority(ADMIN)
                        .requestMatchers(HttpMethod.GET, "/member/").hasAuthority(ADMIN)
                        // Workout assignment to a member is a coaching action (admin/trainer);
                        // members may read their own plans/sessions.
                        .requestMatchers(HttpMethod.POST, "/member/*/workout-plans/**",
                                "/member/*/workout-sessions/**").hasAnyAuthority(ADMIN, TRAINER)
                        .requestMatchers(HttpMethod.DELETE, "/member/*/workout-plans/**",
                                "/member/*/workout-sessions/**").hasAnyAuthority(ADMIN, TRAINER)
                        .requestMatchers(HttpMethod.GET, "/member/*/workout-plans/**",
                                "/member/*/workout-sessions/**").hasAnyAuthority(ADMIN, TRAINER, MEMBER)
                        // A member reads/edits their own profile; trainers may read a client; admin all.
                        .requestMatchers(HttpMethod.GET, "/member/**").hasAnyAuthority(ADMIN, TRAINER, MEMBER)
                        .requestMatchers(HttpMethod.PUT, "/member/").hasAnyAuthority(ADMIN, MEMBER)
                        .requestMatchers(HttpMethod.DELETE, "/member/**").hasAuthority(ADMIN)

                        // ---- Trainer accounts & training resources ----
                        // Only admins may create trainer accounts.
                        .requestMatchers(HttpMethod.POST, "/trainer/").hasAuthority(ADMIN)
                        .requestMatchers("/trainer/**", "/workoutPlan/**", "/workoutSession/**")
                            .hasAnyAuthority(ADMIN, TRAINER)

                        // ---- Subscriptions (member self-service) ----
                        // subscription plan catalog: anyone authenticated reads, only admins change
                        .requestMatchers(HttpMethod.GET, "/subscription-type/**").authenticated()
                        .requestMatchers("/subscription-type/**").hasAuthority(ADMIN)
                        // a member subscribes to / views plans; admins manage them all
                        .requestMatchers("/subscription/**").hasAnyAuthority(ADMIN, MEMBER)

                        // ---- Body metrics / progress ----
                        // members log & read their own; trainers read a client's; admins all
                        .requestMatchers("/body-metric/**").hasAnyAuthority(ADMIN, TRAINER, MEMBER)
                        .requestMatchers("/strength-record/**").hasAnyAuthority(ADMIN, TRAINER, MEMBER)

                        // ---- Payments: financial records, admin only ----
                        .requestMatchers("/payment/**").hasAuthority(ADMIN)

                        // ---- Notifications: admins manage, everyone reads their own ----
                        .requestMatchers(HttpMethod.GET, "/notification/**").authenticated()
                        .requestMatchers("/notification/**").hasAuthority(ADMIN)

                        // everything else just needs a valid token
                        .anyRequest().authenticated()
                )
                // Return 401 instead of a redirect/500 when authentication is missing or invalid.
                .exceptionHandling(ex -> ex.authenticationEntryPoint(restAuthenticationEntryPoint()))
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public AuthenticationEntryPoint restAuthenticationEntryPoint() {
        return (request, response, authException) -> {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setContentType("application/json");
            response.getWriter().write("{\"error\":\"Unauthorized\",\"message\":\"Authentication required\"}");
        };
    }

    // Comma-separated list of browser origins permitted to call the API.
    // Defaults cover the Angular dev server and the dockerized nginx gateway;
    // override with CORS_ALLOWED_ORIGINS for other deployments.
    @Value("${CORS_ALLOWED_ORIGINS:http://localhost:4200,http://localhost:4000,http://localhost:8088}")
    private String allowedOrigins;

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of(allowedOrigins.split(",")));
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH"));
        configuration.setAllowedHeaders(List.of("*"));
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
