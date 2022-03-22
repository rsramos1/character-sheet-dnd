package com.ratriz.charactersheetdnd.service;

import com.google.gson.JsonObject;
import com.ratriz.charactersheetdnd.domain.dto.UserDTO;
import com.ratriz.charactersheetdnd.domain.dto.UserSignUpDTO;
import com.ratriz.charactersheetdnd.domain.entity.User;
import com.ratriz.charactersheetdnd.exception.ObjectNotFoundException;
import com.ratriz.charactersheetdnd.repository.UserRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

// TODO criptografar senha
@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public User findUser(String param) {
        if (!StringUtils.isBlank(param)) {
            if (param.contains("@")) {
                return repository.findByEmail(param);
            }
            return repository.findByLogin(param);
        }
        throw new ObjectNotFoundException();
    }

    // TODO validar se login ou email ja existe
    public UserDTO signUp(UserSignUpDTO dto) {
        if (!dto.password().equals(dto.confirmPassword())) {
            throw new IllegalArgumentException("senha invalida"); // TODO criar exception de senha invalida
        }
        User user = new User(dto);
        return repository.save(user).toDTO();
    }

    public UserDTO login(JsonObject json) {
        String login = json.get("login").getAsString();
        String password = json.get("password").getAsString();
        return Optional.ofNullable(findUser(login)).stream()
                .map(user -> {
                    if (user.getPassword().equals(password)) {
                        return user.toDTO();
                    }
                    throw new IllegalArgumentException("senha invalida"); // TODO criar exception de senha invalida
                }).findFirst().get();
    }

    public void changePassword(Long id, JsonObject json) {
        User user = repository.findById(id).orElseThrow(() -> new ObjectNotFoundException());
        String oldPassword = json.get("oldPassword").getAsString();
        String newPassword = json.get("newPassword").getAsString();
        String confirmNewPassword = json.get("confirmNewPassword").getAsString();
        if (!user.getPassword().equals(oldPassword)) {
            throw new IllegalArgumentException("senha invalida"); // TODO criar excecao para senha invalida
        } else if (!newPassword.equals(confirmNewPassword)) {
            throw new IllegalArgumentException("senha diferente"); // TODO criar excecao para senha diferente
        }
        user.setPassword(newPassword);
        repository.save(user);
    }

}
