package org.myspring.springexercise.controller;

import lombok.RequiredArgsConstructor;
import org.myspring.springexercise.dto.CharacterDto;
import org.myspring.springexercise.model.AsterixCharacter;
import org.myspring.springexercise.service.AsterixService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/asterix/character")
@RequiredArgsConstructor
public class AsterixController {
    private final AsterixService asterixService;

    @GetMapping("")
    public List<AsterixCharacter> getCharacter(@ModelAttribute AsterixCharacter filter) {
        return asterixService.getCharacter(filter);
    }

    @GetMapping("/{id}")
    public List<AsterixCharacter> getCharacter(@PathVariable String id) {
        return asterixService.getCharacter(id);
    }

    @PostMapping("")
    public void saveCharacter(@RequestBody CharacterDto characterDto) {
        asterixService.saveCharacter(characterDto);
    }

    @PostMapping("/batch")
    public void saveCharacters(@RequestBody List<AsterixCharacter> asterixCharacters) {
        asterixService.saveCharacters(asterixCharacters);
    }

    @PutMapping("")
    public void updateCharacters(@RequestBody AsterixCharacter asterixCharacter) {
        asterixService.updateCharacters(asterixCharacter);
    }

    @DeleteMapping("/{id}")
    public void deleteCharacter(@PathVariable String id) {
        asterixService.deleteCharacter(id);
    }
}
