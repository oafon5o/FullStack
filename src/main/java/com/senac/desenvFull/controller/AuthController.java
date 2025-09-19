package com.senac.desenvFull.controller;

import com.senac.desenvFull.dto.LoginRequestDto;
import com.senac.desenvFull.dto.LoginResponseDto;
import com.senac.desenvFull.services.TokenService;
import com.senac.desenvFull.services.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@Tag(name = "Controlador de autenticacao", description = "Controlador responsavel pela autenticação")
public class AuthController {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/login")
    @Operation(summary = "Login", description = "Método responsável por login de usuário")
    public ResponseEntity<?> login(@RequestBody LoginRequestDto request){

        if (!usuarioService.validarSenha(request)){
            return ResponseEntity.badRequest().body("Usuário e/ou senha inválido!");
        }

        var token = tokenService.gerarToken(request);

        return ResponseEntity.ok(new LoginResponseDto(token));
    }
}
