package org.myspring.springexercise.service;

import org.junit.jupiter.api.Test;
import org.myspring.springexercise.dto.CharacterDto;
import org.myspring.springexercise.model.AsterixCharacter;
import org.myspring.springexercise.repository.CharacterRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AsterixServiceTest {

    private final CharacterRepository mockCharacterRepository = mock(CharacterRepository.class);
    private final IdService mockIdService = mock(IdService.class);

    @Test
    void getCharacter_returnAllCharacters() {
        AsterixService asterixService = new AsterixService(mockCharacterRepository, mockIdService);
        AsterixCharacter char1 = AsterixCharacter.builder().id("1").age(25).name("asterix").profession("warrior").build();
        List<AsterixCharacter> charList = new ArrayList<>(List.of(char1));
        when(mockCharacterRepository.findAll()).thenReturn(charList);

        List<AsterixCharacter> result = asterixService.getCharacter(AsterixCharacter.builder().build());

        verify(mockCharacterRepository, times(1)).findAll();
        assertEquals(charList, result);
    }

    @Test
    void getCharacter_returnFilteredCharacters() {
        AsterixService asterixService = new AsterixService(mockCharacterRepository, mockIdService);
        AsterixCharacter char1 = AsterixCharacter.builder().id("1").age(25).name("asterix").profession("warrior").build();
        AsterixCharacter char2 =
                AsterixCharacter.builder().id("2").age(20).name("obelix").profession("warrior").build();
        AsterixCharacter char3 =
                AsterixCharacter.builder().id("3").age(25).name("randomName").profession("warrior").build();
        List<AsterixCharacter> charList = new ArrayList<>(List.of(char1, char2, char3));
        when(mockCharacterRepository.findAll()).thenReturn(charList);

        List<AsterixCharacter> result = asterixService.getCharacter(AsterixCharacter.builder().age(25).build());
        List<AsterixCharacter> expected = new ArrayList<>(List.of(char1, char3));
        verify(mockCharacterRepository, times(1)).findAll();
        assertEquals(expected, result);
    }

    @Test
    void getCharacter_returnById() {
        AsterixService asterixService = new AsterixService(mockCharacterRepository, mockIdService);

        AsterixCharacter char1 = AsterixCharacter.builder().id("1").age(25).name("asterix").profession("warrior").build();
        List<AsterixCharacter> charList = new ArrayList<>(List.of(char1));
        when(mockCharacterRepository.findCharactersById("1")).thenReturn(charList);

        List<AsterixCharacter> result = asterixService.getCharacter("1");
        verify(mockCharacterRepository, times(1)).findCharactersById("1");
        assertEquals(charList, result);
    }

    @Test
    void saveCharacter_saveSingleCharacter() {
        AsterixService asterixService = new AsterixService(mockCharacterRepository, mockIdService);
        when(mockIdService.randomId()).thenReturn("1");
        AsterixCharacter char1 = AsterixCharacter.builder().id("1").age(25).name("asterix").profession("warrior").build();

        asterixService.saveCharacter(CharacterDto.builder().age(25).name("asterix").profession("warrior").build());

        verify(mockIdService, times(1)).randomId();
        verify(mockCharacterRepository, times(1)).save(char1);
    }

    @Test
    void saveCharacters_saveMultipleCharacters() {
        AsterixService asterixService = new AsterixService(mockCharacterRepository, mockIdService);
        AsterixCharacter char1 = AsterixCharacter.builder().id("1").age(25).name("asterix").profession("warrior").build();
        AsterixCharacter char2 =
                AsterixCharacter.builder().id("2").age(25).name("obelix").profession("warrior").build();
        List<AsterixCharacter> charList = new ArrayList<>(List.of(char1, char2));

        asterixService.saveCharacters(charList);

        verify(mockCharacterRepository, times(1)).saveAll(charList);
    }

    @Test
    void updateCharacters() {
        AsterixService asterixService = new AsterixService(mockCharacterRepository, mockIdService);
        AsterixCharacter char1 = AsterixCharacter.builder().id("1").age(25).name("asterix").profession("warrior").build();
        when(mockCharacterRepository.findById("1")).thenReturn(Optional.of(char1));

        asterixService.updateCharacters(AsterixCharacter.builder().id("1").age(30).build());

        verify(mockCharacterRepository, times(1)).findById("1");
        verify(mockCharacterRepository, times(1)).save(AsterixCharacter.builder().id("1").age(30).build());
    }


    @Test
    void updateCharacters_shouldThrowException_whenCharacterNotFound() {
        AsterixService asterixService = new AsterixService(mockCharacterRepository, mockIdService);
        when(mockCharacterRepository.findById("1")).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () ->
                asterixService.updateCharacters(AsterixCharacter.builder().id("1").age(30).build())
        );
        verify(mockCharacterRepository, times(1)).findById("1");
    }

    @Test
    void deleteCharacter() {
        AsterixService asterixService = new AsterixService(mockCharacterRepository, mockIdService);
        AsterixCharacter char1 = AsterixCharacter.builder().id("1").age(25).name("asterix").profession("warrior").build();
        when(mockCharacterRepository.findById("1")).thenReturn(Optional.of(char1));

        asterixService.deleteCharacter("1");

        verify(mockCharacterRepository, times(1)).findById("1");
        verify(mockCharacterRepository, times(1)).deleteById("1");
    }


    @Test
    void deleteCharacter_shouldThrowException_whenCharacterNotFound() {
        AsterixService asterixService = new AsterixService(mockCharacterRepository, mockIdService);
        when(mockCharacterRepository.findById("1")).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () ->
                asterixService.deleteCharacter("1")
        );
        verify(mockCharacterRepository, times(1)).findById("1");

    }
}