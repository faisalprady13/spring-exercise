package org.myspring.springexercise.model;

import org.springframework.data.mongodb.core.mapping.Document;


@Document("characters")
public record Character(String id, String name, Integer age, String profession) {
}
