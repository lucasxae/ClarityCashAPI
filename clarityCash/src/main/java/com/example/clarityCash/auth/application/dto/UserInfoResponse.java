package com.example.clarityCash.auth.application.dto;

import com.example.clarityCash.auth.domain.entity.FinanceDivisionType;

import java.math.BigDecimal;
import java.util.UUID;

public record UserInfoResponse(
        UUID id,
        String name,
        String email,
        String objectives,
        BigDecimal salary,
        FinanceDivisionType financeDivisionType,
        Integer billsPercentage,
        Integer expensesPercentage,
        Integer investmentsPercentage
) {}
