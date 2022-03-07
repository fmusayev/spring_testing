package com.fintech.UniTech.services;

import com.fintech.UniTech.dto.LoginDto;
import com.fintech.UniTech.dto.RegisterDto;

public interface UserService {

    RegisterDto register(RegisterDto registerDto);

    LoginDto login(LoginDto loginDto);
}
