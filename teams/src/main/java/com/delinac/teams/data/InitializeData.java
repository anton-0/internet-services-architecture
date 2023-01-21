package com.delinac.teams.data;

import com.delinac.teams.team.entity.Team;
import com.delinac.teams.team.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class InitializeData {

    private final TeamService teamService;

    @Autowired
    public InitializeData(TeamService teamService) {this.teamService = teamService;}

    @PostConstruct
    private synchronized void init() {
        Team arsenal = Team.builder().name("Arsenal").city("London").build();
        Team chelsea = Team.builder().name("Chelsea").city("London").build();
        Team atletico = Team.builder().name("Atletico").city("Madrid").build();

        teamService.create(arsenal);
        teamService.create(chelsea);
        teamService.create(atletico);
    }
}
