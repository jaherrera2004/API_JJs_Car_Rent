package com.JJsCarRent.services.auth;

import com.JJsCarRent.models.request.auth.AuthRequest;
import com.JJsCarRent.models.response.auth.AuthResponse;

public interface AuthIService {

    AuthResponse iniciarSesion(AuthRequest request);
}
