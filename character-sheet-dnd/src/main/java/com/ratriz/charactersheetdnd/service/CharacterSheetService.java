package com.ratriz.charactersheetdnd.service;

import com.google.gson.JsonObject;
import com.ratriz.charactersheetdnd.domain.dto.*;
import com.ratriz.charactersheetdnd.domain.entity.CharacterSheet;
import com.ratriz.charactersheetdnd.exception.ObjectNotFoundException;
import com.ratriz.charactersheetdnd.infrastructure.ConstantFilter;
import com.ratriz.charactersheetdnd.repository.CharacterSheetRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Stream;

@Service
public class CharacterSheetService {

    @Autowired
    private CharacterSheetRepository repository;

    @Autowired
    private BackgroundService backgroundService;

    @Autowired
    private BackgroundTypeService backgroundTypeService;

    @Autowired
    private SubraceService subraceService;

    @Autowired
    private SubclassService subclassService;

    @Autowired
    private AlignmentService alignmentService;

    @Autowired
    private RaceService raceService;

    @Autowired
    private CharacterClassService characterClassService;

    @Autowired
    private CharacterNameService characterNameService;

    @Autowired
    private SkinService skinService;

    @Autowired
    private HairService hairService;

    @Autowired
    private EyesService eyesService;

    @Autowired
    private PersonalityTraitsService personalityTraitsService;

    @Autowired
    private IdealsService idealsService;

    @Autowired
    private BondsService bondsService;

    @Autowired
    private FlawsService flawsService;

    public Page<CharacterSheet> findAll(Pageable pageRequest) {
        return repository.findAll(pageRequest);
    }

    public Page<CharacterSheetDTO> findAllDto(Pageable pageRequest) {
        return findAll(pageRequest).map(obj -> obj.toDTO());
    }

    public CharacterSheet findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new ObjectNotFoundException());
    }

    public CharacterSheetDTO findByIdDto(Long id) {
        return findById(id).toDTO();
    }

    public CharacterSheet save(CharacterSheet entity) {
        return repository.save(entity);
    }

    public CharacterSheetDTO insert(CharacterSheetDTO dto) {
        return save(dto.toEntity()).toDTO();
    }

    // TODO ajustar carregamento e mapeamento
    public CharacterSheetDTO update(Long id, CharacterSheetDTO dto) {
        if (repository.existsById(id)) {
            throw new ObjectNotFoundException();
        }
        return save(Optional.of(dto.toEntity()).map(entity -> {
            entity.setKey(id);
            return entity;
        }).get()).toDTO();
    }

    public CharacterSheet delete(Long id) {
        CharacterSheet characterSheet = findById(id);
        repository.delete(characterSheet);
        return characterSheet;
    }

    // TODO buscar usuario logado
    public CharacterSheetDTO generate(JsonObject json) {
        CharacterSheet sheet = loadSheet(json);
        randomize(sheet);
        return sheet.toDTO();
    }

    private CharacterSheet loadSheet(JsonObject json) {
        CharacterSheet sheet = new CharacterSheet();
        Optional.ofNullable(json.get("name")).ifPresent(
                obj -> sheet.setName(obj.getAsString()));
        Optional.ofNullable(json.get("gender")).ifPresent(
                obj -> {
                    String gender = StringUtils.trimToNull(obj.getAsString());
                    if (!Objects.isNull(gender) && StringUtils.equalsAnyIgnoreCase(gender, "M", "F")) {
                        sheet.setGender(gender.toUpperCase().charAt(0));
                    }
                });
        Optional.ofNullable(json.get("background")).ifPresent(
                obj -> Optional.ofNullable(obj.getAsLong()).ifPresent(
                        key -> sheet.setBackground(backgroundService.findById(key))));
        Optional.ofNullable(json.get("backgroundType")).ifPresent(
                obj -> Optional.ofNullable(obj.getAsLong()).ifPresent(
                        key -> sheet.setBackgroundType(backgroundTypeService.findById(key))));
        Optional.ofNullable(json.get("subrace")).ifPresent(
                obj -> Optional.ofNullable(obj.getAsLong()).ifPresent(
                        key -> sheet.setSubrace(subraceService.findById(key))));
        Optional.ofNullable(json.get("subclass")).ifPresent(
                obj -> Optional.ofNullable(obj.getAsLong()).ifPresent(
                        key -> sheet.setSubclass(subclassService.findById(key))));
        Optional.ofNullable(json.get("alignment")).ifPresent(
                obj -> Optional.ofNullable(obj.getAsLong()).ifPresent(
                        key -> sheet.setAlignment(alignmentService.findById(key))));
        Optional.ofNullable(json.get("skin")).ifPresent(
                obj -> sheet.setSkin(obj.getAsString()));
        Optional.ofNullable(json.get("hair")).ifPresent(
                obj -> sheet.setHair(obj.getAsString()));
        Optional.ofNullable(json.get("eyes")).ifPresent(
                obj -> sheet.setEyes(obj.getAsString()));
        Optional.ofNullable(json.get("personalityTraits")).ifPresent(
                obj -> sheet.setPersonalityTraits(obj.getAsString()));
        Optional.ofNullable(json.get("ideals")).ifPresent(
                obj -> sheet.setIdeals(obj.getAsString()));
        Optional.ofNullable(json.get("bonds")).ifPresent(
                obj -> sheet.setBonds(obj.getAsString()));
        Optional.ofNullable(json.get("flaws")).ifPresent(
                obj -> sheet.setFlaws(obj.getAsString()));
        Optional.ofNullable(json.get("age")).ifPresent(
                obj -> sheet.setAge(obj.getAsInt()));
        Optional.ofNullable(json.get("height")).ifPresent(
                obj -> sheet.setHeight(obj.getAsInt()));
        Optional.ofNullable(json.get("weight")).ifPresent(
                obj -> sheet.setWeight(obj.getAsFloat()));
        Optional.ofNullable(json.get("race")).ifPresent(
                obj -> Optional.ofNullable(obj.getAsLong()).ifPresent(
                        key -> sheet.setRace(raceService.findById(key))));
        Optional.ofNullable(json.get("characterClass")).ifPresent(
                obj -> Optional.ofNullable(obj.getAsLong()).ifPresent(
                        key -> sheet.setCharacterClass(characterClassService.findById(key))));
        return sheet;
    }

    private void randomize(CharacterSheet sheet) {
        Random random = new Random();
        Optional.ofNullable(sheet.getGender()).ifPresentOrElse(null, () ->
                sheet.setGender(random.nextInt(2) == 0 ? 'M' : 'F'));
        Optional.ofNullable(sheet.getAlignment()).ifPresentOrElse(null, () ->
                sheet.setAlignment(alignmentService.findOneRandom(
                                Map.of(ConstantFilter.INACTIVE_EQUALS, Boolean.FALSE.toString()))
                        .toEntity()));
        Optional.ofNullable(sheet.getBackground()).ifPresentOrElse(null, () ->
                sheet.setBackground(backgroundService.findOneRandom(
                                Map.of(ConstantFilter.INACTIVE_EQUALS, Boolean.FALSE.toString(),
                                        ConstantFilter.ALIGNMENT_ID_EQUALS, sheet.getAlignment().getKey().toString()))
                        .toEntity()));
        Optional.ofNullable(sheet.getBackground().getBackgroundType()).ifPresent(obj -> {
            Optional.ofNullable(sheet.getBackgroundType()).ifPresentOrElse(null, () ->
                    sheet.setBackgroundType(backgroundTypeService.findOneRandom(
                                    Map.of(ConstantFilter.INACTIVE_EQUALS, Boolean.FALSE.toString(),
                                            ConstantFilter.BACKGROUND_ID_EQUALS, sheet.getBackground().getKey().toString()))
                            .toEntity()));
        });
        Optional.ofNullable(sheet.getRace()).ifPresentOrElse(null, () ->
                sheet.setRace(raceService.findOneRandom(
                                Map.of(ConstantFilter.INACTIVE_EQUALS, Boolean.FALSE.toString()))
                        .toEntity()));
        Optional.ofNullable(sheet.getCharacterClass()).ifPresentOrElse(null, () ->
                sheet.setCharacterClass(characterClassService.findOneRandom(
                                Map.of(ConstantFilter.INACTIVE_EQUALS, Boolean.FALSE.toString()))
                        .toEntity()));
        Optional.ofNullable(sheet.getSubrace()).ifPresentOrElse(null, () ->
                sheet.setSubrace(subraceService.findOneRandom(
                                Map.of(ConstantFilter.INACTIVE_EQUALS, Boolean.FALSE.toString(),
                                        ConstantFilter.RACE_ID_EQUALS, sheet.getRace().getKey().toString()))
                        .toEntity()));
        Optional.ofNullable(sheet.getSubrace()).ifPresentOrElse(null, () ->
                sheet.setSubclass(subclassService.findOneRandom(
                                Map.of(ConstantFilter.INACTIVE_EQUALS, Boolean.FALSE.toString(),
                                        ConstantFilter.CHARACTER_CLASS_ID_EQUALS, sheet.getCharacterClass().getKey().toString()))
                        .toEntity()));
        Optional.ofNullable(sheet.getName()).ifPresentOrElse(null, () -> {
            CharacterNameDTO characterName = characterNameService.findOneRandom(
                    Map.of(ConstantFilter.INACTIVE_EQUALS, Boolean.FALSE.toString(),
                            ConstantFilter.GENDER_EQUALS, sheet.getGender().toString(),
                            ConstantFilter.SUBRACE_ID_EQUALS, sheet.getSubrace().getKey().toString()));
            sheet.setName(characterName.name());
        });
        Optional.ofNullable(sheet.getSkin()).ifPresentOrElse(null, () -> {
            SkinDTO skin = skinService.findOneRandom(
                    Map.of(ConstantFilter.INACTIVE_EQUALS, Boolean.FALSE.toString(),
                            ConstantFilter.SUBRACE_ID_EQUALS, sheet.getSubrace().getKey().toString()));
            sheet.setSkin(skin.name());
        });
        Optional.ofNullable(sheet.getHair()).ifPresentOrElse(null, () -> {
            HairDTO hair = hairService.findOneRandom(
                    Map.of(ConstantFilter.INACTIVE_EQUALS, Boolean.FALSE.toString(),
                            ConstantFilter.SUBRACE_ID_EQUALS, sheet.getSubrace().getKey().toString()));
            sheet.setHair(hair.name());
        });
        Optional.ofNullable(sheet.getEyes()).ifPresentOrElse(null, () -> {
            EyesDTO eyes = eyesService.findOneRandom(
                    Map.of(ConstantFilter.INACTIVE_EQUALS, Boolean.FALSE.toString(),
                            ConstantFilter.SUBRACE_ID_EQUALS, sheet.getSubrace().getKey().toString()));
            sheet.setEyes(eyes.name());
        });
        Optional.ofNullable(sheet.getAge()).ifPresentOrElse(null, () -> {
            Integer age = Stream.of(sheet.getRace()).map(race ->
                    random.nextInt(race.getMaxAge() - race.getMinAge() + 1) + race.getMinAge()
            ).findFirst().get();
            sheet.setAge(age);
        });
        Optional.ofNullable(sheet.getHeight()).ifPresentOrElse(null, () -> {
            Integer height = Stream.of(sheet.getRace()).map(race ->
                    random.nextInt(race.getMaxHeight() - race.getMinHeight() + 1) + race.getMinHeight()
            ).findFirst().get();
            sheet.setHeight(height);
        });
        Optional.ofNullable(sheet.getWeight()).ifPresentOrElse(null, () -> {
            Integer weight = Stream.of(sheet.getRace()).map(race ->
                    random.nextInt((int) (race.getMaxWeight() * 100) - (int) (race.getMinWeight() * 100) + 1) + (int) (race.getMinWeight() * 100)
            ).findFirst().get();
            sheet.setWeight((float) weight / 100.0f);
        });
        Optional.ofNullable(sheet.getPersonalityTraits()).ifPresentOrElse(null, () -> {
            PersonalityTraitsDTO personalityTraits = personalityTraitsService.findOneRandom(
                    Map.of(ConstantFilter.INACTIVE_EQUALS, Boolean.FALSE.toString(),
                            ConstantFilter.BACKGROUND_ID_EQUALS, sheet.getBackground().getKey().toString(),
                            ConstantFilter.ALIGNMENT_ID_EQUALS, sheet.getAlignment().getKey().toString()));
            sheet.setPersonalityTraits(personalityTraits.name());
        });
        Optional.ofNullable(sheet.getIdeals()).ifPresentOrElse(null, () -> {
            IdealsDTO ideals = idealsService.findOneRandom(
                    Map.of(ConstantFilter.INACTIVE_EQUALS, Boolean.FALSE.toString(),
                            ConstantFilter.BACKGROUND_ID_EQUALS, sheet.getBackground().getKey().toString(),
                            ConstantFilter.ALIGNMENT_ID_EQUALS, sheet.getAlignment().getKey().toString()));
            sheet.setIdeals(ideals.name());
        });
        Optional.ofNullable(sheet.getBonds()).ifPresentOrElse(null, () -> {
            BondsDTO bonds = bondsService.findOneRandom(
                    Map.of(ConstantFilter.INACTIVE_EQUALS, Boolean.FALSE.toString(),
                            ConstantFilter.BACKGROUND_ID_EQUALS, sheet.getBackground().getKey().toString(),
                            ConstantFilter.ALIGNMENT_ID_EQUALS, sheet.getAlignment().getKey().toString()));
            sheet.setBonds(bonds.name());
        });
        Optional.ofNullable(sheet.getFlaws()).ifPresentOrElse(null, () -> {
            FlawsDTO flaws = flawsService.findOneRandom(
                    Map.of(ConstantFilter.INACTIVE_EQUALS, Boolean.FALSE.toString(),
                            ConstantFilter.BACKGROUND_ID_EQUALS, sheet.getBackground().getKey().toString(),
                            ConstantFilter.ALIGNMENT_ID_EQUALS, sheet.getAlignment().getKey().toString()));
            sheet.setFlaws(flaws.name());
        });
    }

}
