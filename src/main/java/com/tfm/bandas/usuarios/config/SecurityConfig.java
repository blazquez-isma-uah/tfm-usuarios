package com.tfm.bandas.usuarios.config;

import com.tfm.bandas.usuarios.auth.JwtFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    private final JwtFilter jwtFilter;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;

    public SecurityConfig(JwtFilter jwtFilter,
                          JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint,
                          JwtAccessDeniedHandler jwtAccessDeniedHandler) {
        this.jwtFilter = jwtFilter;
        this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
        this.jwtAccessDeniedHandler = jwtAccessDeniedHandler;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable) // Desactivar CSRF
                // configura la política de creación de sesiones como Stateless para que no se creen sesiones en el servidor.
                // Esto es importante para APIs REST, ya que cada solicitud debe ser independiente y no depender de un estado de sesión en el servidor.
                // Con esta configuración, Spring Security no intentará crear o mantener sesiones de usuario, lo que es adecuado para aplicaciones RESTful donde cada solicitud debe ser
                // independiente y no debe depender de un estado de sesión en el servidor.
                .sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .exceptionHandling(e -> e
                        .authenticationEntryPoint(jwtAuthenticationEntryPoint) // Manejo de errores de autenticación
                        .accessDeniedHandler(jwtAccessDeniedHandler) // Manejo de errores de acceso denegado
                )
                .authorizeHttpRequests(auth -> auth
                    .requestMatchers("/auth/**").permitAll()  // público
                    //.anyRequest().permitAll() // Permitir todo
                    .anyRequest().authenticated() // Requiere autenticación
                )
                // Añadir el filtro JWT antes de la autenticación por defecto
                .addFilterBefore(jwtFilter, org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter.class)
        ;

        return http.build();
    }
}
