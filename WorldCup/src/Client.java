public class Client {
    public static void main(String[] args) {
        Game MyGame = new Game();
        MyGame.add_Player("victor","henry");
        MyGame.add_Player("Tobias", "Lepoutre");
        MyGame.add_Player("Biruk","Retta");

        Player Victor = MyGame.get_PLayer("victor");
        Player Tobias = MyGame.get_PLayer("Tobias");
        Player Biruk = MyGame.get_PLayer("Biruk");
        Match match = MyGame.get_Match("Quatar");

        Victor.CreateBetDraw(match, 200);
        Tobias.CreateBetAwayTeam(match,10);
        Biruk.CreateBetHomeTeam(match, 69);

        for(Player play : MyGame.aPlayers){
            System.out.println(play.getUsername());
        }

        match.set_result(0);

        for(Player play : MyGame.aPlayers){
            System.out.println(play.getUsername());
        }





    }
}
