package org.myspring.springexercise.controller;

import lombok.RequiredArgsConstructor;
import org.myspring.springexercise.model.Character;
import org.myspring.springexercise.repository.CharacterRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/asterix/character")
@RequiredArgsConstructor
public class AsterixController {
    private final CharacterRepository characterRepository;

    @GetMapping("")
    public List<Character> getCharacter(@ModelAttribute Character filter) {
        return characterRepository.findAll().stream().filter(character ->

                (filter.name() == null || filter.name().isBlank()
                        || character.name().equalsIgnoreCase(filter.name()))

                        && (filter.profession() == null || filter.profession().isBlank()
                        || character.profession().equalsIgnoreCase(filter.profession()))

                        && (filter.age() == null
                        || character.age().equals(filter.age()))
        ).toList();
    }

    @GetMapping("/{id}")
    public List<Character> getCharacter(@PathVariable String id) {
        return characterRepository.findCharactersById(id);
    }

    @PostMapping("")
    public void saveCharacter(@RequestBody Character character) {
        characterRepository.save(character);
    }

    @PostMapping("/batch")
    public void saveCharacters(@RequestBody List<Character> characters) {
        characterRepository.saveAll(characters);
    }

    @PutMapping("")
    public void updateCharacters(@RequestBody Character character) {
        characterRepository.save(character);
    }

    @DeleteMapping("/{id}")
    public void deleteCharacter(@PathVariable String id) {
        characterRepository.deleteById(id);
    }
}
