package api.voli.api.controller;

import api.voli.api.domain.entities.auth.DadosAutenticacao;
import api.voli.api.domain.entities.auth.DadosTokenJWT;
import api.voli.api.domain.entities.user.User;
import api.voli.api.infra.security.TokenService;
import api.voli.api.repository.UserRepository;
import api.voli.api.service.AutenticacaoService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserRepository repo;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @PostMapping
    public ResponseEntity login(@RequestBody @Valid DadosAutenticacao dados) {
        var authToken = new UsernamePasswordAuthenticationToken(dados.login(), dados.senha());
        var auth = manager.authenticate(authToken);

        var tokenJWT = tokenService.gerarToken((User) auth.getPrincipal());

        return ResponseEntity.ok(new DadosTokenJWT(tokenJWT));
    }

    @PostMapping("/register")
    @Transactional
    public ResponseEntity<?> register(@RequestBody @Valid DadosAutenticacao dados) {
        if (repo.existsByLogin(dados.login())) {
            return ResponseEntity.badRequest().body("Usuario ja existe");
        }

        String hashedPassword = passwordEncoder.encode(dados.senha());

        User newUser = new User(null, dados.login(), hashedPassword);
        repo.save(newUser);

        return ResponseEntity.ok("Usuario registrado com sucesso");
    }
}
