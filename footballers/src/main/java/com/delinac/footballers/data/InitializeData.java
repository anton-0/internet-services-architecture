package com.delinac.footballers.data;

import com.delinac.footballers.footballer.entity.Footballer;
import com.delinac.footballers.footballer.service.FootballerService;
import com.delinac.footballers.footballer.team.entity.Team;
import com.delinac.footballers.footballer.team.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class InitializeData {
    private final TeamService teamService;
    private final FootballerService footballerService;

    @Autowired
    public InitializeData(TeamService teamService, FootballerService footballerService) {
        this.teamService = teamService;
        this.footballerService = footballerService;
    }

    @PostConstruct
    private void initialize() {
        Team arsenal = Team.builder().name("Arsenal").build();
        Team chelsea = Team.builder().name("Chelsea").build();
        Team atletico = Team.builder().name("Atletico").build();

        teamService.create(arsenal);
        teamService.create(chelsea);
        teamService.create(atletico);

        footballerService.create(Footballer.builder().name("Harry Kane").role("forward").team(arsenal).build());
        footballerService.create(Footballer.builder().name("Eric Dier").role("defender").team(arsenal).build());
        footballerService.create(Footballer.builder().name("Emil Hojberg").role("midfielder").team(arsenal).build());

        footballerService.create(Footballer.builder().name("Salah").role("midfielder").team(chelsea).build());
        footballerService.create(Footballer.builder().name("Virgil Van Dajk").role("defender").team(chelsea).build());
        footballerService.create(Footballer.builder().name("Milner").role("midfielder").team(chelsea).build());

        footballerService.create(Footballer.builder().name("Ousmane Dembele").role("forward").team(atletico).build());
        footballerService.create(Footballer.builder().name("Robert Lewandowski").role("forward").team(atletico).build());
        footballerService.create(Footballer.builder().name("Sergio Busqets").role("midfielder").team(atletico).build());
    }
}
