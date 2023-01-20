package com.delinac.footballers.footballer.team.dto;

import com.delinac.footballers.footballer.team.entity.Team;
import lombok.*;

import java.util.function.Function;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class CreateTeamRequest {
    private String name;

    public static Function<CreateTeamRequest, Team> dtoToEntityMapper() {
        return request -> Team.builder()
                .name(request.getName())
                .build();
    }
}
