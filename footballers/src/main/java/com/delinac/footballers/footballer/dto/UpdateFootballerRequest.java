package com.delinac.footballers.footballer.dto;

import com.delinac.footballers.footballer.entity.Footballer;
import lombok.*;

import java.util.function.BiFunction;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class UpdateFootballerRequest {
    private String name;
    private String role;

    public static BiFunction<Footballer, UpdateFootballerRequest, Footballer> dtoToEntityUpdater() {
        return (footballer, request) -> {
            footballer.setName(request.getName());
            footballer.setRole(request.getRole());
            return footballer;
        };
    }
}
