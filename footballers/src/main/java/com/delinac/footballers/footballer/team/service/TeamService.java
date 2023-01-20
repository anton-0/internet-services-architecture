package com.delinac.footballers.footballer.team.service;

import com.delinac.footballers.footballer.team.entity.Team;
import com.delinac.footballers.footballer.team.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class TeamService {
    private final TeamRepository repository;

    @Autowired
    public TeamService(TeamRepository repository) {
        this.repository = repository;
    }

    public Optional<Team> find(String name) {
        return repository.findById(name);
    }

    @Transactional
    public void create(Team team) {
        repository.save(team);
    }

    @Transactional
    public void delete(String name) {
        repository.deleteById(name);
    }
}
