import java.util.ArrayList;

public class Game {
    ArrayList<Player> aPlayers;
    ArrayList<Match> aMatches;




    public Game(){
        aPlayers = new ArrayList<>();
        aMatches = new ArrayList<>();


        Match match1 = new Match("Quatar","Equateur",3.2,2.4,3.1, this);
        Match match2 = new Match("England","Iran",1.33,10,4.75,this);
        Match match3 = new Match("Senegal","Netherlands",5.75,1.60,3.8,this);
        Match match4 = new Match("US","Equateur",2.4,3.1,3.1,this);

        aMatches.add(match1);
        aMatches.add(match2);
        aMatches.add(match3);
        aMatches.add(match4);

    }

    public void add_Player(String username, String password){

        for(Player player : aPlayers){
            if(username.equals(player.getUsername())){
                return;
            }
        }

        Player add = new Player(username,password,this);
        aPlayers.add(add);
    }
    public Player get_PLayer(String username){
        for(Player player : aPlayers){
            if(username.equals(player.getUsername())){
                return player;
            }
        }
        return null;
    }
    public Match get_Match(String HomeTeam){
        for(Match match : aMatches){
            if(match.getHomeTeam().equals(HomeTeam)){
                return match;
            }
        }
        return null;
    }

    public void update_ranking(){
        //Everytime this is called, UI should update
        aPlayers.sort(Player.compareByCoins());
    }



}
