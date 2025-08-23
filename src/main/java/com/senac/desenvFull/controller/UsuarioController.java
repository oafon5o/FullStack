package com.senac.desenvFull.controller;

import com.senac.desenvFull.model.Usuario;
import com.senac.desenvFull.repositoy.UsuarioRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
@Tag(name = "Controlador de Usuários", description = "Camada responsável por controlar os registros de usuário")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> consultaPorId(@PathVariable Long id) {
        var usuario = usuarioRepository.findById(id).orElse(null);
        if (usuario == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(usuario);
    }

    @GetMapping
    @Operation(summary = "usuarios", description = "Método responsável de calcular os custos da folha de pagamento e após faz os lançamentos contábeis na tabela x e blablabla!")
    public ResponseEntity<?> consultarTodos(){
        return ResponseEntity.ok(usuarioRepository.findAll());
    }

    @PostMapping
    @Operation(summary = "Criar Usuários", description = "Método responsável em criar usuários")
    public ResponseEntity<?> criarUsuarios(@RequestBody Usuario usuario) {

        try {

            var usuarioResponse = usuarioRepository.save(usuario);

            return ResponseEntity.ok(usuarioResponse);

        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

}
