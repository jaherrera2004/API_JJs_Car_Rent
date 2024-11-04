package com.JJsCarRent.services.usuarios;


import com.JJsCarRent.models.response.usuarios.UsuarioDatosResponse;
import com.JJsCarRent.repository.roles.RolIRepository;
import com.JJsCarRent.utils.mappers.UsuarioMapper;
import com.JJsCarRent.models.dto.UsuarioDto;
import com.JJsCarRent.models.exceptions.HttpGenericException;
import com.JJsCarRent.models.request.usuario.UsuarioRequest;
import com.JJsCarRent.repository.usuarios.UsuarioIRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class UsuarioServiceImpl implements UsuarioIService {

    private final UsuarioMapper usuarioMapper;
    private final UsuarioIRepository usuarioIRepository;
    private final RolIRepository rolIRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void registrarUsuario(UsuarioRequest request) {

        if(usuarioIRepository.existsByUsername(request.getUsername())){
            throw new HttpGenericException(HttpStatus.BAD_REQUEST, "Este username ya esta registrado");
        }

        if (usuarioIRepository.existsByEmail(request.getEmail())) {
            throw new HttpGenericException(HttpStatus.BAD_REQUEST, "Ya hay un usuario registrado con este correo");
        }

        if (usuarioIRepository.existsByCedula(request.getCedula())) {
            throw new HttpGenericException(HttpStatus.BAD_REQUEST, "Ya hay un usuario registrado con esta cedula");
        }

        if(usuarioIRepository.existsByTelefono(request.getTelefono())){
            throw new HttpGenericException(HttpStatus.BAD_REQUEST, "Ya hay un usuario registrado con este telefono");
        }

        UsuarioDto usuarioDto = construirUsuario(request);
        usuarioIRepository.save(usuarioMapper.toEntity(usuarioDto));

    }

    @Override
    public void desactivarUsuario(Integer id) {

        if (!usuarioIRepository.existsById(id)) {
            throw new HttpGenericException(HttpStatus.BAD_REQUEST, "No encontramos el usuario con ese id");
        }

        UsuarioDto usuarioDto= usuarioMapper.toDto(usuarioIRepository.findById(id));
        if(!usuarioDto.isActivo()){
            throw new HttpGenericException(HttpStatus.BAD_REQUEST, "Este usuario ya esta desactivo");
        }

        if(usuarioDto.getIdRol()==1){
            throw new HttpGenericException(HttpStatus.BAD_REQUEST, "No puedes desactivar a un usuario");
        }

        usuarioIRepository.desactivarUsuario(id);
    }

    @Override
    public void activarUsuario(Integer id) {

        if (!usuarioIRepository.existsById(id)) {
            throw new HttpGenericException(HttpStatus.BAD_REQUEST, "No encontramos el usuario con ese id");
        }

        usuarioIRepository.activarUsuario(id);
    }

    @Override
    public List<UsuarioDatosResponse> obtenerListaUsuarios() {

        List<UsuarioDto> listaUsuariosDto = usuarioIRepository.findAll()
                .stream()
                .map(usuarioMapper::toDto)
                .collect(Collectors.toList());

        List<UsuarioDatosResponse> listaUsuariosResponse = new ArrayList<>();

        listaUsuariosDto.forEach(dto -> {
            listaUsuariosResponse.add(construirResponse(dto));
        });

        return listaUsuariosResponse;
    }

    @Override
    public UsuarioDatosResponse obtenerUsuarioPorId(Integer id) {
        UsuarioDto usuarioDto = usuarioMapper.toDto(usuarioIRepository.findById(id));
        return construirResponse(usuarioDto);
    }

    @Override
    public UsuarioDto obtenerUsuarioPorUsername(String username) {
        return usuarioMapper.toDto(usuarioIRepository.findByUsername(username));
    }

    private UsuarioDto construirUsuario(UsuarioRequest request) {
        return UsuarioDto.builder()
                .nombre(request.getNombre())
                .apellido(request.getApellido())
                .cedula(request.getCedula())
                .edad(request.getEdad())
                .email(request.getEmail())
                .username(request.getUsername())
                .telefono(request.getTelefono())
                .activo(true)
                .contrasenia(passwordEncoder.encode(request.getContrasenia()))
                .idRol(2)
                .build();
    }

    private UsuarioDatosResponse construirResponse(UsuarioDto dto){
        return UsuarioDatosResponse.builder()
                .id(dto.getId())
                .cedula(dto.getCedula())
                .nombre(dto.getNombre())
                .apellido(dto.getApellido())
                .email(dto.getEmail())
                .username(dto.getUsername())
                .telefono(dto.getTelefono())
                .rol(rolIRepository.findRolById(dto.getIdRol()))
                .edad(dto.getEdad())
                .build();
    }
}
