package com.delinac.footballers.footballer.team.controller;

import com.delinac.footballers.footballer.team.dto.CreateTeamRequest;
import com.delinac.footballers.footballer.team.entity.Team;
import com.delinac.footballers.footballer.team.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;

@RestController
@RequestMapping("api/teams")
public class TeamController {
    private final TeamService service;

    @Autowired
    public TeamController(TeamService service) {
        this.service = service;
    }

    @DeleteMapping("{name}")
    public ResponseEntity<Void> deleteTeam(@PathVariable("name") String name) {
        Optional<Team> team = service.find(name);
        if (team.isPresent()) {
            service.delete(name);
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("")
    public ResponseEntity<Void> createTeam(@RequestBody CreateTeamRequest request, UriComponentsBuilder builder) {
        Team team = CreateTeamRequest.dtoToEntityMapper().apply(request);

        service.create(team);
        return ResponseEntity.created(builder.pathSegment("api", "teams", "{name}").buildAndExpand(team.getName()).toUri()).build();
    }
}
