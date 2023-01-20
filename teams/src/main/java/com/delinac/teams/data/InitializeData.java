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
        Team tottenham = Team.builder().name("Tottenham Hotspurs FC").city("London").build();
        Team liverpool = Team.builder().name("Liverpool FC").city("Liverpool").build();
        Team barcelona = Team.builder().name("FC Barcelona").city("Barcelona").build();

        teamService.create(tottenham);
        teamService.create(liverpool);
        teamService.create(barcelona);
    }
}
