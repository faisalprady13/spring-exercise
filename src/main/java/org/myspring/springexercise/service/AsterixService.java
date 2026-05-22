package org.myspring.springexercise.service;

import lombok.RequiredArgsConstructor;
import org.myspring.springexercise.dto.CharacterDto;
import org.myspring.springexercise.model.Character;
import org.myspring.springexercise.repository.CharacterRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AsterixService {
    private final CharacterRepository characterRepository;
    private final IdService idService;

    public List<Character> getCharacter(Character filter) {
        return characterRepository.findAll().stream().filter(character ->
                (filter.name() == null || filter.name().isBlank()
                        || character.name().equalsIgnoreCase(filter.name()))

                        && (filter.profession() == null || filter.profession().isBlank()
                        || character.profession().equalsIgnoreCase(filter.profession()))

                        && (filter.age() == null
                        || character.age().equals(filter.age()))
        ).toList();
    }

    public List<Character> getCharacter(@PathVariable String id) {
        return characterRepository.findCharactersById(id);
    }

    public void saveCharacter(CharacterDto characterDto) {
        Character character = Character.builder()
                .id(idService.randomId())
                .name(characterDto.name())
                .age(characterDto.age())
                .profession(characterDto.profession())
                .build();

        characterRepository.save(character);
    }

    public void saveCharacters(List<Character> characters) {
        characterRepository.saveAll(characters);
    }

    public void updateCharacters(@RequestBody Character character) {
        characterRepository.findById(character.id()).orElseThrow();
        characterRepository.save(character);
    }

    public void deleteCharacter(@PathVariable String id) {
        characterRepository.findById(id).orElseThrow();
        characterRepository.deleteById(id);
    }
}
