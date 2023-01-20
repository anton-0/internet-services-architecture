package com.delinac.teams.team.dto;

import com.delinac.teams.team.entity.Team;
import lombok.*;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class GetTeamsResponse {
    @Singular
    private List<String> teams;

    public static Function<Collection<Team>, GetTeamsResponse> entityToDtoMapper() {
        return teams -> {
            GetTeamsResponseBuilder response = GetTeamsResponse.builder();
            teams.stream()
                    .map(Team::getName)
                    .forEach(response::team);
            return response.build();
        };
    }
}
