package com.ratriz.charactersheetdnd.domain.dto;

import com.ratriz.charactersheetdnd.domain.entity.Subclass;
import com.ratriz.charactersheetdnd.domain.entity.User;

import java.time.LocalDateTime;

public record UserDTO(Long key, String email, String login, String name, Character gender, boolean inactive,
                      LocalDateTime createdAt, LocalDateTime lastUpdate)
        implements IDTO<User> {

    public UserDTO(User entity) {
        this(entity.getKey(), entity.getEmail(), entity.getLogin(), entity.getName(), entity.getGender(), entity.isInactive(), entity.getCreatedAt(), entity.getLastUpdate());
    }

    @Override
    public User toEntity() {
        return new User(this);
    }
}


