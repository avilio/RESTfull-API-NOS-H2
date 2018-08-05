
INSERT INTO coach (name) VALUES ('Rui');
INSERT INTO coach (name) VALUES ('Jose');
INSERT INTO coach (name) VALUES ('Sergio');

INSERT INTO match (against_team_id,match_result) VALUES (1,'LOST');
INSERT INTO match (against_team_id,match_result) VALUES (3,'WON');

INSERT INTO match (against_team_id,match_result) VALUES (2,'WON');
INSERT INTO match (against_team_id,match_result) VALUES (3,'LOST');

INSERT INTO match (against_team_id,match_result) VALUES (1,'LOST');
INSERT INTO match (against_team_id,match_result) VALUES (2,'WON');


INSERT INTO team (name,coach_id) VALUES ('SLB',1);
INSERT INTO team (name,coach_id) VALUES ('SCP',2);
INSERT INTO team (name,coach_id) VALUES ('FCP',3);

INSERT INTO team_matches (matches_id,team_id) VALUES (1,3);
INSERT INTO team_matches (matches_id,team_id) VALUES (2,1);

INSERT INTO team_matches (matches_id,team_id) VALUES (3,3);
INSERT INTO team_matches (matches_id,team_id) VALUES (4,2);

INSERT INTO team_matches (matches_id,team_id) VALUES (5,2);
INSERT INTO team_matches (matches_id,team_id) VALUES (6,1);







