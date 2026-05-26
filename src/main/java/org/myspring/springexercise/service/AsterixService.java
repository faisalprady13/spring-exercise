package org.myspring.springexercise.service;

import lombok.RequiredArgsConstructor;
import org.myspring.springexercise.dto.CharacterDto;
import org.myspring.springexercise.model.AsterixCharacter;
import org.myspring.springexercise.repository.CharacterRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AsterixService {
    private final CharacterRepository characterRepository;
    private final IdService idService;

    public List<AsterixCharacter> getCharacter(AsterixCharacter filter) {
        return characterRepository.findAll().stream().filter(asterixCharacter ->
                (filter.name() == null || filter.name().isBlank()
                        || asterixCharacter.name().equalsIgnoreCase(filter.name()))

                        && (filter.profession() == null || filter.profession().isBlank()
                        || asterixCharacter.profession().equalsIgnoreCase(filter.profession()))

                        && (filter.age() == null
                        || asterixCharacter.age().equals(filter.age()))
        ).toList();
    }

    public List<AsterixCharacter> getCharacter(@PathVariable String id) {
        return characterRepository.findCharactersById(id);
    }

    public void saveCharacter(CharacterDto characterDto) {
        AsterixCharacter asterixCharacter = AsterixCharacter.builder()
                .id(idService.randomId())
                .name(characterDto.name())
                .age(characterDto.age())
                .profession(characterDto.profession())
                .build();

        characterRepository.save(asterixCharacter);
    }

    public void saveCharacters(List<AsterixCharacter> asterixCharacters) {
        characterRepository.saveAll(asterixCharacters);
    }

    public void updateCharacters(@RequestBody AsterixCharacter asterixCharacter) {
        characterRepository.findById(asterixCharacter.id()).orElseThrow();
        characterRepository.save(asterixCharacter);
    }

    public void deleteCharacter(@PathVariable String id) {
        characterRepository.findById(id).orElseThrow();
        characterRepository.deleteById(id);
    }
}
