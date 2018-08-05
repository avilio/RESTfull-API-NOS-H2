package nos.interview.restfullapinosh2.services;

import nos.interview.restfullapinosh2.domain.Coach;
import nos.interview.restfullapinosh2.domain.Team;
import nos.interview.restfullapinosh2.repositories.TeamRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.mockito.Mockito.when;


@RunWith(SpringRunner.class)
@SpringBootTest
public class TeamServiceImplTest {


    @Mock
    private TeamRepository teamRepository;

    @Autowired
    private TeamService teamService;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getTeams() {

        List<Team> teams = new ArrayList<>();

        when(teamRepository.findAll()).thenReturn(teams);

        Set<Team> team1 = teamService.getTeams();

        Assert.assertNotNull(team1);
    }

    @Test
    public void addTeam() {

        List<Team> teams = new ArrayList<>();

        when(teamRepository.saveAll(Mockito.anyIterable())).thenReturn(teams);

        Team team1 = teamService.addTeam(createTeam("PORTO"));

        Assert.assertNotNull(team1);
        Assert.assertEquals("PORTO", team1.getName());
    }

    private Team createTeam(String name) {
        Team t = new Team();
        Coach c = new Coach();

        c.setName("jj");
        t.setName(name);
        t.setCoach(c);

        return t;
    }
}