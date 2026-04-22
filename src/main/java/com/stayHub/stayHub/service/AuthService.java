package com.stayHub.stayHub.service;

import com.stayHub.stayHub.dto.LoginDto;
import com.stayHub.stayHub.dto.SignUpRequestDto;
import com.stayHub.stayHub.dto.UserDto;
import com.stayHub.stayHub.entity.User;
import com.stayHub.stayHub.entity.enums.Role;
import com.stayHub.stayHub.exception.ResoureceNotFoundException;
import com.stayHub.stayHub.repositry.UserRepository;
import com.stayHub.stayHub.security.JWTService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JWTService  jwtService;

    public UserDto signUp(SignUpRequestDto signUpRequestDto){

        User user = userRepository.findByEmail(signUpRequestDto.getEmail()).orElse(null);

        if(user != null){
            throw new RuntimeException("User with email already exists");
        }

        User newUser = modelMapper.map(signUpRequestDto, User.class);
        newUser.setRoles(Set.of(Role.GUEST));
        newUser.setPassword(passwordEncoder.encode(signUpRequestDto.getPassword()));
        newUser = userRepository.save(newUser);

        return modelMapper.map(newUser,UserDto.class);
    }

        public String[] login(LoginDto  loginDto){
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    loginDto.getEmail(), loginDto.getPassword()
            ));

            User user = (User) authentication.getPrincipal();

            String[] arr = new String[2];

            arr[0] = jwtService.generateAccessToken(user);
            arr[1] = jwtService.generateRefreshToken(user);

            return arr;

        }


        public String refreshToken(String refreshToken){
            Long id = jwtService.getUserIdFromToken(refreshToken);

            User user = userRepository.findById(id).orElseThrow(()-> new ResoureceNotFoundException("User not found with id : " + id));
            return  jwtService.generateAccessToken(user);
        }

}
