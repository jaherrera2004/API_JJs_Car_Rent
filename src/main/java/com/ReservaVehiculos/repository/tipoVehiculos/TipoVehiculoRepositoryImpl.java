package com.ReservaVehiculos.repository.tipoVehiculos;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class TipoVehiculoRepositoryImpl implements TipoVehiculoIRepository{

    private final JdbcTemplate jdbcTemplate;

    @Override
    public boolean existsById(Integer id) {

        String sql = "SELECT COUNT(*) FROM tbl_tipo_vehiculo WHERE id=?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, id);

        return count != null && count > 0;
    }
}
