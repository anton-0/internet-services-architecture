package com.delinac.footballers.footballer.dto;

import com.delinac.footballers.footballer.entity.Footballer;
import lombok.*;

import java.util.function.Function;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class GetFootballerResponse {
    private Long id;
    private String name;
    private String team;
    private String role;

    public static Function<Footballer, GetFootballerResponse> entityToDtoMapper() {
        return footballer -> GetFootballerResponse.builder()
                .id(footballer.getId())
                .name(footballer.getName())
                .team(footballer.getTeam().getName())
                .role(footballer.getRole())
                .build();
    }
}
