package com.JJsCarRent.repository.reservas;

import com.JJsCarRent.models.entity.ReservaEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class ReservaRepositoryImpl implements ReservaIRepository{

    private final JdbcTemplate jdbcTemplate;

    @Override
    public void save(ReservaEntity entity) {
        String sql = "CALL registrar_reserva(?, ?, ?, ?, ?)";

        jdbcTemplate.update(sql,
                entity.getFechaInicio(),
                entity.getFechaEntrega(),
                entity.getIdUsuario(),
                entity.getIdVehiculo(),
                entity.getIdEstado()
        );
    }

}
