package com.delinac.teams.team.service;

import com.delinac.teams.team.entity.Team;
import com.delinac.teams.team.event.repository.TeamEventRepository;
import com.delinac.teams.team.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TeamService {
    private final TeamRepository repository;
    private final TeamEventRepository eventRepository;

    @Autowired
    public TeamService(TeamRepository repository, TeamEventRepository eventRepository) {
        this.repository = repository;
        this.eventRepository = eventRepository;
    }

    public Optional<Team> find(String name) {
        return repository.findById(name);
    }

    public List<Team> findAll() {
        return repository.findAll();
    }

    @Transactional
    public void create(Team team) {
        repository.save(team);
        eventRepository.create(team);
    }
    @Transactional
    public void delete(Team team) {
        eventRepository.delete(team);
        repository.delete(team);
    }
    @Transactional
    public void update(Team team) {
        repository.save(team);
    }
}
