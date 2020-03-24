package com.jp.api.dto;

import com.jp.api.models.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.modelmapper.ModelMapper;

@Data
public class UserDto {
    private String login;
    private String name;
    private String email;

    private String token;

    public static UserDto create(User user, String token) {
        ModelMapper modelMapper = new ModelMapper();
        UserDto dto = modelMapper.map(user, UserDto.class);
        dto.token = token;
        return dto;
    }

    public String toJson() throws JsonProcessingException {
        ObjectMapper m = new ObjectMapper();
        return m.writeValueAsString(this);
    }
}