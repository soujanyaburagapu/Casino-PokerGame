package io.zipcoder.casino;

import java.util.HashMap;

public class DicePlayer {
    private Player player;
    Dice[] cup = new Dice[5];
    ScoreSheet scoreSheet = new ScoreSheet();

    public DicePlayer(Player player){
        this.player = player;
    }

    public ScoreSheet getScoreSheet() {
        return scoreSheet;
    }

    public Dice[] getCup() {
        return cup;
    }

    public String cupToString() {

        String cupString = "";

        for(Dice d : cup) {
            cupString += (d.getValue() + " ");
        }
        return cupString;
    }

    public Player getPlayer() {
        return player;
    }

    public String balanceAtEnd() {
        return "Your total balance is now: $" + getPlayer().getCurrentBalance();
    }
}


