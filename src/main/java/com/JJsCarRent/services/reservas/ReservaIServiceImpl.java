package com.JJsCarRent.services.reservas;

import com.JJsCarRent.models.dto.ReservaDto;
import com.JJsCarRent.models.exceptions.HttpGenericException;
import com.JJsCarRent.models.request.reserva.ReservaRequest;
import com.JJsCarRent.models.response.reservas.ReservaResponse;
import com.JJsCarRent.repository.reservas.ReservaIRepository;
import com.JJsCarRent.repository.usuarios.UsuarioIRepository;
import com.JJsCarRent.repository.vehiculos.VehiculoIRepository;
import com.JJsCarRent.utils.mappers.ReservaMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ReservaIServiceImpl implements ReservaIService {

    private final ReservaIRepository reservaIRepository;
    private final UsuarioIRepository usuarioIRepository;
    private final VehiculoIRepository vehiculoIRepository;

    private final ReservaMapper reservaMapper;

    @Override
    public void crearReserva(ReservaRequest request) {

        if (!usuarioIRepository.existsById(request.getIdUsuario())) {
            throw new HttpGenericException(HttpStatus.BAD_REQUEST, "El usuario ingresado no existe");
        }

        if (!vehiculoIRepository.existsById(request.getIdVehiculo())) {
            throw new HttpGenericException(HttpStatus.BAD_REQUEST, "El vehiculo ingresado no existe");
        }

        if(!vehiculoIRepository.isVehiculoDisponible(request.getIdVehiculo(), request.getFechaInicio(),request.getFechaEntrega())){
            throw new HttpGenericException(HttpStatus.BAD_REQUEST, "El vehiculo ingresado no esta disponible para esta fecha");
        }

        ReservaDto reservaDto = construirDto(request);
        reservaDto.setIdEstado(2);
        reservaIRepository.save(reservaMapper.toEntity(reservaDto));
    }

    @Override
    public List<ReservaResponse> obtenerListaReservas() {
        List<ReservaDto> listaReservasDto = reservaIRepository.findAll()
                .stream()
                .map(reservaMapper::toDto)
                .toList();

        List<ReservaResponse> listaReservasResponses = new ArrayList<>();
        listaReservasDto.forEach(reservaDto -> listaReservasResponses.add(construirResponse(reservaDto)));

        return listaReservasResponses;
    }

    @Override
    public List<ReservaResponse> obtenerListaReservasPorIdUsuario(Integer idUsuario) {
        if (!usuarioIRepository.existsById(idUsuario)) {
            throw new HttpGenericException(HttpStatus.BAD_REQUEST, "El usuario ingresado no existe");
        }

        List<ReservaDto> listaReservasDto = reservaIRepository.findAllByIdUsuario(idUsuario)
                .stream()
                .map(reservaMapper::toDto)
                .toList();

        List<ReservaResponse> listaReservasResponses = new ArrayList<>();
        listaReservasDto.forEach(reservaDto -> listaReservasResponses.add(construirResponse(reservaDto)));

        return listaReservasResponses;
    }

    @Override
    public void actualizarReserva(Integer idReserva, Integer idEstado) {
        if(!reservaIRepository.existsById(idReserva)){
            throw new HttpGenericException(HttpStatus.BAD_REQUEST,"La reserva ingresada no existe");
        }

        if(!reservaIRepository.existsEstadoById(idEstado)){
            throw new HttpGenericException(HttpStatus.BAD_REQUEST,"El estado ingresado no existe");
        }

        reservaIRepository.updateEstado(idReserva,idEstado);
    }

    private ReservaDto construirDto(ReservaRequest request) {
        return ReservaDto.builder()
                .idUsuario(request.getIdUsuario())
                .idVehiculo(request.getIdVehiculo())
                .fechaInicio(request.getFechaInicio())
                .fechaEntrega(request.getFechaEntrega())
                .idEstado(1)
                .build();
    }

    private ReservaResponse construirResponse(ReservaDto reservaDto) {
        return ReservaResponse.builder()
                .id(reservaDto.getId())
                .cedula(usuarioIRepository.findById(reservaDto.getIdUsuario()).getCedula())
                .placaVehiculo(vehiculoIRepository.findById(reservaDto.getIdVehiculo()).getPlaca())
                .fechaInicio(reservaDto.getFechaInicio())
                .fechaEntrega(reservaDto.getFechaEntrega())
                .estado(reservaIRepository.findEstadoById(reservaDto.getIdEstado()))
                .build();
    }
}
