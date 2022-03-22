package com.ratriz.charactersheetdnd.domain.entity;

import com.ratriz.charactersheetdnd.domain.dto.CharacterSheetDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class CharacterSheet {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long key;

    @ManyToOne(optional = false)
    private User user;

    private String name;

    private Character gender;

    @ManyToOne(optional = false)
    private Background background;

    @ManyToOne
    private BackgroundType backgroundType;

    @ManyToOne(optional = false)
    private Subrace subrace;

    @ManyToOne(optional = false)
    private Subclass subclass;

    @ManyToOne(optional = false)
    private Alignment alignment;

    private String skin;

    private String hair;

    private String eyes;

    private String personalityTraits;

    private String ideals;

    private String bonds;

    private String flaws;

    private Integer age;

    private Integer height;

    private Float weight;

    @ColumnDefault(AbstractEntity.CURRENT_TIMESTAMP)
    private LocalDateTime createAt;

    @ColumnDefault(AbstractEntity.CURRENT_TIMESTAMP)
    private LocalDateTime lastUpdate;

    @Transient
    private Race race;

    @Transient
    private CharacterClass characterClass;

    public CharacterSheet(CharacterSheetDTO dto) {
        this.key = dto.key();
        this.user = dto.user().toEntity();
        this.name = dto.name();
        this.background = dto.background();
        this.backgroundType = dto.backgroundType();
        this.subrace = dto.subrace();
        this.subclass = dto.subclass();
        this.alignment = dto.alignment();
        this.skin = dto.skin();
        this.hair = dto.hair();
        this.eyes = dto.eyes();
        this.personalityTraits = dto.personalityTraits();
        this.ideals = dto.ideals();
        this.bonds = dto.bonds();
        this.flaws = dto.flaws();
        this.age = dto.age();
        this.height = dto.height();
        this.weight = dto.weight();
    }

    public CharacterSheetDTO toDTO() {
        return new CharacterSheetDTO(this);
    }

}
