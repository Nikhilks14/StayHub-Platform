package com.stayHub.stayHub.controller;

import com.stayHub.stayHub.dto.LoginDto;
import com.stayHub.stayHub.dto.LoginResponseDto;
import com.stayHub.stayHub.dto.SignUpRequestDto;
import com.stayHub.stayHub.dto.UserDto;
import com.stayHub.stayHub.service.AuthService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<UserDto> signUp(@RequestBody SignUpRequestDto  signUpRequestDto){
        return new ResponseEntity<>(authService.signUp(signUpRequestDto), HttpStatus.CREATED);
    }

    @PostMapping("login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginDto  loginDto ,
                                                  HttpServletRequest request ,
                                                  HttpServletResponse response){

        String[] tokens = authService.login(loginDto);
        Cookie cookies = new Cookie("RefreshToken", tokens[1]);
        cookies.setHttpOnly(true);

        response.addCookie(cookies);
        return ResponseEntity.ok(new LoginResponseDto(tokens[0]));
    }

}
