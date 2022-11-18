import java.util.ArrayList;

public class Match {
    private Game aGame;
    private String HomeTeam;
    private String AwayTeam;
    private double HomeOdds;
    private double AwayOdds;
    private double TieOdds;
    private ArrayList<Bet> aBets;
    private int result;

    public Match(String pHome, String pAway, double pHomeOdds, double pAwayOdds, double pTieOdds, Game pGame){
        aGame = pGame;
        HomeTeam = pHome;
        AwayTeam = pAway;
        HomeOdds = pHomeOdds;
        AwayOdds =pAwayOdds;
        TieOdds = pTieOdds;
        aBets = new ArrayList<>();
    }

    public double getAwayOdds(){
        return AwayOdds;
    }


    public double getHomeOdds(){
        return HomeOdds;
    }

    public double getTieOdds(){
        return TieOdds;
    }

    public void addBet(Bet bet){
        aBets.add(bet);
    }
    public void set_result(int res){
        //to be called after each game is played and results are determined
        result = res;
        update_bets();
    }
    private void update_bets(){
        for(Bet bet : aBets){
            bet.set_result(result);
        }
        aGame.update_ranking();
    }
    public String getHomeTeam(){
        return HomeTeam;
    }


}
