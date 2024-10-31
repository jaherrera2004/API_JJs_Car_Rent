package com.ReservaVehiculos.config;

import com.ReservaVehiculos.models.CustomUserDetails;
import com.ReservaVehiculos.models.dto.UsuarioDto;
import com.ReservaVehiculos.services.JwtService;
import com.ReservaVehiculos.services.usuarios.UsuarioIService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtRequestFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UsuarioIService usuarioIService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {


        final String authorizationHeader = request.getHeader("Authorization");

        String username = null;
        String jwt = null;

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            jwt = authorizationHeader.substring(7);
            username = jwtService.extractUsername(jwt);
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UsuarioDto usuarioOpt = usuarioIService.obtenerUsuarioPorUsername(username);

            if (jwtService.validateToken(jwt, usuarioOpt)) {
                var permisos = jwtService.extractPermisos(jwt);

                var authorities = permisos.stream()
                        .map(SimpleGrantedAuthority::new)
                        .toList();

                List<GrantedAuthority> grantedAuthorities = new ArrayList<>(authorities);

                CustomUserDetails userDetails = new CustomUserDetails(username, "", grantedAuthorities);

                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                        new UsernamePasswordAuthenticationToken(userDetails, null, authorities);
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }

        chain.doFilter(request, response);
    }
}
