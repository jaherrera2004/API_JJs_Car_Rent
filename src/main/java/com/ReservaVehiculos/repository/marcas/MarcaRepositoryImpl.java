package com.ReservaVehiculos.repository.marcas;

import com.ReservaVehiculos.models.entity.MarcaEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class MarcaRepositoryImpl implements MarcaIRepository{

    private final JdbcTemplate jdbcTemplate;

    @Override
    public boolean existsByMarca(String marca) {

        String sql= "SELECT COUNT(*) FROM tbl_marcas WHERE marca=?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, marca);

        return count != null && count > 0;
    }

    @Override
    public void save(MarcaEntity marca) {
        String sql="INSERT INTO tbl_marcas (marca,activo) VALUES (?,?)";

        jdbcTemplate.update(sql,marca.getMarca(),marca.isActivo());
    }
}
