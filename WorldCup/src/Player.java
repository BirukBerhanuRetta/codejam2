import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

public class Player {

    private Game aGame;
    private String Username;
    private String Password;
    private double aCoins;
    private ArrayList<Bet> myBets;

    /**
     * @param pUsername takes in username
     * @param pPassword takes in pPassword
     * @pre neither params will be null
     */
    public Player(String pUsername, String pPassword, Game pGame) {
        assert (pUsername != null && pPassword != null);
        aCoins=500;
        Username = pUsername;
        Password = pPassword;
        aGame = pGame;
        myBets = new ArrayList<>();
    }


    public void CreateBetHomeTeam(Match match, int amount) {
        double odds = match.getHomeOdds();
        double value_to_win = odds * amount;
        aCoins -= amount;
        Bet bet = new Bet(value_to_win,-1,this);
        match.addBet(bet);
        myBets.add(bet);
        aGame.update_ranking();
    }

    public void CreateBetAwayTeam(Match match, int amount) {
        double odds = match.getAwayOdds();
        double value_to_win = odds * amount;
        aCoins -= amount;
        Bet bet = new Bet(value_to_win,1,this);
        match.addBet(bet);
        myBets.add(bet);
        aGame.update_ranking();
    }

    public void CreateBetDraw(Match match, float amount) {
        double odds = match.getTieOdds();
        double value_to_win = odds * amount;
        aCoins -= amount;
        Bet bet = new Bet(value_to_win,0,this);
        match.addBet(bet);
        myBets.add(bet);
        aGame.update_ranking();
    }
    public String getUsername(){
        return Username;
    }
    public String getPassword(){
        return Password;
    }

    public double getCoins(){
        return aCoins;
    }
    public void addCoins(double add){
        aCoins+=add;
    }
    public ArrayList<Bet> getMyBets(){
        return myBets;
    }

    @Override
    public String toString() {
        return "Player{" +
                "aGame=" + aGame +
                ", Username='" + Username + '\'' +
                ", Password='" + Password + '\'' +
                ", aCoins=" + aCoins +
                ", myBets=" + myBets +
                '}';
    }

    public static Comparator<Player> compareByCoins(){
        return new Comparator<Player>() {
            @Override
            public int compare(Player o1, Player o2) {
                if(o1.getCoins() > o2.getCoins()){
                    return 1;
                }
                else if(o1.getCoins() < o2.getCoins()){
                    return -1;
                }
                else return 0;
            }
        };

    }



}