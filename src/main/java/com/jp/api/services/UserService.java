package com.jp.api.services;


import com.jp.api.dto.UserDto;
import com.jp.api.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository rep;

    public List<UserDto> getUsers() {
        return rep.findAll().stream().map(UserDto::create).collect(Collectors.toList());
    }
}