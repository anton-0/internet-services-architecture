package com.delinac.footballers.footballer.repository;

import com.delinac.footballers.footballer.entity.Footballer;
import com.delinac.footballers.footballer.team.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Repository
public interface FootballerRepository extends JpaRepository<Footballer, Long> {
    List<Footballer> findAllByTeam(Team team);
    Optional<Footballer> findByTeamAndId(Team team, Long id);
}
