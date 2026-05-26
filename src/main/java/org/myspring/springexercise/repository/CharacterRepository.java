package org.myspring.springexercise.repository;

import org.myspring.springexercise.model.AsterixCharacter;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CharacterRepository extends MongoRepository<AsterixCharacter, String> {
    List<AsterixCharacter> findCharactersById(String id);
}
