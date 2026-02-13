package com.example.clarityCash.auth.application.dto;

public record LoginRequest(
    String email,
    String password
) {}
