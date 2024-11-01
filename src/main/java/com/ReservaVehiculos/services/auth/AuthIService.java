package com.ReservaVehiculos.services.auth;

import com.ReservaVehiculos.models.request.auth.AuthRequest;
import com.ReservaVehiculos.models.response.auth.AuthResponse;

public interface AuthIService {

    AuthResponse iniciarSesion(AuthRequest request);
}
