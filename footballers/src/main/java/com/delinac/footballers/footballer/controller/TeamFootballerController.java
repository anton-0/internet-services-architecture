package com.delinac.footballers.footballer.controller;

import com.delinac.footballers.footballer.dto.CreateFootballerRequest;
import com.delinac.footballers.footballer.dto.GetFootballerResponse;
import com.delinac.footballers.footballer.dto.GetFootballersResponse;
import com.delinac.footballers.footballer.dto.UpdateFootballerRequest;
import com.delinac.footballers.footballer.entity.Footballer;
import com.delinac.footballers.footballer.service.FootballerService;
import com.delinac.footballers.footballer.team.entity.Team;
import com.delinac.footballers.footballer.team.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/teams/{name}/footballers")
public class TeamFootballerController {
    private final TeamService teamService;
    private final FootballerService footballerService;

    @Autowired
    public TeamFootballerController(TeamService teamService, FootballerService footballerService) {
        this.teamService = teamService;
        this.footballerService = footballerService;
    }

    @GetMapping
    public ResponseEntity<GetFootballersResponse> getFootballers(@PathVariable("name") String name) {
        Optional<Team> team = teamService.find(name);

        if (team.isPresent()) {
            List<Footballer> footballers = footballerService.findAll(team.get());
            return ResponseEntity.ok(GetFootballersResponse.entityToDtoMapper().apply(footballers));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<GetFootballerResponse> getFootballer(@PathVariable("id") long id, @PathVariable String name) {
        return footballerService.find(name, id)
                .map(footballer -> ResponseEntity.ok(GetFootballerResponse.entityToDtoMapper().apply(footballer)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Void> createFootballer(@PathVariable("name") String name,
                                                 @RequestBody CreateFootballerRequest request,
                                                 UriComponentsBuilder builder) {
        Optional<Team> team = teamService.find(name);

        if (team.isPresent()) {
            Footballer footballer = CreateFootballerRequest
                    .dtoToEntityMapper(team::get)
                    .apply(request);

            footballerService.create(footballer);
            return ResponseEntity.created(builder.pathSegment("api", "teams", "{name}", "cars", "{id}")
                    .buildAndExpand(team.get().getName(), footballer.getId()).toUri()).build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteFootballer(@PathVariable("id") long id, @PathVariable String name) {
        Optional<Footballer> footballer = footballerService.find(name, id);

        if (footballer.isPresent()) {
            footballerService.delete(footballer.get().getId());
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> updateFootballer(@RequestBody UpdateFootballerRequest request,
                                                 @PathVariable("id") long id) {
        Optional<Footballer> footballer = footballerService.find(id);

        if (footballer.isPresent()) {
            UpdateFootballerRequest.dtoToEntityUpdater().apply(footballer.get(), request);
            footballerService.update(footballer.get());
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
