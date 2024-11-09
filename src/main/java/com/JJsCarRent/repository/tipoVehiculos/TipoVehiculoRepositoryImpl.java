package com.JJsCarRent.repository.tipoVehiculos;

import com.JJsCarRent.models.entity.TipoVehiculoEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class TipoVehiculoRepositoryImpl implements TipoVehiculoIRepository {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public boolean existsById(Integer id) {

        String sql = "SELECT COUNT(*) FROM tbl_tipo_vehiculo WHERE id=?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, id);

        return count != null && count > 0;
    }

    @Override
    public void save(TipoVehiculoEntity tipoVehiculoEntity) {
        String sql = "CALL agregar_tipo_vehiculo(?,?,?);";

        jdbcTemplate.update(sql, tipoVehiculoEntity.getTipo(),
                tipoVehiculoEntity.getDescripcion(),
                tipoVehiculoEntity.isActivo());
    }

    @Override
    public boolean existsByTipo(String tipo) {

        String sql = "SELECT COUNT(*) FROM tbl_tipo_vehiculo WHERE tipo=?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, tipo);

        return count != null && count > 0;
    }

    @Override
    public List<TipoVehiculoEntity> findAll() {
        String sql = "SELECT * FROM ver_tipos_vehiculos WHERE activo=1;";

        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            TipoVehiculoEntity tipoVehiculo = new TipoVehiculoEntity();
            tipoVehiculo.setId(rs.getInt("id"));
            tipoVehiculo.setTipo(rs.getString("tipo"));
            tipoVehiculo.setDescripcion(rs.getString("descripcion"));
            tipoVehiculo.setActivo(rs.getBoolean("activo"));
            return tipoVehiculo;
        });
    }

    @Override
    public String findTipoById(Integer id) {
        String sql = "SELECT tipo FROM ver_tipos_vehiculos WHERE id=?";

        return jdbcTemplate.queryForObject(sql, new Object[]{id}, String.class);

    }

    @Override
    public void desactivar(Integer id) {
        String sql = "CALL desactivar_tipo_vehiculo(?)";

        jdbcTemplate.update(sql, id);
    }
}
