package nos.interview.restfullapinosh2.domain;

import lombok.Data;

import javax.persistence.*;


@Data
@Entity
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long againstTeamId;
    @Enumerated(value = EnumType.STRING)
    private MatchResult matchResult;
}
