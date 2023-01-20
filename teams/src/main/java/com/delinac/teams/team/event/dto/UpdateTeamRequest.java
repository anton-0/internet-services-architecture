package com.delinac.teams.team.event.dto;

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

    public static BiFunction<Team, com.delinac.teams.team.dto.UpdateTeamRequest, Team> dtoToEntityUpdater() {
        return (team, request) -> {
            team.setName(request.getName());
            return team;
        };
    }
}
