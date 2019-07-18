package com.stages.stage1.service;

import com.stages.stage1.config.securityConfig.SecurityConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PasswordEncoderService {

    private final SecurityConfig encoder;

    public String encode(String string){
        BCryptPasswordEncoder passwordEncoder = encoder.passwordEncoder();
        return passwordEncoder.encode(string);
    }

    public boolean isPassword(String password, String savedPassword){
        BCryptPasswordEncoder passwordEncoder = encoder.passwordEncoder();
        return passwordEncoder.matches(password, savedPassword);
    }
}
