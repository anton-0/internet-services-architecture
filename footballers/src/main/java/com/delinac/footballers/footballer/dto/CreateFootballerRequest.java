package com.delinac.footballers.footballer.dto;

import com.delinac.footballers.footballer.entity.Footballer;
import com.delinac.footballers.footballer.team.entity.Team;
import lombok.*;

import java.util.function.Function;
import java.util.function.Supplier;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class CreateFootballerRequest {
    private String name;
    private String team;
    private String role;

    public static Function<CreateFootballerRequest, Footballer> dtoToEntityMapper(
            Supplier<Team> teamSupplier) {
        return request -> Footballer.builder()
                .name(request.getName())
                .team(teamSupplier.get())
                .role(request.getRole())
                .build();
    }
}
