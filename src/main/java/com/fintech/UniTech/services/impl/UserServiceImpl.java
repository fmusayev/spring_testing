package com.fintech.UniTech.services.impl;

import com.fintech.UniTech.domain.User;
import com.fintech.UniTech.dto.LoginDto;
import com.fintech.UniTech.dto.RegisterDto;
import com.fintech.UniTech.exception.InvalidCredentialsException;
import com.fintech.UniTech.exception.PinAlreadyExistException;
import com.fintech.UniTech.repository.UserRepository;
import com.fintech.UniTech.services.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Override
    public RegisterDto register(RegisterDto registerDto) {
        Optional<User> existingUser = userRepository.findByPin(registerDto.getPin());
        if (existingUser.isPresent()) throw new PinAlreadyExistException("Pin already exist !");

        User newUser = modelMapper.map(registerDto, User.class);
        userRepository.save(newUser);

        // TODO: return JWT
        return registerDto;
    }

    @Override
    public LoginDto login(LoginDto loginDto) {
        User user = userRepository.findByPinAndPassword(loginDto.getPin(), loginDto.getPassword())
                .orElseThrow(() -> new InvalidCredentialsException("Pin or password is invalid!"));

        // TODO: return JWT
        return modelMapper.map(user, LoginDto.class);
    }
}
