package com.ratriz.charactersheetdnd.domain.dto;

import com.ratriz.charactersheetdnd.domain.entity.*;

public record CharacterSheetDTO(Long key, UserDTO user, String name, Background background,
                                BackgroundType backgroundType,
                                Subrace subrace, Subclass subclass, Alignment alignment,
                                String skin, String hair, String eyes, String personalityTraits,
                                String ideals, String bonds, String flaws,
                                Integer age, Integer height, Float weight) {

    public CharacterSheetDTO(CharacterSheet entity) {
        this(entity.getKey(), entity.getUser().toDTO(), entity.getName(), entity.getBackground(), entity.getBackgroundType()
                , entity.getSubrace(), entity.getSubclass(), entity.getAlignment(), entity.getSkin(),
                entity.getHair(), entity.getEyes(), entity.getPersonalityTraits(), entity.getIdeals(),
                entity.getBonds(), entity.getFlaws(), entity.getAge(), entity.getHeight(), entity.getWeight());
    }

    public CharacterSheet toEntity() {
        return new CharacterSheet(this);
    }
}
