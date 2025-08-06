package com.tfm.bandas.usuarios.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class JwtAccessDeniedHandler implements AccessDeniedHandler {

    // Configura el manejo de excepciones de autenticación. Se envia un error 403 Forbidden
    // Sirve para responder a solicitudes que no tienen los permisos necesarios para acceder a un recurso específico.
    @Override
    public void handle(HttpServletRequest request,
                       HttpServletResponse response,
                       AccessDeniedException accessDeniedException)
            throws IOException {
        response.sendError(HttpServletResponse.SC_FORBIDDEN, "Forbidden");
    }
}
