package edu.northeastern.cs5200;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.northeastern.cs5200.models.Away;
import edu.northeastern.cs5200.models.AwayLineUp;
import edu.northeastern.cs5200.models.Club;
import edu.northeastern.cs5200.models.ClubSeasonData;
import edu.northeastern.cs5200.models.Coach;
import edu.northeastern.cs5200.models.Faculty;
import edu.northeastern.cs5200.models.Home;
import edu.northeastern.cs5200.models.HomeLineUp;
import edu.northeastern.cs5200.models.Jersey;
import edu.northeastern.cs5200.models.Match;
import edu.northeastern.cs5200.models.News;
import edu.northeastern.cs5200.models.Player;
import edu.northeastern.cs5200.models.Season;
import edu.northeastern.cs5200.models.Tournament;
import edu.northeastern.cs5200.repositories.AwayLineUpRepository;
import edu.northeastern.cs5200.repositories.AwayRepository;
import edu.northeastern.cs5200.repositories.ClubRepository;
import edu.northeastern.cs5200.repositories.ClubSeasonDataRepository;
import edu.northeastern.cs5200.repositories.CoachRepository;
import edu.northeastern.cs5200.repositories.FacultyRepository;
import edu.northeastern.cs5200.repositories.HomeLineUpRepository;
import edu.northeastern.cs5200.repositories.HomeRepository;
import edu.northeastern.cs5200.repositories.JerseyRepository;
import edu.northeastern.cs5200.repositories.MatchRepository;
import edu.northeastern.cs5200.repositories.NewsRepository;
import edu.northeastern.cs5200.repositories.PlayerRepository;
import edu.northeastern.cs5200.repositories.SeasonRepository;
import edu.northeastern.cs5200.repositories.TournamentRepository;

@Service
public class SportraderWebServiceClient {
	
	@Autowired
	SeasonRepository sr;
	@Autowired
	FacultyRepository fr;
	@Autowired
	ClubRepository cr;
	@Autowired
	TournamentRepository tr;
	@Autowired
	CoachRepository coachr;
	@Autowired
	JerseyRepository jr;
	@Autowired
	PlayerRepository pr;
	@Autowired
	MatchRepository mr;
	@Autowired
	HomeRepository hr;
	@Autowired
	AwayRepository ar;
	@Autowired
	HomeLineUpRepository hlr;
	@Autowired
	AwayLineUpRepository alr;
	@Autowired
	NewsRepository nr;
	@Autowired
	ClubSeasonDataRepository csdr;
	
	
	
	
	public static final String CLUB_URL = "https://api.sportradar.us/soccer-t3/eu/en/teams/{id}/profile.json?api_key=2rtp9njqtwgunh6rhmqchet5";
	public static final String TOURNAMENT_URL = "https://api.sportradar.us/soccer-t3/eu/en/tournaments/sr:tournament:{id}/info.json?api_key=2rtp9njqtwgunh6rhmqchet5";
	public static final String SEASON_LIST_URL = "https://api.sportradar.us/soccer-t3/eu/en/tournaments/{id}/seasons.json?api_key=2rtp9njqtwgunh6rhmqchet5";
	public static final String SEASON_URL = "https://api.sportradar.us/soccer-t3/eu/en/tournaments/{id}/info.json?api_key=2rtp9njqtwgunh6rhmqchet5";
	private static final String SEASON_MATCH_URL = "https://api.sportradar.us/soccer-t3/eu/en/tournaments/{id}/results.json?api_key=2rtp9njqtwgunh6rhmqchet5";
	private static final String MATCH_LINEUP_URL = "https://api.sportradar.us/soccer-t3/eu/en/matches/{id}/lineups.json?api_key=2rtp9njqtwgunh6rhmqchet5";
	private static final String MATCH_URL = "https://api.sportradar.us/soccer-t3/eu/en/matches/{id}/summary.json?api_key=2rtp9njqtwgunh6rhmqchet5";
	private static final String MATCH_NEWS_URL = "https://api.sportradar.us/soccer-t3/eu/en/matches/{id}/funfacts.json?api_key=2rtp9njqtwgunh6rhmqchet5";
	public static final String CLUB_SEASON_DATA_URL = "https://api.sportradar.us/soccer-t3/eu/en/tournaments/sr:tournament:23/standings.json?api_key=2rtp9njqtwgunh6rhmqchet5";
	
	
	
	public static String sendGetRequestForNews(String requestUrl) {
		StringBuffer res = new StringBuffer();
		try {
			URL url = new URL(requestUrl);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("Accept", "*/*");
			connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
			InputStream stream = connection.getInputStream();
			InputStreamReader reader = new InputStreamReader(stream);
			BufferedReader buffer = new BufferedReader(reader);
			String line;
			while ((line = buffer.readLine()) != null) {
			    res.append(line);
			}
			buffer.close();
			connection.disconnect();

		} catch (Exception e) {
			if (e instanceof FileNotFoundException) {
				return "News Not Found";
			}
			e.printStackTrace();
		} 
		return res.toString();
	}
	
	
	public static String sendGetRequest(String requestUrl) {
		StringBuffer res = new StringBuffer();
		try {
			URL url = new URL(requestUrl);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("Accept", "*/*");
			connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
			InputStream stream = connection.getInputStream();
			InputStreamReader reader = new InputStreamReader(stream);
			BufferedReader buffer = new BufferedReader(reader);
			String line;
			while ((line = buffer.readLine()) != null) {
			    res.append(line);
			}
			buffer.close();
			connection.disconnect();

		} catch (Exception e) {
			e.printStackTrace();
		} 
		return res.toString();
	}
	
	private String generateUsername(String s) {
		return s.replaceAll(",", "").replaceAll(" ", "").toLowerCase();
	}
	private String generatePassword(String s) {
		return "iam" + generateUsername(s);
	}
	private boolean checkValidJSON(JSONObject jo, String[] s) {
		Set<String> values = jo.keySet();
		Set<String> check = new HashSet<>(Arrays.asList(s));
		return values.containsAll(check);
		
	}
	
	public void mapClubSeasonData() {
		String jsonRes = sendGetRequest(CLUB_SEASON_DATA_URL);
		while (jsonRes.isEmpty()) {
			try {
				Thread.sleep(3000);
				jsonRes = sendGetRequest(CLUB_SEASON_DATA_URL);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		}
		JSONObject jo = new JSONObject(jsonRes);
		JSONObject seasonj = jo.getJSONObject("season");
		Season season = sr.findSeasonBySeasonId(seasonj.getString("id"));
		JSONArray standingsj = jo.getJSONArray("standings");
		JSONObject standingj = standingsj.getJSONObject(0);
		JSONArray groupsj = standingj.getJSONArray("groups");
		groupsj.forEach(item1 -> {
			if (item1 instanceof JSONObject) {
				JSONObject groupj = (JSONObject) item1;
				JSONArray tsj = groupj.getJSONArray("team_standings");
				tsj.forEach(item2 -> {
					if (item2 instanceof JSONObject) {
						JSONObject tstandingj = (JSONObject) item2;
						JSONObject teamj = tstandingj.getJSONObject("team");
						Club club = cr.findClubByClubId(teamj.getString("id"));
						ClubSeasonData csd = new ClubSeasonData();
						csd.setClub(club);
						csd.setSeason(season);
						csd.setRank(tstandingj.getInt("rank"));
						csd.setMatchesPlayed(tstandingj.getInt("played"));
						csd.setMatchesWon(tstandingj.getInt("win"));
						csd.setMatchesDrawn(tstandingj.getInt("draw"));
						csd.setMatchesLost(tstandingj.getInt("loss"));
						csd.setGoalsScored(tstandingj.getInt("goals_for"));
						csd.setGoalsConceded(tstandingj.getInt("goals_against"));
						csdr.save(csd);
					}
				});
						
			}
		});
	}
	
	public void mapAllMatchesNewsData() {
		ArrayList<Match> matches = (ArrayList<Match>) mr.findAll();
		for (Match m: matches) {
			mapMatchNewsData(m);
		}
	}
	
	public void mapMatchNewsData(Match match) {
		News news = new News();
		news.setMatch(match);
		String url = MATCH_NEWS_URL.replace("{id}", match.getMatch_id());
		String jsonRes = sendGetRequestForNews(url);
		while (jsonRes.isEmpty()) {
			try {
				Thread.sleep(3000);
				jsonRes = sendGetRequestForNews(url);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		}
		if (jsonRes.equals("News Not Found")) {
			news.setFact(jsonRes);
		} else {
			JSONObject jo = new JSONObject(jsonRes);
			Set<String> check = jo.keySet();
			if (check.contains("facts")) {
				JSONArray factsj = jo.getJSONArray("facts");
				StringBuilder sb = new StringBuilder();
				factsj.forEach(item -> {
					if (item instanceof JSONObject) {
						JSONObject statementsj = (JSONObject) item;
						sb.append("/n");
						sb.append(statementsj.getString("statement"));
						sb.append("/n");
					}
				});
				news.setFact(sb.toString());
			}
		}
		nr.save(news);
	}
	
	public void mapMatchData(Match match, Home home, Away away, ArrayList<HomeLineUp> hlps, ArrayList<AwayLineUp> alps) {
		String lineup_url = MATCH_LINEUP_URL.replace("{id}", match.getMatch_id());
		String match_url = MATCH_URL.replace("{id}", match.getMatch_id());
		String lineup_jsonRes = sendGetRequest(lineup_url);
		while (lineup_jsonRes.isEmpty()) {
			try {
				Thread.sleep(3000);
				lineup_jsonRes = sendGetRequest(lineup_url);
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		}
		String match_jsonRes = sendGetRequest(match_url);
		while (match_jsonRes.isEmpty()) {
			try {
				Thread.sleep(2000);
				match_jsonRes = sendGetRequest(match_url);
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		}
		JSONObject lineup_jo = new JSONObject(lineup_jsonRes);
		JSONObject match_jo = new JSONObject(match_jsonRes);
		JSONObject statsj = match_jo.getJSONObject("statistics");
		JSONArray teamsj = statsj.getJSONArray("teams");
		JSONArray lineupsj = lineup_jo.getJSONArray("lineups");
		if (teamsj.isNull(0)) {
			return;
		}
		teamsj.forEach(item -> {
			if (item instanceof JSONObject) {
				JSONObject teamj = (JSONObject) item;
				if (teamj.getString("qualifier").equals("home")) {
					Club club = cr.findClubByClubId(teamj.getString("id"));
					JSONObject statj = teamj.getJSONObject("statistics");
					Set<String> check = statj.keySet();
					if (check.contains("ball_possession")) {
						home.setBall_possession(statj.getInt("ball_possession"));						
					}
					if (check.contains("corner_kicks")) {
						home.setCorner_kicks(statj.getInt("corner_kicks"));						
					}
					if (check.contains("free_kicks")) {
						home.setFree_kicks(statj.getInt("free_kicks"));						
					}
					if (check.contains("shots_on_target")) {
						home.setShots_on_target(statj.getInt("shots_on_target"));						
					}
					if (check.contains("fouls")) {
						home.setFouls(statj.getInt("fouls"));						
					}
					if (check.contains("goal_kicks")) {
						home.setGoal_kicks(statj.getInt("goal_kicks"));						
					}
					if (check.contains("yellow_cards")) {
						home.setYEllow_cards(statj.getInt("yellow_cards"));						
					}
					home.setClub_id(club.getClubId());;
					JSONArray playersj = teamj.getJSONArray("players");
					if (playersj.isNull(0)) {
						return;
					}
					playersj.forEach(item1 -> {
						if (item1 instanceof JSONObject) {
							JSONObject playerj = (JSONObject) item1;
							HomeLineUp hlp = new HomeLineUp();
							Player player = pr.findPlayerByPlayerId(playerj.getString("id"));
							hlp.setHome(home);
							Set<String> check1 = playerj.keySet();
							if (check1.contains("substituted_in")) {
								hlp.setSubstitude_in(playerj.getInt("substituted_in"));								
							}
							if (check1.contains("substituted_out")) {
								hlp.setSubstitude_out(playerj.getInt("substituted_out"));								
							}
							if (check1.contains("goals_scored")) {
								hlp.setGoals_scored(playerj.getInt("goals_scored"));								
							}
							if (check1.contains("assists")) {
								hlp.setAssists(playerj.getInt("assists"));								
							}
							if (check1.contains("yellow_cards")) {
								hlp.setYellow_cards(playerj.getInt("yellow_cards"));								
							}
							if (check1.contains("red_cards")) {
								hlp.setRed_cards(playerj.getInt("red_cards"));								
							}
							if (player != null) {
								hlp.setPlayer_id(player.getPlayer_id());								
							}
							lineupsj.forEach(item2 -> {
								if (item2 instanceof JSONObject) {
									JSONObject lineupj = (JSONObject) item2;
									if (lineupj.getString("team").equals("home")) {
										home.setFormation(lineupj.getString("formation"));
										JSONArray slpj = lineupj.getJSONArray("starting_lineup");
										if (player != null) {
											slpj.forEach(item3 -> {
												if (item3 instanceof JSONObject) {
													JSONObject lpj = (JSONObject) item3;
													if (lpj.getString("id").equals(player.getPlayer_id())) {
														hlp.setPosition(lpj.getString("position"));
													}
												}
											});
										}
									}
								}
							});
							hlps.add(hlp);
						}
					});
				} else {
					Club club = cr.findClubByClubId(teamj.getString("id"));
					JSONObject statj = teamj.getJSONObject("statistics");
					Set<String> check = statj.keySet();
					if (check.contains("ball_possession")) {
						away.setBall_possession(statj.getInt("ball_possession"));						
					}
					if (check.contains("corner_kicks")) {
						away.setCorner_kicks(statj.getInt("corner_kicks"));						
					}
					if (check.contains("free_kicks")) {
						away.setFree_kicks(statj.getInt("free_kicks"));						
					}
					if (check.contains("shots_on_target")) {
						away.setShots_on_target(statj.getInt("shots_on_target"));						
					}
					if (check.contains("fouls")) {
						away.setFouls(statj.getInt("fouls"));						
					}
					if (check.contains("goal_kicks")) {
						away.setGoal_kicks(statj.getInt("goal_kicks"));						
					}
					if (check.contains("yellow_cards")) {
						away.setYEllow_cards(statj.getInt("yellow_cards"));						
					}
					away.setClub_id(club.getClubId());;
					JSONArray playersj = teamj.getJSONArray("players");
					playersj.forEach(item1 -> {
						if (item1 instanceof JSONObject) {
							JSONObject playerj = (JSONObject) item1;
							AwayLineUp hlp = new AwayLineUp();
							hlp.setAway(away);
							Player player = pr.findPlayerByPlayerId(playerj.getString("id"));
							Set<String> check1 = playerj.keySet();
							if (check1.contains("substituted_in")) {
								hlp.setSubstitude_in(playerj.getInt("substituted_in"));								
							}
							if (check1.contains("substituted_out")) {
								hlp.setSubstitude_out(playerj.getInt("substituted_out"));								
							}
							if (check1.contains("goals_scored")) {
								hlp.setGoals_scored(playerj.getInt("goals_scored"));								
							}
							if (check1.contains("assists")) {
								hlp.setAssists(playerj.getInt("assists"));								
							}
							if (check1.contains("yellow_cards")) {
								hlp.setYellow_cards(playerj.getInt("yellow_cards"));								
							}
							if (check1.contains("red_cards")) {
								hlp.setRed_cards(playerj.getInt("red_cards"));								
							}
							if (player != null) {
								hlp.setPlayer_id(player.getPlayer_id());								
							}
							lineupsj.forEach(item2 -> {
								if (item2 instanceof JSONObject) {
									JSONObject lineupj = (JSONObject) item2;
									if (lineupj.getString("team").equals("away")) {
										home.setFormation(lineupj.getString("formation"));
										JSONArray slpj = lineupj.getJSONArray("starting_lineup");
										if (player != null) {
											slpj.forEach(item3 -> {
												if (item3 instanceof JSONObject) {
													JSONObject lpj = (JSONObject) item3;
													if (lpj.getString("id").equals(player.getPlayer_id())) {
														hlp.setPosition(lpj.getString("position"));
													}
												}
											});
											
										}
									}
								}
							});
							alps.add(hlp);
						}
					});
				}
			}
		});
	}
	
	public void mapAllMatchData(Season s) {
		String url = SEASON_MATCH_URL.replace("{id}", s.getSeason_id());
		String jsonRes = sendGetRequest(url);
		while (jsonRes.isEmpty()) {
			try {
				Thread.sleep(3000);
				jsonRes = sendGetRequest(url);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		}
		JSONObject jo = new JSONObject(jsonRes);
		JSONArray resultsj = jo.getJSONArray("results");
		if (resultsj.isNull(0)) {
			return;
		}
		resultsj.forEach(item -> {
			if (item instanceof JSONObject) {
				JSONObject resultj = (JSONObject) item;
				JSONObject eventj = resultj.getJSONObject("sport_event");
				JSONObject venuej = eventj.getJSONObject("venue");
				JSONArray competitorsj = eventj.getJSONArray("competitors");
				JSONObject statsj = resultj.getJSONObject("sport_event_status");
				Set<String> check = statsj.keySet();
				Match match = new Match();
				match.setMatch_id(eventj.getString("id"));
				match.setScheduled(eventj.getString("scheduled"));
				match.setVenue(venuej.getString("name"));
				match.setStatus(statsj.getString("status"));
				if (check.contains("winner_id")) {
					match.setWinner_id(statsj.getString("winner_id"));					
				}
				match.setSeason(s);
				Home home = new Home();
				home.setMatch(match);
				Away away = new Away();
				away.setMatch(match);
				ArrayList<HomeLineUp> hlps = new ArrayList<>();
				ArrayList<AwayLineUp> alps = new ArrayList<>();
				mapMatchData(match, home, away, hlps, alps);
				
				mr.save(match);
				hr.save(home);
				ar.save(away);
				hlr.saveAll(hlps);
				alr.saveAll(alps);
				
			}
		});
	}
	
	public void mapSeasonMatch() {
		String[] seasonids = {"sr:season:54571", "sr:season:54533", "sr:season:55917", "sr:season:55737"};
		for (String id: seasonids) {
			Season season = sr.findSeasonBySeasonId(id);
			mapAllMatchData(season);
		}
	}
	
	public void mapSeasonData(String sid, Tournament t) {
		String url = SEASON_URL.replace("{id}", sid);
		String jsonRes = sendGetRequest(url);
		while (jsonRes.isEmpty()) {
			try {
				Thread.sleep(3000);
				jsonRes = sendGetRequest(url);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		}
		JSONObject jo = new JSONObject(jsonRes);
		Season season = new Season();
		String[] check = {"season", "season_coverage_info"};
		if (checkValidJSON(jo, check)) {
			JSONObject seasonj = jo.getJSONObject("season");
			JSONObject seasoninfoj = jo.getJSONObject("season_coverage_info");
			season.setSeason_id(seasonj.getString("id"));
			season.setSname(seasonj.getString("name"));
			season.setStart(Date.valueOf(seasonj.getString("start_date")));
			season.setEnd(Date.valueOf(seasonj.getString("end_date")));
			season.setYear(seasonj.getString("year"));
			season.setScheduled(seasoninfoj.getInt("scheduled"));
			season.setPlayed(seasoninfoj.getInt("played"));
			season.setMax_coverage_level(seasoninfoj.getString("max_coverage_level"));
			season.setMax_covered(seasoninfoj.getInt("max_covered"));
			season.setTournament(t);
			if (season.getYear().equals("18/19")) {
				season.setCurrent(true);
			} else {
				season.setCurrent(false);
			}
		} else {
			JSONObject seasonj = jo.getJSONObject("season");
			season.setSeason_id(seasonj.getString("id"));
			season.setSname(seasonj.getString("name"));
			season.setStart(Date.valueOf(seasonj.getString("start_date")));
			season.setEnd(Date.valueOf(seasonj.getString("end_date")));
			season.setYear(seasonj.getString("year"));
			season.setTournament(t);
			if (season.getYear().equals("18/19")) {
				season.setCurrent(true);
			} else {
				season.setCurrent(false);
			}
		}
		sr.save(season);
	}
	
	
	public void mapAllSeasonsData(Tournament t) {
		String url = SEASON_LIST_URL.replace("{id}", t.getTournament_id());
		String jsonRes = sendGetRequest(url);
		while (jsonRes.isEmpty()) {
			try {
				Thread.sleep(3000);
				jsonRes = sendGetRequest(url);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		}
		JSONObject jo = new JSONObject(jsonRes);
		JSONArray seasonsj = jo.getJSONArray("seasons");
		seasonsj.forEach(item -> {
			if (item instanceof JSONObject) {
				JSONObject seasonj = (JSONObject) item;
				String sid = seasonj.getString("id");
				mapSeasonData(sid, t);
			}
		});
	}
	
	
	
	public void mapClubData(Club club) {
		String url = CLUB_URL.replace("{id}", club.getClubId());
		String jsonRes = sendGetRequest(url);
		while (jsonRes.isEmpty()) {
			try {
				Thread.sleep(3000);
				jsonRes = sendGetRequest(url);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		}
		JSONObject jo = new JSONObject(jsonRes);
		String[] check = {"venue", "jerseys", "manager", "players"};
		if (!checkValidJSON(jo, check)) {
			return;
		}
		JSONObject venuej = jo.getJSONObject("venue");
		JSONArray jerseysj = jo.getJSONArray("jerseys");
		JSONObject coachj = jo.getJSONObject("manager");
		JSONArray playersj = jo.getJSONArray("players");
		
		club.setCity(venuej.getString("city_name"));
		club.setVenueName(venuej.getString("name"));
		club.setVenueCapacity(venuej.getInt("capacity"));
		
		Coach coach = new Coach();
		coach.setPlayer_id(coachj.getString("id"));
		coach.setFullName(coachj.getString("name"));
		coach.setUsername(generateUsername(coachj.getString("name")));
		coach.setPassword(generatePassword(coachj.getString("name")));
		coach.setNationality(coachj.getString("nationality"));
		coach.setClub(club);
		
		ArrayList<Jersey> jerseys = new ArrayList<>();
		jerseysj.forEach(item -> {
			if (item instanceof JSONObject) {
				JSONObject jerseyj = (JSONObject) item;
				Jersey jersey = new Jersey();
				jersey.setType(jerseyj.getString("type"));
				jersey.setBase(jerseyj.getString("base"));
				jersey.setNumber(jerseyj.getString("number"));
				jersey.setClub(club);
				jerseys.add(jersey);
			}
		});
		
		ArrayList<Player> players = new ArrayList<>();
		playersj.forEach(item -> {
			if (item instanceof JSONObject) {
				JSONObject playerj = (JSONObject) item;
				String[] fields = {"jersey_number", "height", "weight"};
				if (checkValidJSON(playerj, fields)) {
					Player player = new Player();
					player.setPlayer_id(playerj.getString("id"));
					player.setFullName(playerj.getString("name"));
					player.setUsername(generateUsername(playerj.getString("name")));
					player.setPassword(generatePassword(playerj.getString("name")));
					player.setType(playerj.getString("type"));
					player.setDob(playerj.getString("date_of_birth"));
					player.setNationality(playerj.getString("nationality"));
					player.setHeight(playerj.getInt("height"));
					player.setWeight(playerj.getInt("weight"));
					player.setJerseyNumber(playerj.getInt("jersey_number"));
					player.setGender(playerj.getString("gender"));
					player.setClub(club);
					players.add(player);
				} 
			}
		});
		
		coachr.save(coach);
		cr.save(club);
		jr.saveAll(jerseys);
		pr.saveAll(players);
		
	}
	
	public void mapAllClubData() {
		ArrayList<Club> clubs = (ArrayList<Club>) cr.findAll();
		for (Club c: clubs) {
			mapClubData(c);
		}
	}
	
	public void mapTournamentData(Faculty f, int id) {
		String url = TOURNAMENT_URL.replace("{id}", Integer.toString(id));
		String jsonRes = sendGetRequest(url);
		while (jsonRes.isEmpty()) {
			try {
				Thread.sleep(3000);
				jsonRes = sendGetRequest(url);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		}
		JSONObject tournament = new JSONObject(jsonRes);
		JSONArray groups = tournament.getJSONArray("groups");
		
		Tournament t = new Tournament();
		t.setTournament_id(tournament.getJSONObject("tournament").getString("id"));
		t.setName(tournament.getJSONObject("tournament").getString("name"));
		t.setFaculty(f);
		
		ArrayList<Club> addclubs = new ArrayList<>();
		groups.forEach(item -> {
			if (item instanceof JSONObject) {
				JSONObject group = (JSONObject) item;
				JSONArray clubs = group.getJSONArray("teams");
				clubs.forEach(o -> {
					if (o instanceof JSONObject) {
						JSONObject clubJson = (JSONObject) o;
						String cid = (String) clubJson.get("id");
						String name = (String) clubJson.get("name");
						String country = (String) clubJson.get("country");
						
						Club current = cr.findClubByClubId(cid);
						if (current == null) {
							Club club = new Club();
							club.setClubId(cid);
							club.setName(name);
							club.setCountry(country);
							club.joinTournament(t);
							addclubs.add(club);
						} else {
							current.joinTournament(t);
							addclubs.add(current);
						}
					
					}
				});
			} 
		});
		
		
		cr.saveAll(addclubs);
		fr.save(f);
		tr.save(t);
		mapAllSeasonsData(t);
	}
	
}
