package com.JJsCarRent.repository.facturas;

import com.JJsCarRent.models.entity.FacturaEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@RequiredArgsConstructor
@Repository
public class FacturaRepositoryImpl implements FacturaIRepository{

    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<FacturaEntity> obtenerFacturas() {
        String sql = "SELECT id, valor, fecha, id_reserva FROM tbl_facturas";
        return jdbcTemplate.query(sql, new FacturaRowMapper());
    }

    @Override
    public float obtenerGanancias() {
        String sql = "SELECT SUM(valor) FROM tbl_facturas";
        Float totalGanancias = jdbcTemplate.queryForObject(sql, Float.class);
        return totalGanancias != null ? totalGanancias : 0f;
    }


    private static class FacturaRowMapper implements RowMapper<FacturaEntity> {

        @Override
        public FacturaEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
            FacturaEntity factura = new FacturaEntity();
            factura.setId(rs.getInt("id"));
            factura.setValor(rs.getFloat("valor"));
            factura.setFecha(rs.getTimestamp("fecha").toLocalDateTime().toLocalDate());
            factura.setIdReserva(rs.getInt("id_reserva"));
            return factura;
        }
    }
}
