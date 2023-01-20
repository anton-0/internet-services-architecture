package com.delinac.footballers.footballer.dto;

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
public class GetFootballersResponse {

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @ToString
    @EqualsAndHashCode
    public static class Footballer {
        private Long id;
        private String name;
    }

    @Singular
    private List<Footballer> footballers;
    public static Function<Collection<com.delinac.footballers.footballer.entity.Footballer>, GetFootballersResponse> entityToDtoMapper() {
        return footballers -> {
            GetFootballersResponseBuilder response = GetFootballersResponse.builder();
            footballers.stream()
                    .map(footballer -> Footballer.builder()
                            .id(footballer.getId())
                            .name(footballer.getName())
                            .build())
                    .forEach(response::footballer);
            return response.build();
        };
    }
}
