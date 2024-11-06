package com.JJsCarRent.repository.modelos;

import com.JJsCarRent.models.entity.ModeloEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ModeloRepositoryImpl implements ModeloIRepository {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public void save(ModeloEntity modeloEntity) {
        String sql = "CALL agregar_modelo(?,?,?,?)";

        jdbcTemplate.update(sql,
                modeloEntity.getModelo(),
                modeloEntity.getIdMarca(),
                modeloEntity.getIdTipoVehiculo(),
                modeloEntity.isActivo());
    }

    @Override
    public boolean existsByModelo(String modelo) {
        String sql = "SELECT COUNT(*) FROM tbl_modelos WHERE modelo=?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, modelo);

        return count != null && count > 0;
    }

    @Override
    public boolean existsById(Integer id) {

        String sql = "SELECT COUNT(*) FROM tbl_modelos WHERE id=?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, id);

        return count != null && count > 0;
    }

    @Override
    public ModeloEntity findById(Integer id) {
        String sql = "SELECT * FROM ver_lista_modelos WHERE id=?";
        ModeloEntity modeloEntity = jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {

            ModeloEntity entity = new ModeloEntity();
            entity.setId(rs.getInt("id"));
            entity.setModelo(rs.getString("modelo"));
            entity.setActivo(rs.getBoolean("activo"));
            entity.setIdMarca(rs.getInt("id_marca"));
            entity.setIdTipoVehiculo(rs.getInt("id_tipo_vehiculo"));

            return entity;
        }, id);
        return modeloEntity;
    }

    @Override
    public List<ModeloEntity> findAll() {
        String sql = "SELECT * FROM ver_lista_modelos;";

        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            ModeloEntity modelo = new ModeloEntity();
            modelo.setId(rs.getInt("id"));
            modelo.setModelo(rs.getString("modelo"));
            modelo.setActivo(rs.getBoolean("activo"));
            modelo.setIdMarca(rs.getInt("id_marca"));
            modelo.setIdTipoVehiculo(rs.getInt("id_tipo_vehiculo"));
            return modelo;
        });
    }

    @Override
    public String findModeloById(Integer id) {
        String sql = "SELECT modelo FROM ver_lista_modelos WHERE id=?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, String.class);
    }
}
