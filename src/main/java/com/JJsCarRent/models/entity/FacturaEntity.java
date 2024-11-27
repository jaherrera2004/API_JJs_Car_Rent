package com.JJsCarRent.models.entity;

import lombok.Data;

import java.time.LocalDate;

@Data
public class FacturaEntity {

    private Integer id;
    private float valor;
    private LocalDate fecha;
    private Integer idReserva;

}
