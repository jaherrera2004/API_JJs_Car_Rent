package com.JJsCarRent.repository.vehiculos;

import com.JJsCarRent.models.entity.VehiculoEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

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

    @Override
    public boolean existsById(Integer id) {
        String sql = "SELECT COUNT(*) FROM tbl_vehiculos WHERE id=?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, id);

        return count != null && count > 0;
    }

    @Override
    public List<VehiculoEntity> findAll() {
        String sql = "SELECT * FROM ver_lista_vehiculos WHERE activo=1";

        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            VehiculoEntity vehiculo = new VehiculoEntity();
            vehiculo.setId(rs.getInt("id"));
            vehiculo.setPlaca(rs.getString("placa"));
            vehiculo.setAnio(rs.getInt("anio"));
            vehiculo.setKilometraje(rs.getFloat("kilometraje"));
            vehiculo.setValorDia(rs.getFloat("valor_dia"));
            vehiculo.setColor(rs.getString("color"));
            vehiculo.setActivo(rs.getBoolean("activo"));
            vehiculo.setIdModelo(rs.getInt("id_modelo"));
            return vehiculo;
        });
    }

    @Override
    public void desactivar(Integer id) {
        String sql="CALL desactivar_vehiculo(?)";
        jdbcTemplate.update(sql,id);
    }

    @Override
    public void activar(Integer id) {
        String sql = "CALL activar_vehiculo(?)";
        jdbcTemplate.update(sql,id);
    }

    @Override
    public void agregarFoto(String foto, String placa) {
            String sql = "CALL agregar_foto_vehiculo(?,?)";
            jdbcTemplate.update(sql,foto,placa);
        }

    @Override
    public String fotoByIdVehiculo(Integer id) {
        String sql = "SELECT foto FROM tbl_vehiculoso WHERE id= ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, String.class);
    }

}
