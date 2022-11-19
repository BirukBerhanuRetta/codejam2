package com.example.worldcupbet;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
public class Player {

    private String Username;
    private String Password;
    private double aCoins;


    /**
     * @param pUsername takes in username
     * @param pPassword takes in pPassword
     * @pre neither params will be null
     */
    public Player(String pUsername, String pPassword) {
        assert (pUsername != null && pPassword != null);
        aCoins = 500;
        Username = pUsername;
        Password = pPassword;
    }


    public void CreateBetHomeTeam(Match match, int amount) {
        double odds = match.getHomeOdds();
        double value_to_win = odds * amount;
        aCoins -= amount;
        Bet bet = new Bet(value_to_win, -1, this);
        match.addBet(bet);
        //aGame.update_ranking();
    }

    public void CreateBetAwayTeam(Match match, int amount) {
        double odds = match.getAwayOdds();
        double value_to_win = odds * amount;
        aCoins -= amount;
        Bet bet = new Bet(value_to_win, 1, this);
        match.addBet(bet);
        //aGame.update_ranking();
    }

    public void CreateBetDraw(Match match, float amount) {
        double odds = match.getTieOdds();
        double value_to_win = odds * amount;
        aCoins -= amount;
        Bet bet = new Bet(value_to_win, 0, this);
        match.addBet(bet);
        //aGame.update_ranking();
    }

    public ArrayList<Bet> get_players_bet(Game game){
        ArrayList<Bet> bets = new ArrayList<>();
        for(Match match: game.aMatches){
            for(Bet bet : match.get_bets()){
                if(bet.getPlayer().getUsername() == getUsername()){
                    bets.add(bet);
                }
            }
        }
        return bets;
    }

    public String getUsername() {
        return Username;
    }

    public String getPassword() {
        return Password;
    }

    private void setUsername(String username){
        Username = username;
    }

    private void setPassword(String password){
        Password = password;
    }

    public double getCoins() {
        return aCoins;
    }

    public void addCoins(double add) {
        aCoins += add;
    }


    @Override
    public String toString() {
        return "Player{" +
                ", Username='" + Username +
                '}';
    }

    public static Comparator<Player> compareByCoins() {
        return new Comparator<Player>() {
            @Override
            public int compare(Player o1, Player o2) {
                if (o1.getCoins() > o2.getCoins()) {
                    return 1;
                } else if (o1.getCoins() < o2.getCoins()) {
                    return -1;
                } else return 0;
            }
        };

    }


}