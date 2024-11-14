package com.JJsCarRent.repository.reservas;

import com.JJsCarRent.models.entity.ReservaEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class ReservaRepositoryImpl implements ReservaIRepository {

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

    @Override
    public List<ReservaEntity> findAll() {
        String sql = "SELECT * FROM ver_reservas";

        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            ReservaEntity reserva = new ReservaEntity();
            reserva.setId(rs.getInt("id"));
            reserva.setIdVehiculo(rs.getInt("id_vehiculo"));
            reserva.setIdUsuario(rs.getInt("id_usuario"));
            reserva.setIdEstado(rs.getInt("id_estado"));
            reserva.setFechaInicio(rs.getDate("fecha_inicio").toLocalDate());
            reserva.setFechaEntrega(rs.getDate("fecha_fin").toLocalDate());
            return reserva;
        });
    }

    @Override
    public List<ReservaEntity> findAllByIdUsuario(Integer idUsuario) {
        String sql = "SELECT * FROM ver_reservas WHERE id_usuario=?";

        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            ReservaEntity reserva = new ReservaEntity();
            reserva.setId(rs.getInt("id"));
            reserva.setIdVehiculo(rs.getInt("id_vehiculo"));
            reserva.setIdUsuario(rs.getInt("id_usuario"));
            reserva.setIdEstado(rs.getInt("id_estado"));
            reserva.setFechaInicio(rs.getDate("fecha_inicio").toLocalDate());
            reserva.setFechaEntrega(rs.getDate("fecha_fin").toLocalDate());
            return reserva;

        }, idUsuario);
    }

    @Override
    public String findEstadoById(Integer id) {
        String sql = "SELECT estado FROM tbl_estado_reserva WHERE id=?";
        return jdbcTemplate.queryForObject(sql, String.class, id);
    }

    @Override
    public boolean existsEstadoById(Integer id) {
        String sql = "SELECT COUNT(*) FROM tbl_estado_reserva WHERE id=?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, id);

        return count != null && count > 0;
    }

    @Override
    public boolean existsById(Integer id) {
        String sql = "SELECT COUNT(*) FROM tbl_reservas WHERE id=?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, id);

        return count != null && count > 0;
    }

    @Override
    public void updateEstado(Integer idReserva, Integer idEstado) {
        String sql = "UPDATE tbl_reservas SET id_estado=? WHERE id=?";
        jdbcTemplate.update(sql, idEstado, idReserva);
    }


}
