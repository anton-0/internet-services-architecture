package com.delinac.footballers.footballer.service;

import com.delinac.footballers.footballer.entity.Footballer;
import com.delinac.footballers.footballer.repository.FootballerRepository;
import com.delinac.footballers.footballer.team.entity.Team;
import com.delinac.footballers.footballer.team.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class FootballerService {

    private final FootballerRepository footballerRepository;
    private final TeamRepository teamRepository;

    @Autowired
    public FootballerService(FootballerRepository footballerRepository, TeamRepository teamRepository) {
        this.footballerRepository = footballerRepository;
        this.teamRepository = teamRepository;
    }

    public Optional<Footballer> find(Long id) {
        return footballerRepository.findById(id);
    }

    public Optional<Footballer> find(String teamName, Long id) {
        Optional<Team> team = teamRepository.findById(teamName);
        if (team.isPresent()) {
            return footballerRepository.findByTeamAndId(team.get(), id);
        } else {
            return Optional.empty();
        }
    }

    public List<Footballer> findAll() {
        return footballerRepository.findAll();
    }
    public List<Footballer> findAll(Team team) {
        return footballerRepository.findAllByTeam(team);
    }

    @Transactional
    public Footballer create(Footballer footballer) {
        return footballerRepository.save(footballer);
    }
    @Transactional
    public void delete(Long id) {footballerRepository.deleteById(id);}
    @Transactional
    public void update(Footballer footballer) {
        footballerRepository.save(footballer);
    }
}
