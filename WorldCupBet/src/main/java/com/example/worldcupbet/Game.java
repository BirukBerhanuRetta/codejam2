package com.example.worldcupbet;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class Game {
    ArrayList<Player> aPlayers;
    ArrayList<Match> aMatches;

    ArrayList<String> aRanking;


    public Game() {
        aPlayers = new ArrayList<>();
        aMatches = new ArrayList<>();
        aRanking = new ArrayList<>();


        Match match1 = new Match("Quatar", "Equateur", 3.2, 2.4, 3.1);
        Match match2 = new Match("England", "Iran", 1.33, 10, 4.75);
        Match match3 = new Match("Senegal", "Netherlands", 5.75, 1.60, 3.8);
        Match match4 = new Match("US", "Equateur", 2.4, 3.1, 3.1);

        aMatches.add(match1);
        aMatches.add(match2);
        aMatches.add(match3);
        aMatches.add(match4);
        add_Player(new Player("victor", "henry"));
        add_Player(new Player("Tobias", "Lepoutre"));
        add_Player(new Player("Biruk", "Retta"));

        Player Victor = get_Player("victor");
        Player Tobias = get_Player("Tobias");
        Player Biruk = get_Player("Biruk");
        Match match = get_Match("Quatar","Equateur");

        Victor.CreateBetDraw(match, 200);
        Tobias.CreateBetAwayTeam(match, 10);
        Biruk.CreateBetHomeTeam(match, 69);

        match.set_result(0);

    }

    @PostMapping(path = "/game/add_player")
    public Player add_Player(@RequestBody Player aPlayer) {

        for (Player player : aPlayers) {
           if(aPlayer.getUsername().equals(player.getUsername())) {
                assert !aPlayer.getUsername().equals(player.getUsername());
           }
        }
        aPlayers.add(aPlayer);
        return aPlayer;
    }

    @GetMapping("game/player/{username}")
    public Player get_Player(@PathVariable("username") String username) {
        for (Player player : aPlayers) {
            if (username.equals(player.getUsername())) {
                return player;
            }
        }
        return null;
    }

    @GetMapping("game/player/{username}/coins")
    public double get_Player_coins(@PathVariable("username") String username){
        assert username_exists(username);
        for(Player player: aPlayers){
            if(username.equals(player.getUsername())){
                return player.getCoins();
            }
        }
        return -404;
    }

    @GetMapping("game/player/{username}/bets")
    public ArrayList<Bet> get_Player_bets(@PathVariable("username")String username){
        Player player = get_Player(username);
        return player.get_players_bet(this);
    }

    public boolean username_exists(String username){
        for(Player player : aPlayers){
            if(username.equals(player.getUsername())){
                return true;
            }
        }
        return false;
    }

    public void update_ranking() {
        //Everytime this is called, UI should update
        aPlayers.sort(Player.compareByCoins());
        aRanking = new ArrayList<>();
        for(int index = aPlayers.size()-1 ; index >= 0; index--){
            Player curr = aPlayers.get(index);
            String add = curr.getUsername() +" : " + curr.getCoins();
            aRanking.add(add);
        }
    }

    @GetMapping("game/players")
    public ArrayList<Player> get_players(){
        return aPlayers;
    }

    @GetMapping("game/ranking")
    public ArrayList<String> get_ranking(){
        update_ranking();
        return aRanking;
    }

    @GetMapping("game/matches")
    public ArrayList<Match> get_matches(){
        return aMatches;
    }

    @PostMapping("game/matches/add_match")
    public Match add_match(@RequestBody Match match){
        aMatches.add(match);
        return match;
    }


    @GetMapping("game/matches/{country}")
    public ArrayList<Match> get_Match_of_Country(@PathVariable("country") String country) {
        ArrayList<Match> add = new ArrayList<>();
        for (Match match : aMatches) {
            if (match.getHomeTeam().equals(country) || match.getAwayTeam().equals(country)) {
                add.add(match);
            }
        }
        return add;
    }

    @GetMapping("game/matches/{home}/{away}")
    public Match get_Match(@PathVariable("home") String HomeTeam,@PathVariable("away") String AwayTeam) {
        for (Match match : aMatches) {
            if (match.getHomeTeam().equals(HomeTeam) && match.getAwayTeam().equals(AwayTeam)) {
                return match;
            }
        }
        return null;
    }
    @GetMapping("game/matches/{home}/{away}/bets")
    public ArrayList<Bet> get_Bets_of_Match(@PathVariable("home")String home,@PathVariable("away") String away){


        for(Match match : aMatches){
            if(match.getHomeTeam().equals(home) && match.getAwayTeam().equals(away)){
                return match.get_bets();
            }
        }
        return null;
    }
    @GetMapping("game/matches/{home}/{away}/home_odds")
    public double get_Home_Odds(@PathVariable("home") String HomeTeam,@PathVariable("away") String AwayTeam){
        for (Match match : aMatches) {
            if (match.getHomeTeam().equals(HomeTeam) && match.getAwayTeam().equals(AwayTeam)) {
                return match.getHomeOdds();
            }
        }
        return -404;
    }


    @GetMapping("game/matches/{home}/{away}/away_odds")
    public double get_Away_Odds(@PathVariable("home") String HomeTeam,@PathVariable("away") String AwayTeam){
        for (Match match : aMatches) {
            if (match.getHomeTeam().equals(HomeTeam) && match.getAwayTeam().equals(AwayTeam)) {
                return match.getAwayOdds();
            }
        }
        return -404;
    }

    @GetMapping("game/matches/{home}/{away}/tie_odds")
    public double get_Tie_Odds(@PathVariable("home") String HomeTeam,@PathVariable("away") String AwayTeam){
        for (Match match : aMatches) {
            if (match.getHomeTeam().equals(HomeTeam) && match.getAwayTeam().equals(AwayTeam)) {
                return match.getTieOdds();
            }
        }
        return -404;
    }

    //@PostMapping("game/players/{player}/place_bet")
    //public Bet place_Bet(Player){

    //}


}
