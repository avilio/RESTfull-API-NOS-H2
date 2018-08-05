package nos.interview.restfullapinosh2.repositories;

import nos.interview.restfullapinosh2.domain.Team;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface TeamRepository extends CrudRepository<Team,Long> {

    Set<Team> findByName(String name);
    Set<Team> findByCoachName(String coachName);
}
