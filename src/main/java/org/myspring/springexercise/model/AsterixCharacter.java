package org.myspring.springexercise.model;

import lombok.Builder;
import org.springframework.data.mongodb.core.mapping.Document;

@Builder
@Document("characters")
public record AsterixCharacter(String id, String name, Integer age, String profession) {
}
