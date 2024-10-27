package com.ReservaVehiculos.repository.modelos;

import com.ReservaVehiculos.models.entity.ModeloEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ModeloRepositoryImpl implements ModeloIRepository{

    private final JdbcTemplate jdbcTemplate;

    @Override
    public void save(ModeloEntity modeloEntity) {
        String sql="CALL agregar_modelo(?,?,?,?)";

        jdbcTemplate.update(sql,
                modeloEntity.getModelo(),
                modeloEntity.getIdMarca(),
                modeloEntity.getIdTipoVehiculo(),
                modeloEntity.isActivo());
    }

    @Override
    public boolean existsByModelo(String modelo) {
        String sql= "SELECT COUNT(*) FROM tbl_modelos WHERE modelo=?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, modelo);

        return count != null && count > 0;
    }
}
