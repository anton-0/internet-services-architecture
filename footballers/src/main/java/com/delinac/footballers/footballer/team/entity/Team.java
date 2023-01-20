package com.delinac.footballers.footballer.team.entity;

import com.delinac.footballers.footballer.entity.Footballer;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString()
@Entity
@Table(name = "teams")
public class Team implements Serializable {
    @Id
    private String name;

    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<Footballer> teamMembers;
}
