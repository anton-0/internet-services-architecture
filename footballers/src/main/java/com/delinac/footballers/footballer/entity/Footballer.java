package com.delinac.footballers.footballer.entity;

import com.delinac.footballers.footballer.team.entity.Team;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString()
@Entity
@Table(name = "footballers")
public class Footballer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToOne
    @JoinColumn(name = "teams")
    private Team team;
    private String role;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Footballer footballer = (Footballer) o;
        return id != null && Objects.equals(id, footballer.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
