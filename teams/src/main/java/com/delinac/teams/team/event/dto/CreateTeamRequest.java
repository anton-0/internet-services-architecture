package com.delinac.teams.team.event.dto;

import com.delinac.teams.team.entity.Team;
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

    public static Function<Team, CreateTeamRequest> entityToDtoMapper() {
        return entity -> CreateTeamRequest.builder()
                .name(entity.getName())
                .build();
    }
}
