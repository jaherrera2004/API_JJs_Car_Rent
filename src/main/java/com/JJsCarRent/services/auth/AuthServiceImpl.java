package com.JJsCarRent.services.auth;

import com.JJsCarRent.models.CustomUserDetails;
import com.JJsCarRent.models.dto.UsuarioDto;
import com.JJsCarRent.models.exceptions.HttpGenericException;
import com.JJsCarRent.models.request.auth.AuthRequest;
import com.JJsCarRent.models.response.auth.AuthResponse;
import com.JJsCarRent.models.response.usuarios.UsuarioDatosResponse;
import com.JJsCarRent.repository.roles.RolIRepository;
import com.JJsCarRent.repository.usuarios.UsuarioIRepository;
import com.JJsCarRent.services.JwtService;
import com.JJsCarRent.utils.mappers.UsuarioMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements AuthIService{

    private final UsuarioIRepository usuarioIRepository;
    private final UsuarioMapper usuarioMapper;
    private final PasswordEncoder passwordEncoder;
    private final RolIRepository rolIRepository;
    private final JwtService jwtService;

    @Override
    public AuthResponse iniciarSesion(AuthRequest request) {
        UsuarioDto datosUsuario = usuarioMapper.toDto(usuarioIRepository.findByUsername(request.getUsuario()));

        if(!passwordEncoder.matches(request.getContrasenia(), datosUsuario.getContrasenia())){
            throw new HttpGenericException(HttpStatus.BAD_REQUEST, "Username o contraseña incorrectos");
        }

        List<String> permisos= rolIRepository.findPermisosByIdRol(datosUsuario.getIdRol());
        List<SimpleGrantedAuthority> authorities = permisos
                .stream()
                .map(SimpleGrantedAuthority::new)
                .toList();

        List<GrantedAuthority> grantedAuthorities = new ArrayList<>(authorities);
        CustomUserDetails customUserDetails = new CustomUserDetails(datosUsuario.getId(), request.getUsuario(), "", grantedAuthorities);

        String token = jwtService.generateToken(customUserDetails, permisos, datosUsuario.getId());
        String rol =rolIRepository.findRolById(datosUsuario.getIdRol()) ;

        return construirAuthResponse(token,datosUsuario,rol);
    }
    private AuthResponse construirAuthResponse(String token, UsuarioDto datosUsuario,String rol){
        return AuthResponse.builder()
                .accessToken(token)
                .usuario(UsuarioDatosResponse.builder()
                        .nombre(datosUsuario.getNombre())
                        .apellido(datosUsuario.getApellido())
                        .email(datosUsuario.getEmail())
                        .username(datosUsuario.getUsername())
                        .rol(rol)
                        .build())
                .build();
    }

}
