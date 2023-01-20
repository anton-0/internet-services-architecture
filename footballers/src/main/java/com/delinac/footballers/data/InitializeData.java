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
        Team tottenham = Team.builder().name("Tottenham Hotspurs FC").build();
        Team liverpool = Team.builder().name("Liverpool FC").build();
        Team barcelona = Team.builder().name("FC Barcelona").build();

        teamService.create(tottenham);
        teamService.create(liverpool);
        teamService.create(barcelona);

        footballerService.create(Footballer.builder().name("Harry Kane").role("forward").team(tottenham).build());
        footballerService.create(Footballer.builder().name("Eric Dier").role("defender").team(tottenham).build());
        footballerService.create(Footballer.builder().name("Emil Hojberg").role("midfielder").team(tottenham).build());

        footballerService.create(Footballer.builder().name("Salah").role("midfielder").team(liverpool).build());
        footballerService.create(Footballer.builder().name("Virgil Van Dajk").role("defender").team(liverpool).build());
        footballerService.create(Footballer.builder().name("Milner").role("midfielder").team(liverpool).build());

        footballerService.create(Footballer.builder().name("Ousmane Dembele").role("forward").team(barcelona).build());
        footballerService.create(Footballer.builder().name("Robert Lewandowski").role("forward").team(barcelona).build());
        footballerService.create(Footballer.builder().name("Sergio Busqets").role("midfielder").team(barcelona).build());
    }
}
