package com.JJsCarRent.repository.vehiculos;

import com.JJsCarRent.models.entity.VehiculoEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class VehiculoRepositoryImpl implements VehiculoIRepository{

    private final JdbcTemplate jdbcTemplate;

    @Override
    public void save(VehiculoEntity vehiculoEntity) {
        String sql="CALL agregar_vehiculo(?,?,?,?,?,?,?);";

        jdbcTemplate.update(sql,vehiculoEntity.getPlaca(),
                vehiculoEntity.getAnio(),
                vehiculoEntity.getKilometraje(),
                vehiculoEntity.getValorDia(),
                vehiculoEntity.getColor(),
                vehiculoEntity.getIdModelo(),
                vehiculoEntity.isActivo());
    }

    @Override
    public boolean existsByPlaca(String placa) {
        String sql = "SELECT COUNT(*) FROM tbl_vehiculos WHERE placa=?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, placa);

        return count != null && count > 0;
    }
}
