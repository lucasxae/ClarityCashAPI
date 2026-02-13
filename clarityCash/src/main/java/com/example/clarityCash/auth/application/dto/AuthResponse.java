package com.example.clarityCash.auth.application.dto;

public record AuthResponse(
    String token,
    String email,
    String name
) {}
