package com.ReservaVehiculos.services.marcas.logo;

import com.ReservaVehiculos.models.request.marcas.MarcaLogoRequest;
import com.ReservaVehiculos.repository.marcas.MarcaIRepository;
import com.ReservaVehiculos.utils.ArchivoUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;

@RequiredArgsConstructor
@Service
public class MarcaLogoServiceImpl implements MarcaLogoIService{

    private final ArchivoUtil archivoUtil;
    private final MarcaIRepository marcaIRepository;

    @Override
    public void agregarLogo(MarcaLogoRequest request) throws IOException {

        String archivo = archivoUtil.subirArchivo(request.getLogo());


    }
}
