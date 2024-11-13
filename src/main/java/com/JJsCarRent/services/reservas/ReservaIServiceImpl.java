package com.JJsCarRent.services.reservas;

import com.JJsCarRent.models.dto.ReservaDto;
import com.JJsCarRent.models.exceptions.HttpGenericException;
import com.JJsCarRent.models.request.reserva.ReservaRequest;
import com.JJsCarRent.repository.reservas.ReservaIRepository;
import com.JJsCarRent.repository.usuarios.UsuarioIRepository;
import com.JJsCarRent.repository.vehiculos.VehiculoIRepository;
import com.JJsCarRent.utils.mappers.ReservaMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ReservaIServiceImpl implements ReservaIService {

    private final ReservaIRepository reservaIRepository;
    private final UsuarioIRepository usuarioIRepository;
    private final VehiculoIRepository vehiculoIRepository;

    private final ReservaMapper reservaMapper;

    @Override
    public void agregarFactura(ReservaRequest request) {

        if (!usuarioIRepository.existsById(request.getIdUsuario())) {
            throw new HttpGenericException(HttpStatus.BAD_REQUEST, "El usuario ingresado no existe");
        }

        if (!vehiculoIRepository.existsById(request.getIdVehiculo())) {
            throw new HttpGenericException(HttpStatus.BAD_REQUEST, "El vehiculo ingresado no existe");
        }

        ReservaDto reservaDto = construirDto(request);
        reservaIRepository.save(reservaMapper.toEntity(reservaDto));
    }
    private ReservaDto construirDto(ReservaRequest request){
        return ReservaDto.builder()
                .idUsuario(request.getIdUsuario())
                .idVehiculo(request.getIdVehiculo())
                .fechaInicio(request.getFechaInicio())
                .fechaEntrega(request.getFechaEntrega())
                .idEstado(1)
                .build();
    }
}
