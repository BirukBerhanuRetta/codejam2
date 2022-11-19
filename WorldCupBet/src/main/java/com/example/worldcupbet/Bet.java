package com.example.worldcupbet;

public class Bet {
    private double aAmoutToWin;
    private int aTeam;
    private int result;
    private Player aPlayer;

    public Bet(double pAmountToWin, int pTeam, Player pPlayer) {
        aAmoutToWin = pAmountToWin;
        aTeam = pTeam;
        aPlayer = pPlayer;
    }

    public void set_result(int pResult) {
        result = pResult;
        updatePlayer();
    }

    private void updatePlayer() {
        if (result == aTeam) {
            aPlayer.addCoins(aAmoutToWin);
        }
    }

    public double getaAmoutToWin(){return aAmoutToWin;}

    public int getaTeam(){return aTeam;}

    public int getResult(){return result;}

    public Player getPlayer(){return aPlayer;}



}
