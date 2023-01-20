package com.delinac.footballers.footballer.controller;

import com.delinac.footballers.footballer.dto.CreateFootballerRequest;
import com.delinac.footballers.footballer.dto.GetFootballerResponse;
import com.delinac.footballers.footballer.dto.GetFootballersResponse;
import com.delinac.footballers.footballer.dto.UpdateFootballerRequest;
import com.delinac.footballers.footballer.entity.Footballer;
import com.delinac.footballers.footballer.service.FootballerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;

@RestController
@RequestMapping("api/footballers")
public class FootballerController {
    private final FootballerService footballerService;

    @Autowired
    public FootballerController(FootballerService footballerService) {
        this.footballerService = footballerService;
    }

    @GetMapping
    public ResponseEntity<GetFootballersResponse> getFootballers() {
        return ResponseEntity.ok(GetFootballersResponse.entityToDtoMapper().apply(footballerService.findAll()));
    }

    @GetMapping("{id}")
    public ResponseEntity<GetFootballerResponse> getFootballer(@PathVariable("id") Long id) {
        return footballerService.find(id).map(value -> ResponseEntity
                        .ok(GetFootballerResponse
                                .entityToDtoMapper()
                                .apply(value)))
                .orElseGet(() -> ResponseEntity
                        .notFound()
                        .build());
    }

    @PostMapping
    public ResponseEntity<Void> createFootballer(@RequestBody CreateFootballerRequest request, UriComponentsBuilder builder) {
        Footballer footballer = CreateFootballerRequest
                .dtoToEntityMapper(() -> null)
                .apply(request);

        footballer = footballerService.create(footballer);

        return ResponseEntity.created(builder.pathSegment("api", "footballers", "{id}")
                .buildAndExpand(footballer.getId()).toUri()).build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteFootballer(@PathVariable("id") Long id) {
        Optional<Footballer> footballer = footballerService.find(id);

        if (footballer.isPresent()) {
            footballerService.delete(footballer.get().getId());
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> updateFootballer(@RequestBody UpdateFootballerRequest request, @PathVariable("id") long id) {
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
