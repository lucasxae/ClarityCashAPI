package com.example.clarityCash.auth.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;
    private String email;
    private String password;
    private String role;
    private Boolean enabled;

    // Profile information
    private String objectives;
    private BigDecimal salary;
    
    @Enumerated(EnumType.STRING)
    private FinanceDivisionType financeDivisionType;
    
    // Percentages for each category (only used when CUSTOM)
    private Integer billsPercentage;
    private Integer expensesPercentage;
    private Integer investmentsPercentage;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
