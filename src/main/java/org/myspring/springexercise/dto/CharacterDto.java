package org.myspring.springexercise.dto;

import lombok.Builder;

@Builder
public record CharacterDto(String name, Integer age, String profession) {

}
