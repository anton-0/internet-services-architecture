package com.delinac.footballers.footballer.team.repository;

import com.delinac.footballers.footballer.team.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends JpaRepository<Team, String> {

}
