package org.myspring.springexercise.repository;

import org.myspring.springexercise.model.Character;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CharacterRepository extends MongoRepository<Character, String> {
    List<Character> findCharactersById(String id);
}
