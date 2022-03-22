package com.ratriz.charactersheetdnd.domain.dto;

import com.ratriz.charactersheetdnd.domain.entity.User;

public record UserSignUpDTO(String email, String login, String name, Character gender, String password,
                            String confirmPassword)
        implements IDTO<User> {

    @Override
    public User toEntity() {
        return new User(this);
    }
}
