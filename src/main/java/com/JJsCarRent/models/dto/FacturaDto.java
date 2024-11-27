package com.JJsCarRent.models.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class FacturaDto {

    private Integer id;
    private float valor;
    private LocalDate fecha;
    private Integer idReserva;
}
