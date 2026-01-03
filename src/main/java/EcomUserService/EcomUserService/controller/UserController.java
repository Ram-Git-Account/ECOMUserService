package EcomUserService.EcomUserService.controller;

import EcomUserService.EcomUserService.dto.LogoutRequestDto;
import EcomUserService.EcomUserService.dto.SignUpRequestDto;
import EcomUserService.EcomUserService.dto.UserDto;
import EcomUserService.EcomUserService.dto.ValidateTokenRequestDto;
import EcomUserService.EcomUserService.model.SessionStatus;
import EcomUserService.EcomUserService.service.AuthService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final AuthService authService;

    public UserController(AuthService authService) {
        this.authService = authService;
    }


    @PostMapping("/login")
    public ResponseEntity<UserDto> login(@RequestBody SignUpRequestDto loginRequest) {
        return authService.login(loginRequest.getEmail(), loginRequest.getPassword());
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(
            @RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader
    ) {
        authService.logout(authHeader);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/signup")
    public UserDto signUp(@RequestBody SignUpRequestDto signUpRequest) {
        return authService.signUp(signUpRequest.getEmail(), signUpRequest.getPassword());
    }

    @PostMapping("/validate")
    public SessionStatus validateToken(@RequestBody ValidateTokenRequestDto validateRequest) {
        return authService.validate(validateRequest.getToken());
    }
}