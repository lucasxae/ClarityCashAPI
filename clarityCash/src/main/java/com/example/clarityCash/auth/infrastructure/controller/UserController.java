package com.example.clarityCash.auth.infrastructure.controller;

import com.example.clarityCash.auth.application.dto.UserInfoRequest;
import com.example.clarityCash.auth.application.dto.UserInfoResponse;
import com.example.clarityCash.auth.application.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@Tag(name = "User Profile", description = "Gerenciamento de perfil do usuário")
@SecurityRequirement(name = "Bearer Authentication")
public class UserController {

    private final UserService userService;

    @GetMapping("/profile")
    @Operation(summary = "Buscar perfil do usuário", description = "Retorna as informações do perfil do usuário logado")
    @ApiResponse(responseCode = "200", description = "Perfil encontrado com sucesso")
    public ResponseEntity<UserInfoResponse> getProfile(Authentication authentication) {
        UserInfoResponse profile = userService.getUserInfo(authentication.getName());
        return ResponseEntity.ok(profile);
    }

    @PutMapping("/profile")
    @Operation(summary = "Atualizar perfil do usuário", description = "Atualiza as informações do perfil do usuário logado")
    @ApiResponse(responseCode = "200", description = "Perfil atualizado com sucesso")
    public ResponseEntity<UserInfoResponse> updateProfile(
            @RequestBody UserInfoRequest request,
            Authentication authentication) {
        UserInfoResponse updatedProfile = userService.updateUser(authentication.getName(), request);
        return ResponseEntity.ok(updatedProfile);
    }

    @GetMapping
    @Operation(summary = "Listar todos os usuários", description = "Retorna lista de todos os usuários cadastrados")
    @ApiResponse(responseCode = "200", description = "Lista de usuários retornada com sucesso")
    public ResponseEntity<List<UserInfoResponse>> getAllUsers() {
        List<UserInfoResponse> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @DeleteMapping
    @Operation(summary = "Deletar perfil do usuário", description = "Remove o perfil do usuário logado")
    @ApiResponse(responseCode = "204", description = "Perfil deletado com sucesso")
    public ResponseEntity<Void> deleteProfile(Authentication authentication) {
        userService.deleteUser(authentication.getName());
        return ResponseEntity.noContent().build();
    }
}
