package org.myspring.springexercise.controller;

import lombok.RequiredArgsConstructor;
import org.myspring.springexercise.dto.CharacterDto;
import org.myspring.springexercise.model.Character;
import org.myspring.springexercise.repository.CharacterRepository;
import org.myspring.springexercise.service.AsterixService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/asterix/character")
@RequiredArgsConstructor
public class AsterixController {
    private final AsterixService asterixService;

    @GetMapping("")
    public List<Character> getCharacter(@ModelAttribute Character filter) {
        return asterixService.getCharacter(filter);
    }

    @GetMapping("/{id}")
    public List<Character> getCharacter(@PathVariable String id) {
        return asterixService.getCharacter(id);
    }

    @PostMapping("")
    public void saveCharacter(@RequestBody CharacterDto characterDto) {
        asterixService.saveCharacter(characterDto);
    }

    @PostMapping("/batch")
    public void saveCharacters(@RequestBody List<Character> characters) {
        asterixService.saveCharacters(characters);
    }

    @PutMapping("")
    public void updateCharacters(@RequestBody Character character) {
        asterixService.updateCharacters(character);
    }

    @DeleteMapping("/{id}")
    public void deleteCharacter(@PathVariable String id) {
        asterixService.deleteCharacter(id);
    }
}
