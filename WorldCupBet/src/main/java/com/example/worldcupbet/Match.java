package com.example.worldcupbet;
import java.util.ArrayList;

public class Match {
    private String HomeTeam;
    private String AwayTeam;
    private double HomeOdds;
    private double AwayOdds;
    private double TieOdds;
    private ArrayList<Bet> aBets;
    private int result;

    public Match(String pHome, String pAway, double pHomeOdds, double pAwayOdds, double pTieOdds) {

        HomeTeam = pHome;
        AwayTeam = pAway;
        HomeOdds = pHomeOdds;
        AwayOdds = pAwayOdds;
        TieOdds = pTieOdds;
        aBets = new ArrayList<>();
    }

    public double getAwayOdds() {
        return AwayOdds;
    }

    public void setAwayOdds(double set) {AwayOdds = set;}

    public void setHomeOdds(double set){HomeOdds = set;}

    public void setTieOdds(double set){TieOdds = set;}

    public void setHomeTeam(String set){HomeTeam = set;}

    public void setAwayTeam(String set){AwayTeam = set;};


    public double getHomeOdds() {
        return HomeOdds;
    }

    public double getTieOdds() {
        return TieOdds;
    }

    public void addBet(Bet bet) {
        aBets.add(bet);
    }

    public void set_result(int res) {
        //to be called after each game is played and results are determined
        result = res;
        update_bets();
    }

    private void update_bets() {
        for (Bet bet : aBets) {
            bet.set_result(result);
        }
    }

    public String getHomeTeam() {
        return HomeTeam;
    }
    public String getAwayTeam(){
        return AwayTeam;
    }
    public ArrayList<Bet> get_bets(){return aBets;};


}
