package com.delinac.teams.team.controller;

import com.delinac.teams.team.dto.CreateTeamRequest;
import com.delinac.teams.team.dto.GetTeamResponse;
import com.delinac.teams.team.dto.GetTeamsResponse;
import com.delinac.teams.team.dto.UpdateTeamRequest;
import com.delinac.teams.team.entity.Team;
import com.delinac.teams.team.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@RestController
@RequestMapping("api/teams")
public class TeamController {
    private final TeamService teamService;

    @Autowired
    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @GetMapping
    public ResponseEntity<GetTeamsResponse> getTeams() {
        List<Team> all = teamService.findAll();
        Function<Collection<Team>, GetTeamsResponse> mapper = GetTeamsResponse.entityToDtoMapper();
        GetTeamsResponse response = mapper.apply(all);
        return ResponseEntity.ok(response);
    }

    @GetMapping("{name}")
    public ResponseEntity<GetTeamResponse> getTeam(@PathVariable("name") String name) {
        return teamService.find(name)
                .map(value -> ResponseEntity.ok(GetTeamResponse.entityToDtoMapper().apply(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Void> createTeam(@RequestBody CreateTeamRequest request, UriComponentsBuilder builder) {
        Team team = CreateTeamRequest.entityToDtoMapper().apply(request);
        teamService.create(team);
        return ResponseEntity.created(builder.pathSegment("api", "teams", "{name}")
                .buildAndExpand(team.getName()).toUri()).build();
    }

    @DeleteMapping("{name}")
    public ResponseEntity<Void> deleteTeam(@PathVariable("name") String name) {
        Optional<Team> team = teamService.find(name);
        if (team.isPresent()) {
            teamService.delete(team.get());
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("{name}")
    public ResponseEntity<Void> updateTeam(@RequestBody UpdateTeamRequest request, @PathVariable("name") String name) {
        Optional<Team> team = teamService.find(name);
        if (team.isPresent()) {
            UpdateTeamRequest.dtoToEntityUpdater().apply(team.get(), request);
            teamService.update(team.get());
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
