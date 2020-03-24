package com.jp.api.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.jp.api.models.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.modelmapper.ModelMapper;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto {
    private String login;
    private String name;
    private String email;

    private String token;

    public static UserDto create(User user) {
        ModelMapper modelMapper = new ModelMapper();
        UserDto dto = modelMapper.map(user, UserDto.class);
        return dto;
    }

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