package io.zipcoder.casino;

import java.util.Random;

public class SlotMachine implements Game, Gamble {
    protected int betAmount;
    private int payoutAmt=0;
    String word="";
    String outputword="";

    String word1="";
    String word2="";
    String word3="";
    double totalBet=0;
    Player player;

    public SlotMachine(int betAmount, Player player) {

        this.betAmount = betAmount;
        this.player = player;
    }

    @Override
    public void bet(int betAmount) {

        this.betAmount= betAmount;
        player.changeBalance(-betAmount);

    }

    public void payout(){
        getPlayer().changeBalance(payoutAmt);
        Printer.printMessage("Your payout amount for slot machine is: $" + payoutAmt + "\n");

    }

    @Override
    public void startGame() {
        Printer.printMessage("You are all set to play a new slot game..zrrr..! \n");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Printer.printMessage("Your slot is in progress" + "\n");

        startRound();
    }

    public void generateWords() {
        Random rand = new Random();

        generateWords(rand);

    }

    protected void generateWords(Random rand) {
        for (int i = 1; i <= 3; i++) {
            int randnum = rand.nextInt(6);


            if (randnum == 0) {
                word = "DOG";

            } else if (randnum == 1) {
                word = "CAT";

            } else if (randnum == 2) {
                word = "RABBIT";

            } else if (randnum == 3) {
                word = "SQUIRREL";

            } else if (randnum == 4) {
                word = "FISH";

            } else if (randnum == 5) {
                word = "MOUSE";

            }

            if (i == 1) {
                word1 = word;
            } else if (i == 2) {
                word2 = word;
            } else if (i == 3) {
                word3 = word;
            }
        }

        outputword= "[ " + word1+ " ]" + "   " + "[ " + word2 + " ]" + "   "+ "[ " + word3 + " ]" + "\n" ;

    }

    public void slotResult()
    {
            if(((!word1.equals(word2)) )&& ((!word1.equals(word3))) && ((!word2.equals(word3)))){

                outputword= outputword + "\n"+" You have won $0";
                setPayoutAmt(0);
            }
            else if( (word1.equals(word2) && (!word1.equals(word3))) || ((word1.equals(word3)) && (!word1.equals(word2))) || ((word2.equals(word3)) && (!word2.equals(word1)))){

                outputword= outputword + "\n" +" You have won $" + (betAmount*2);
                setPayoutAmt(betAmount*2);
            }


            else if(word1.equals(word2) && word1.equals(word3)) {
                outputword= outputword + "\n" + "You have won $" + (betAmount*3);
                setPayoutAmt(betAmount*3);
            }

            Printer.printMessage(( outputword + "\n" ));

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void startRound() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        generateWords();
    }

    public int getPayoutAmt() {

        return payoutAmt;
    }

    public void setPayoutAmt(int payoutAmt) {
        this.payoutAmt = payoutAmt;
    }

    public void setWord1(String word1) {

        this.word1 = word1;
    }

    public void setWord2(String word2) {
        this.word2 = word2;
    }

    public void setWord3(String word3) {
        this.word3 = word3;
    }

    public String getOutputword() {
        return outputword;
    }

    public Player getPlayer() {
        return player;
    }

}

