package nos.interview.restfullapinosh2.services;

import lombok.extern.slf4j.Slf4j;
import nos.interview.restfullapinosh2.domain.Match;
import nos.interview.restfullapinosh2.domain.MatchResult;
import nos.interview.restfullapinosh2.domain.Team;
import nos.interview.restfullapinosh2.repositories.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
public class TeamServiceImpl implements TeamService {

    private final TeamRepository teamRepository;

    @Autowired
    public TeamServiceImpl(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    @Override
    public Set<Team> getTeams() {

        log.info("Search for all Teams");
        Set<Team> teamSet = new HashSet<>();
        teamRepository.findAll().iterator().forEachRemaining(teamSet::add);

        return teamSet;
    }

    @Override
    public Set<Team> searchTeamByName(String name) {
        log.info("Searching team with name:"+name);
        return teamRepository.findByName(name);
    }

    @Override
    public Set<Team> searchTeamByCoach(String coachName) {
        log.info("Searching team with coach:"+coachName);
        return teamRepository.findByCoachName(coachName);
    }

    @Override
    public void deleteTeamById(Long idToDelete) {
        teamRepository.deleteById(idToDelete);
        log.info("Team with id:" + idToDelete +" Deleted");
    }

    @Override
    public Team addTeam(Team team) {
        Team savedTeam = teamRepository.save(team);
        log.info("Team "+team +" was added!");

        return savedTeam;
    }

    @Override
    public Team updateTeamInfo(Team team) {

        Team teamFromDb = teamRepository.findById(team.getId()).get();

        if (!team.getMatches().isEmpty())
            teamFromDb.setMatches(team.getMatches());
        if (team.getCoach()!= null)
            teamFromDb.setCoach(team.getCoach());
        if (team.getName()!= null)
            teamFromDb.setName(team.getName());

        Team savedTeam = teamRepository.save(teamFromDb);
        log.info("Team "+team +" was updated!");
        return savedTeam;
    }

    @Override
    public void addMatch(Long teamId, Match match) {

        Optional<Team> teamOptional = teamRepository.findById(teamId);

        if(teamOptional.isPresent()) {
            Team team = teamOptional.get();
            team.getMatches().add(match);

            teamRepository.save(team);
            log.info("match added: " + match.toString() + "to team:" + team.getName());
        }

        Optional<Team> againstTeamObject = teamRepository.findById(match.getAgainstTeamId());

        if(againstTeamObject.isPresent()) {
            Match againstMatch = new Match();
            againstMatch.setAgainstTeamId(teamId);
            againstMatch.setMatchResult(inverseMatchResult(match.getMatchResult()));
            Team againstTeam = againstTeamObject.get();
            againstTeam.getMatches().add(againstMatch);

            teamRepository.save(againstTeam);
            log.info("match added: " + againstMatch.toString() + "to team:" + againstTeam.getName());
        }

        log.info("Match between" + teamOptional.get().getName() + "and" + againstTeamObject.get().getName() + "added");
    }

    @Override
    public void updateMatch(Long teamId, Match match) {

        if (match.getId() == null || teamId == null)
            throw new IllegalArgumentException("Match Id or Team ID is null");

        Optional<Team> teamOptional = teamRepository.findById(teamId);

        if(teamOptional.isPresent()) {
            Team team = teamOptional.get();
            for (Match teamMatch : team.getMatches()) {
                if (teamMatch.getId().equals(match.getId())){
                    teamMatch.setMatchResult(match.getMatchResult());
                    break;
                }
            }
            teamRepository.save(team);
        }

        Optional<Team> againstTeamOptional = teamRepository.findById(match.getAgainstTeamId());

        if (againstTeamOptional.isPresent()){

            Team againstTeam = againstTeamOptional.get();
            for (Match againstTeamMatch : againstTeam.getMatches()) {
                if (againstTeam.getId().equals(match.getAgainstTeamId())) {
                    againstTeamMatch.setMatchResult(inverseMatchResult(match.getMatchResult()));
                    break;
                }
            }
            teamRepository.save(againstTeam);
        }

        log.info( match +" was updated!");
    }

    private MatchResult inverseMatchResult(MatchResult matchResult) {

        switch (matchResult){
            case WON: return MatchResult.LOST;
            case LOST: return MatchResult.WON;
            default: return matchResult;
        }
    }
}
