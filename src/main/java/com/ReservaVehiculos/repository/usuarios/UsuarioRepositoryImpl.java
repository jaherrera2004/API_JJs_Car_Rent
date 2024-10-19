package com.ReservaVehiculos.repository.usuarios;

import com.ReservaVehiculos.models.entity.UsuarioEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
@RequiredArgsConstructor
public class UsuarioRepositoryImpl implements UsuarioIRepository {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public void save(UsuarioEntity usuarioEntity) {

        String sql = "INSERT INTO tbl_usuarios" +
                "(cedula,nombre,apellido,edad,email,telefono,contrasenia,activo,id_rol)" +
                "VALUES (?,?,?,?,?,?,?,?,?)";

        jdbcTemplate.update(sql, usuarioEntity.getCedula(),
                usuarioEntity.getNombre(),
                usuarioEntity.getApellido(),
                usuarioEntity.getEdad(),
                usuarioEntity.getEmail(),
                usuarioEntity.getTelefono(),
                usuarioEntity.getContrasenia(),
                usuarioEntity.isActivo(),
                usuarioEntity.getIdRol());
    }

    @Override
    public void deleteById(Integer id) {

        String sql="UPDATE tbl_usuarios SET activo=false WHERE id=?";
        jdbcTemplate.update(sql,id);

    }

    @Override
    public boolean existsByEmail(String email) {

        String sql = "SELECT COUNT(*) FROM tbl_usuarios WHERE email=?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, email);

        return count != null && count > 0;

    }

    @Override
    public boolean existsByCedula(String cedula) {

        String sql= "SELECT COUNT(*) FROM tbl_usuarios WHERE cedula=?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, cedula);

        return count != null && count > 0;
    }

    @Override
    public boolean existsById(Integer id) {

        String sql= "SELECT COUNT(*) FROM tbl_usuarios WHERE id=?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, id);

        return count != null && count > 0;
    }
}
