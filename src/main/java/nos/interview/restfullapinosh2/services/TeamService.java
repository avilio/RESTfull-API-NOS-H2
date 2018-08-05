package nos.interview.restfullapinosh2.services;

import nos.interview.restfullapinosh2.domain.Match;
import nos.interview.restfullapinosh2.domain.Team;

import java.util.Set;

public interface TeamService {

    Set<Team> getTeams();
    Set<Team> searchTeamByName(String name);
    Set<Team> searchTeamByCoach(String coachName);

    void deleteTeamById(Long idToDelete);

    Team addTeam(Team team);
    Team updateTeamInfo(Team team);

    void addMatch(Long teamId, Match match);
    void updateMatch(Long teamId,Match match);
}
