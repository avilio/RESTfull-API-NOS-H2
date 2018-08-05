package nos.interview.restfullapinosh2.controllers;

import nos.interview.restfullapinosh2.domain.Match;
import nos.interview.restfullapinosh2.domain.Team;
import nos.interview.restfullapinosh2.services.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Set;


@Controller
@RequestMapping("/team")
public class TeamController {

    private TeamService teamService;

    @Autowired
    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @GetMapping
    public ResponseEntity<?> getTeams() {
        Set<Team> teams = teamService.getTeams();
        return ResponseEntity.ok(teams);
    }

    @PostMapping
    public ResponseEntity<?> addTeam(@RequestBody Team team) {
        Team savedTeam = teamService.addTeam(team);
        return ResponseEntity.ok(savedTeam);
    }

    @PutMapping
    public ResponseEntity<?> updateTeam(@RequestBody Team team) {
        Team savedTeam = teamService.updateTeamInfo(team);
        return ResponseEntity.ok(savedTeam);
    }

    @GetMapping("/{teamName}")
    public ResponseEntity<?> searchByTeamName(@PathVariable String teamName) {
        Set<Team> teams = teamService.searchTeamByName(teamName);
        return ResponseEntity.ok(teams);
    }

    @GetMapping("/coach/{coachName}")
    public ResponseEntity<?> searchByCoachName(@PathVariable String coachName) {
        Set<Team> teams = teamService.searchTeamByCoach(coachName);
        return ResponseEntity.ok(teams);
    }

    @PostMapping("/match/{teamId}")
    public ResponseEntity<?> addMatch(@PathVariable Long teamId, @RequestBody Match match) {
        teamService.addMatch(teamId, match);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/match/update/{teamId}")
    public ResponseEntity<?> updateMatch(@PathVariable Long teamId, @RequestBody Match match){
        teamService.updateMatch(teamId,match);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{teamId}")
    public ResponseEntity<?> deleteTeamById(@PathVariable Long teamId){
        teamService.deleteTeamById(teamId);
        return ResponseEntity.noContent().build();
    }

}
