package com.delinac.teams.team.dto;

import com.delinac.teams.team.entity.Team;
import lombok.*;

import java.util.function.BiFunction;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class UpdateTeamRequest {
    private String name;
    private String city;

    public static BiFunction<Team, UpdateTeamRequest, Team> dtoToEntityUpdater() {
        return (team, request) -> {
            team.setName(request.getName());
            team.setCity(request.getCity());
            return team;
        };
    }
}
