package com.ratriz.charactersheetdnd.domain.entity;

import com.ratriz.charactersheetdnd.domain.dto.UserDTO;
import com.ratriz.charactersheetdnd.domain.dto.UserSignUpDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Entity
@Table
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class User extends AbstractEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long key;

    @NotBlank
    @Column(unique = true)
    private String email;

    @NotBlank
    @Column(unique = true)
    private String login;

    @NotBlank
    private String password;

    @NotBlank
    private String name;

    @NotEmpty
    private Character gender;

    @ColumnDefault(CURRENT_TIMESTAMP)
    private LocalDateTime createdAt;

    public User(UserDTO dto){
        this.key = dto.key();
        this.email = dto.email();
        this.login = dto.login();
        this.name = dto.name();
        this.gender = dto.gender();
    }

    public User(UserSignUpDTO dto){
        this.email = dto.email();
        this.login = dto.login();
        this.name = dto.name();
        this.gender = dto.gender();
        this.password = getPassword();
    }

    @Override
    public Long getId() {
        return key;
    }

    @Override
    public void setId(Long id) {
        this.key = id;
    }

    @Override
    @SuppressWarnings("unchecked")
    public UserDTO toDTO() {
        return null;
    }
}
