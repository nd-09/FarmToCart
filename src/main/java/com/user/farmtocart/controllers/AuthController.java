package com.user.farmtocart.controllers;

import com.user.farmtocart.dtos.UserDTO;
import com.user.farmtocart.jwt.JwtUtilService;
import com.user.farmtocart.models.AuthenticationRequest;
import com.user.farmtocart.models.AuthenticationResponse;
import com.user.farmtocart.services.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
public class AuthController {
    private final AuthService authService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtilService jwtUtilService;
    private final PasswordEncoder passwordEncoder;
    public AuthController(AuthService authService, AuthenticationManager authenticationManager, JwtUtilService jwtUtilService,PasswordEncoder passwordEncoder) {
        this.authService = authService;
        this.authenticationManager = authenticationManager;
        this.jwtUtilService = jwtUtilService;
        this.passwordEncoder = passwordEncoder;
    }
    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest authenticationRequest) throws BadCredentialsException {
        try {
//            authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(),
//                            authenticationRequest.getPassword()));
            UserDetails userDetails = authService.loadUserByUsername(authenticationRequest.getEmail());
            boolean matches = passwordEncoder.matches(authenticationRequest.getPassword(), userDetails.getPassword());
       }catch (BadCredentialsException e) {
            throw new BadCredentialsException("Invalid username or password.");
       }
       final UserDetails userDetails = authService.loadUserByUsername(authenticationRequest.getEmail());
       final String jwt= jwtUtilService.generateToken(userDetails);
       return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }

    @PostMapping("/signup")
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDto) {
        ResponseEntity<UserDTO> response = authService.createUser(userDto);
        if(response.getBody()==null){
            throw new BadCredentialsException("Not a valid user.");
        }
        return response ;
    }

}
