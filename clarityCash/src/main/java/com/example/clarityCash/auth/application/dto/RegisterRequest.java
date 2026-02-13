package com.example.clarityCash.auth.application.dto;

public record RegisterRequest(
    String name,
    String email,
    String password
) {}
