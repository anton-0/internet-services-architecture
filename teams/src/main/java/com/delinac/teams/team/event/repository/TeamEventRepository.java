package com.delinac.teams.team.event.repository;

import com.delinac.teams.team.entity.Team;
import com.delinac.teams.team.event.dto.CreateTeamRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

@Repository
public class TeamEventRepository {
    private final RestTemplate restTemplate;

    @Autowired
    public TeamEventRepository(@Value("${footballer.footballers.url}") String baseUrl) {
        this.restTemplate = new RestTemplateBuilder().rootUri(baseUrl).build();
    }

    public void delete(Team team) {restTemplate.delete("/teams/{name}", team.getName());}

    public void create(Team team) {restTemplate.postForLocation("/teams", CreateTeamRequest.entityToDtoMapper().apply(team));}
}
