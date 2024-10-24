package com.ReservaVehiculos.repository.marcas;

import com.ReservaVehiculos.mappers.MarcaMapper;
import com.ReservaVehiculos.models.dto.MarcaDto;
import com.ReservaVehiculos.models.entity.MarcaEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Repository
public class MarcaRepositoryImpl implements MarcaIRepository{

    private final JdbcTemplate jdbcTemplate;
    private final MarcaMapper marcaMapper;

    @Override
    public boolean existsByMarca(String marca) {

        String sql= "SELECT COUNT(*) FROM tbl_marcas WHERE marca=?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, marca);

        return count != null && count > 0;
    }

    @Override
    public void save(MarcaEntity marca) {
        String sql="CALL agregar_marca(?,?);";

        jdbcTemplate.update(sql,marca.getMarca(),marca.isActivo());
    }

    @Override
    public List<MarcaDto> findAll() {
        String sql="SELECT * FROM ver_lista_marcas";

        List<MarcaEntity> listaMarcasEntity = jdbcTemplate.query(sql,(rs,rowNum)->{
           MarcaEntity marcaEntity = new MarcaEntity();

           marcaEntity.setId(rs.getInt("id"));
           marcaEntity.setMarca(rs.getString("marca"));
           marcaEntity.setActivo(rs.getBoolean("activo"));

           return marcaEntity;
        });

        return listaMarcasEntity.stream()
                .map(marcaMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void desactivarMarca(Integer id) {
        String sql="CALL desactivar_marca(?);";
        jdbcTemplate.update(sql,id);
    }

    @Override
    public boolean existsById(Integer id) {
        String sql= "SELECT COUNT(*) FROM tbl_marcas WHERE id=?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, id);
        return count != null && count > 0;
    }

    @Override
    public void activarMarca(Integer id) {
        String sql="CALL activar_marca(?);";
        jdbcTemplate.update(sql,id);
    }

    @Override
    public MarcaDto findById(Integer id) {

        String sql="SELECT * FROM ver_lista_marcas WHERE id=?";

        MarcaEntity marcaEntity= jdbcTemplate.queryForObject(sql,(rs,rowNum)->{
            MarcaEntity entity = new MarcaEntity();

            entity.setId(rs.getInt("id"));
            entity.setMarca(rs.getString("marca"));
            entity.setActivo(rs.getBoolean("activo"));

            return entity;

        },id);
        return marcaMapper.toDto(marcaEntity);
    }
}
