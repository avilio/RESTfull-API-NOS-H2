package nos.interview.restfullapinosh2.domain;


import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    private Coach coach;

    @ManyToMany(cascade =CascadeType.ALL )
    private Set<Match> matches;


}
