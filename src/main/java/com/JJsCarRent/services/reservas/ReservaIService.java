package com.JJsCarRent.services.reservas;

import com.JJsCarRent.models.request.reserva.ReservaRequest;
import com.JJsCarRent.models.response.reservas.ReservaResponse;

import java.util.List;

public interface ReservaIService {

    void crearReserva(ReservaRequest request);

    List<ReservaResponse> obtenerListaReservas();

    List<ReservaResponse> obtenerListaReservasPorIdUsuario(Integer idUsuario);

    void actualizarReserva(Integer idReserva, Integer idEstado);

}
