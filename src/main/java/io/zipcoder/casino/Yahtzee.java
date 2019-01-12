package io.zipcoder.casino;

import java.util.ArrayList;

public class Yahtzee extends DiceGame implements Game, Gamble {

    DicePlayer dicePlayer;
    Console console = new Console();
    int betAmount = 0;
    int totalScore = 0;

    public Yahtzee(Player player) {
        this.dicePlayer = new DicePlayer(player);
        this.totalScore = this.dicePlayer.getScoreSheet().getTotalScore();
    }

    public int getBid() {
        return betAmount;
    }

    public void setBid(int bid) {
        this.betAmount = bid;
    }

    public DicePlayer getDicePlayer() {
        return dicePlayer;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public void startGame() {
        Dice dice1 = new Dice();
        Dice dice2 = new Dice();
        Dice dice3 = new Dice();
        Dice dice4 = new Dice();
        Dice dice5 = new Dice();

        dicePlayer.getCup()[0] = dice1;
        dicePlayer.getCup()[1] = dice2;
        dicePlayer.getCup()[2] = dice3;
        dicePlayer.getCup()[3] = dice4;
        dicePlayer.getCup()[4] = dice5;

    }

    @Override
    public void startRound() {
        for (int i = 0; i < ScoreSheet.getSize(); i++) {
            rollAll();
            for(int j = 0; j < 2; j++) {
                int rollChoice = console.getIntFromUser("Would you like to:\n1. Roll all dice again.\n2. Roll some dice again.\n3. Stop rolling and score.\nNumber of Selection: ");
                String diceToRoll = "";
                if(rollChoice == 2) {
                    Console console2 = new Console();
                    diceToRoll = console2.getLineFromUser("Which numbers would you like to reroll? List the numbers separated by spaces.");
                }
                rollOptions(rollChoice, diceToRoll); }

            boolean validEntry = false;
            ScoreSheet.ROW row = ScoreSheet.ROW.CHANCE;
            while (!validEntry) {
                Printer.printMessage(dicePlayer.getScoreSheet().scoreCardToString());

                int rowChoice = console.getIntFromUser("Which row would you like to apply your turn to on the scoresheet?.\n" +
                        "Remember you can only use each row once!");

                row = selectingRow(rowChoice);
                validEntry = checkEmptyRow(row);
            }
            dicePlayer.getScoreSheet().setRow(row, dicePlayer.getCup());
            Printer.printMessage("\n" + dicePlayer.getScoreSheet().scoreCardToString());
        }

    }

    public void rollAll() {
        for (Dice d : dicePlayer.getCup()) {
            d.roll();
        }
        Printer.printMessage("\nYou rolled:\n" + dicePlayer.cupToString() + "\n");
    }


    public void rollOptions(int choice, String diceToRoll) {

        switch (choice) {
            case 1:
                rollAll();
                break;

            case 2:
                reRoll(diceToRoll);
                break;

            case 3:
                break;
        }
    }

    public void reRoll(String diceToRoll) {

        String[] numbersString = diceToRoll.split(" ");
        ArrayList<Integer> numbers = new ArrayList<>();
        for (String s : numbersString) {
            numbers.add(Integer.parseInt(s));
        }

        for (Integer i : numbers) {
            for (int j = 0; j < 5; j++) {
                if (i == dicePlayer.getCup()[j].getValue()) {
                    dicePlayer.getCup()[j].roll();
                    break;
                }
            }
        }
        Printer.printMessage("\nYou rolled:\n" + dicePlayer.cupToString() + "\n");

    }

    public ScoreSheet.ROW selectingRow(int choice) {

        ScoreSheet.ROW row = ScoreSheet.ROW.CHANCE;

            switch (choice) {
                case 1:
                    row = ScoreSheet.ROW.ACES;
                    break;
                case 2:
                    row = ScoreSheet.ROW.TWOS;
                    break;
                case 3:
                    row = ScoreSheet.ROW.THREES;
                    break;
                case 4:
                    row = ScoreSheet.ROW.FOURS;
                    break;
                case 5:
                    row = ScoreSheet.ROW.FIVES;
                    break;
                case 6:
                    row = ScoreSheet.ROW.SIXES;
                    break;
                case 7:
                    row = ScoreSheet.ROW.THREEOFAKIND;
                    break;
                case 8:
                    row = ScoreSheet.ROW.FOUROFAKIND;
                    break;
                case 9:
                    row = ScoreSheet.ROW.FULLHOUSE;
                    break;
                case 10:
                    row = ScoreSheet.ROW.SMALLSTRAIGHT;
                    break;
                case 11:
                    row = ScoreSheet.ROW.LARGESTRAIGHT;
                    break;
                case 12:
                    row = ScoreSheet.ROW.YAHTZEE;
                    break;
                case 13:
                    row = ScoreSheet.ROW.CHANCE;
                    break;
            }

            return row;

        }

        public boolean checkEmptyRow(ScoreSheet.ROW row) {
            if (dicePlayer.getScoreSheet().getScore(row) == null) {
                return true;
            } else {
                Printer.printMessage("Error, you have already filled that row");
                return false;
            }
        }



    @Override
    public void bet(int betAmount) {
        dicePlayer.getPlayer().changeBalance(betAmount * -1);
    }

    @Override
    public void payout() {
        int score = getTotalScore();
        int payOut = 0;
        if (score == 1575) {
            payOut = getBid() * 100;
        } else if (score > 500) {
            payOut = getBid() * 10;
        } else if (score > 200) {
            payOut = getBid() * 2;
        } else {
            payOut = 0;
        }
        dicePlayer.getPlayer().changeBalance(payOut);
        Printer.printMessage("You won $" + payOut);
    }

}