package com.ReservaVehiculos.services.usuarios;


import com.ReservaVehiculos.mappers.UsuarioMapper;
import com.ReservaVehiculos.models.dto.UsuarioDto;
import com.ReservaVehiculos.models.exceptions.HttpGenericException;
import com.ReservaVehiculos.models.request.usuario.UsuarioRequest;
import com.ReservaVehiculos.repository.usuarios.UsuarioIRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class UsuarioServiceImpl implements UsuarioIService {

    private final UsuarioMapper usuarioMapper;
    private final UsuarioIRepository usuarioIRepository;

    @Override
    public void registrarUsuario(UsuarioRequest request) {

        UsuarioDto usuarioDto = construirUsuario(request);

        if (usuarioIRepository.existsByEmail(request.getEmail())) {
            throw new HttpGenericException(HttpStatus.BAD_REQUEST, "Ya hay un usuario registrado con este correo");
        }

        if (usuarioIRepository.existsByCedula(request.getCedula())) {
            throw new HttpGenericException(HttpStatus.BAD_REQUEST, "Ya hay un usuario registrado con esta cedula");
        }

        if (request.getEdad() <= 17) {
            throw new HttpGenericException(HttpStatus.BAD_REQUEST, "Debes ser mayor de edad para registrarte");
        }

        usuarioIRepository.save(usuarioMapper.toEntity(usuarioDto));

    }

    @Override
    public void desactivarUsuario(Integer id) {

        if (!usuarioIRepository.existsById(id)) {
            throw new HttpGenericException(HttpStatus.BAD_REQUEST, "No encontramos el usuario con ese id");
        }

        usuarioIRepository.desactivarUsuario(id);
    }

    private UsuarioDto construirUsuario(UsuarioRequest request) {
        return UsuarioDto.builder()
                .nombre(request.getNombre())
                .apellido(request.getApellido())
                .cedula(request.getCedula())
                .edad(request.getEdad())
                .email(request.getEmail())
                .telefono(request.getTelefono())
                .activo(true)
                .contrasenia(request.getContrasenia())
                .idRol(1)
                .build();
    }
}
