package com.delinac.teams.team.dto;

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
public class GetTeamResponse {
    private String name;
    private String city;

    public static Function<Team, GetTeamResponse> entityToDtoMapper() {
        return team -> GetTeamResponse.builder()
                .name(team.getName())
                .city(team.getCity())
                .build();
    }
}
