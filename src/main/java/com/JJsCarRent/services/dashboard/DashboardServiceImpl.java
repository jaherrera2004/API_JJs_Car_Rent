package com.JJsCarRent.services.dashboard;

import com.JJsCarRent.models.response.DashboardResponse;
import com.JJsCarRent.repository.facturas.FacturaIRepository;
import com.JJsCarRent.repository.reservas.ReservaIRepository;
import com.JJsCarRent.repository.usuarios.UsuarioIRepository;
import com.JJsCarRent.repository.vehiculos.VehiculoIRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class DashboardServiceImpl implements DashboardIService {

    private final FacturaIRepository facturaIRepository;
    private final VehiculoIRepository vehiculoIRepository;
    private final UsuarioIRepository usuarioIRepository;
    private final ReservaIRepository reservaIRepository;

    @Override
    public DashboardResponse obtenerDashboard() {
        return DashboardResponse.builder()
                .totalGanancias(facturaIRepository.obtenerGanancias())
                .totalClientes(usuarioIRepository.obtenerTotalUsuarios())
                .totalReservas(reservaIRepository.obtenerTotalReservas())
                .totalVehiculos(vehiculoIRepository.obtenerTotalVehiculos())
                .build();
    }
}
