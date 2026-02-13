package com.example.clarityCash.auth.application.dto;

import com.example.clarityCash.auth.domain.entity.FinanceDivisionType;

import java.math.BigDecimal;

public record UserInfoRequest(
        String name,
        String objectives,
        BigDecimal salary,
        FinanceDivisionType financeDivisionType,
        Integer billsPercentage,
        Integer expensesPercentage,
        Integer investmentsPercentage
) {}
