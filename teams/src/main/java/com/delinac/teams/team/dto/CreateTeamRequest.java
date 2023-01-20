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
public class CreateTeamRequest {
    private String name;
    private String city;

    public static Function<CreateTeamRequest, Team> entityToDtoMapper() {
        return request -> Team.builder()
                .name(request.getName())
                .city(request.getCity())
                .build();
    }
}
