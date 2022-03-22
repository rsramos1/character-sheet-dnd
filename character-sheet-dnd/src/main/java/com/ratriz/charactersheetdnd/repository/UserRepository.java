package com.ratriz.charactersheetdnd.repository;

import com.ratriz.charactersheetdnd.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	public User findByEmail(String email);

	public User findByLogin(String login);

}
