package com.example.clarityCash.auth.application.service;

import com.example.clarityCash.auth.application.dto.UserInfoRequest;
import com.example.clarityCash.auth.application.dto.UserInfoResponse;
import com.example.clarityCash.auth.domain.entity.User;
import com.example.clarityCash.auth.infrastructure.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserInfoResponse getUserInfo(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return mapToResponse(user);
    }

    public List<UserInfoResponse> getAllUsers() {
        return userRepository.findAll().stream()
                .map(this::mapToResponse)
                .toList();
    }

    public void deleteUser(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        userRepository.delete(user);
    }

    public UserInfoResponse updateUser(String email, UserInfoRequest request) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        
        updateUserFromRequest(user, request);
        user.setUpdatedAt(LocalDateTime.now());
        
        User savedUser = userRepository.save(user);
        return mapToResponse(savedUser);
    }

    private void updateUserFromRequest(User user, UserInfoRequest request) {
        if (request.name() != null) {
            user.setName(request.name());
        }
        if (request.objectives() != null) {
            user.setObjectives(request.objectives());
        }
        if (request.salary() != null) {
            user.setSalary(request.salary());
        }
        if (request.financeDivisionType() != null) {
            user.setFinanceDivisionType(request.financeDivisionType());
            setPercentagesByType(user, request);
        }
    }

    private void setPercentagesByType(User user, UserInfoRequest request) {
        switch (user.getFinanceDivisionType()) {
            case RULE_50_30_20 -> {
                user.setBillsPercentage(50);
                user.setExpensesPercentage(30);
                user.setInvestmentsPercentage(20);
            }
            case RULE_50_20_30 -> {
                user.setBillsPercentage(50);
                user.setExpensesPercentage(20);
                user.setInvestmentsPercentage(30);
            }
            case RULE_40_30_30 -> {
                user.setBillsPercentage(40);
                user.setExpensesPercentage(30);
                user.setInvestmentsPercentage(30);
            }
            case CUSTOM -> {
                user.setBillsPercentage(request.billsPercentage());
                user.setExpensesPercentage(request.expensesPercentage());
                user.setInvestmentsPercentage(request.investmentsPercentage());
            }
        }
    }

    private UserInfoResponse mapToResponse(User user) {
        return new UserInfoResponse(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getObjectives(),
                user.getSalary(),
                user.getFinanceDivisionType(),
                user.getBillsPercentage(),
                user.getExpensesPercentage(),
                user.getInvestmentsPercentage()
        );
    }
}
