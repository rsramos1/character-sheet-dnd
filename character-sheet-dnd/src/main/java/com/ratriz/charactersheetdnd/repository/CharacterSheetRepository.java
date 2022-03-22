package com.ratriz.charactersheetdnd.repository;

import com.ratriz.charactersheetdnd.domain.dto.CharacterSheetDTO;
import com.ratriz.charactersheetdnd.domain.dto.UserDTO;
import com.ratriz.charactersheetdnd.domain.entity.CharacterSheet;
import com.ratriz.charactersheetdnd.domain.entity.User;
import com.ratriz.charactersheetdnd.infrastructure.ConstantPages;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CharacterSheetRepository extends JpaRepository<CharacterSheet, Long> {

    public Page<CharacterSheet> findByUser(User user, Pageable pageRequest);

    default Page<CharacterSheetDTO> findByUserDto(User user) {
        return findByUser(user, ConstantPages.PAGE_REQUEST_ORDER_NAME).map(obj -> obj.toDTO());
    }

    default Page<CharacterSheetDTO> findByUserDto(UserDTO user) {
        return findByUserDto(user.toEntity());
    }

}
