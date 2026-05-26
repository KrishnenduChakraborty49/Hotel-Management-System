package com.hotel.booking.service;
import com.hotel.booking.dto.LoginRequest;
import com.hotel.booking.dto.RegisterRequest;

public interface AuthService {
    String login(LoginRequest loginDto);
    String register(RegisterRequest registerDto);
}
