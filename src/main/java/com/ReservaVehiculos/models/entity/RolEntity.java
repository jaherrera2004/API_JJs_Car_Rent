package com.ReservaVehiculos.models.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Getter @Setter

public class RolEntity {


    private Integer id;
    private  String rol;
    private String descripcion;

}
