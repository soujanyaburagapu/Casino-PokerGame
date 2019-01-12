package io.zipcoder.casino;

import java.util.ArrayList;
import java.util.Scanner;

public class Stud extends CardGame implements Game {
    Console console = new Console();
    private boolean isDealt = false;
    private boolean isCardFlipped = false;
    private int countRound = 0;

    public Stud(int ante) {
        super(ante);
    }
    
    public void playCard(Player player, Card card) {
        card.setVisibility(true);               //CARD isVISIBLE
        Printer.printMessage(player.getName() + " shows a " + card.getName());         //PRINT card name to CONSOLE
    }

    public boolean getIsDealt(){
        return isDealt;
    }

    /**
     * Determine what player wins by looping through player array and then
     * passing each hand to the 'handValue' method
     */
    public CardPlayer determineWinner(ArrayList<CardPlayer> players){
        int max = 0;
        CardPlayer winner = null;

        for(int i = 0; i < players.size(); i ++){
            int playerHandValue = handValue(players.get(i)); // 'handValue' method sets 'max' value of this hand
            if(playerHandValue > max){
                max = playerHandValue;
                winner = players.get(i);
            }
        }
        System.out.println("The winner is " + winner.getPlayer().getName());
        System.out.println(winner.getPlayer().getName() + "\'s hand was: " + winner.getHand().get(0).getName() +
                " - " + winner.getHand().get(1).getName() + " - " + winner.getHand().get(2).getName() + "\n\n" );
        return winner;
    }

    /**
     * Method will return a int value, larger value means strong hand to determine the winning player
     * @param player
     * @return
     */
    public int handValue(CardPlayer player){
        int handValue = 0;
        int card1 = player.getHand().get(0).getCardValue();
        int card2 = player.getHand().get(1).getCardValue();
        int card3 = player.getHand().get(2).getCardValue();

        if (card1 == card2 && card1 == card3) {                         //Three of a Kind
            handValue = threeOfAKindHandValue(card1);
        }
        else if (card1 == card2 || card1 == card3 || card2 == card3){
            handValue = onePairHandValue(card1, card2, card3);
        }
        else {
            handValue = highCardHandValue(card1, card2, card3);
        }
        return handValue;
    }

    /**
     * Helper method for handValue() if had is three-of-a-kind
     * @param card1
     * @return
     */
    public int threeOfAKindHandValue(int card1){
        int handValue;
        handValue = card1 * 1000000;
        return handValue;
    }

    /**
     * Helper method for handValue() if had is one-pair
     * @param card1
     * @param card2
     * @param card3
     * @return
     */
    public int onePairHandValue(int card1, int card2, int card3){
        int handValue = 0;

        if (card1 == card2){
            handValue = (card1 * 10000) + card3;
        }
        else if (card1 == card3){
            handValue = (card1 * 10000) + card2;
        }
        else if (card2 == card3) {
            handValue = (card2 * 10000) + card1;
            return handValue;
        }
        return handValue;
    }

    /**
     * Helper method for handValue() if had is high-card
     * @param card1
     * @param card2
     * @param card3
     * @return
     */
    public int highCardHandValue(int card1, int card2, int card3){
        int handValue = 0;
        // Card1 is Highest
        if (card1 > card2 && card1 > card3 && card2 > card3) {
            handValue = (card1 * 100) + (card2 * 10) + card3;
        }
        else if (card1 > card2 && card1 > card3 && card3 > card2) {
            handValue = (card1 * 100) + (card3 * 10) + card2;
        }
        // Card2 is Highest
        else if (card2 > card1 && card2 > card3 && card1 > card3) {
            handValue = (card2 * 100) + (card1 * 10) + card3;
        }
        else if (card2 > card1 && card2 > card3 && card3 > card1) {
            handValue = (card2 * 100) + (card3 * 10) + card1;
        }
        // Card3 is Highest
        else if (card3 > card1 && card3 > card2 && card1 > card2) {
            handValue = (card3 * 100) + (card1 * 10) + card2;
        }
        else if (card3 > card1 && card3 > card2 && card2 > card1) {
            handValue = (card3 * 100) + (card2 * 10) + card1;
        }
        else {}
        return handValue;
    }

    public void payAnte(ArrayList<CardPlayer> players) {
        for(int i = 0; i < super.getPlayers().size(); i ++)
        {
            //players.get(i).getPlayer().changeBalance(-super.getAnte());
            CardPlayer player = super.getPlayers().get(i);
            player.getPlayer().changeBalance(-super.getAnte());
        }
    }

    public void startGame() {
        setHandSize(3);                 //SET Hand Size for game(3)
        payAnte(this.getPlayers());     //PAY ante (all players)
        deal(this.getPlayers());        //DEALS cards/ hands to each player
        startRound();                   //METHOD called

    }

    /**
     * Game played in this method by calling the 'gameRound' methods
     */
    public void startRound() {
        System.out.println("Welcome to Three Card Stud! Cards are dealt!");
        flipCard();
        gameRound(this.getPlayers(), countRound);
        countRound++;
        flipCard();
        gameRound(this.getPlayers(), countRound);
        countRound++;
        flipCard();
        gameRound(this.getPlayers(), countRound);
        determineWinner(this.getPlayers()); //TEST ADDED ARGUMENT
    }

    /**
     * Plays through rounds that includes flipping cards face up and then betting or folding
     */
    public void gameRound(ArrayList<CardPlayer> players, int countRound){
        for (int j = 0; j < players.size(); j++) {
            playCard(players.get(j).getPlayer(), players.get(j).getHand().get(countRound));      //SHOW-PRINT players first CARD
        }
    }

    /**
     * Deal each player(and dealer) 3 face down cards in turn
     */
    public void deal(ArrayList<CardPlayer> players) {
        for(int i = 0; i < getHandSize(); i ++){
            for (int j = 0; j < players.size(); j++) {
                players.get(j).getHand().add(getDeck().pullCard());
            }
        }
        isDealt = true;
    }

    public boolean flipCard()
    {
        isCardFlipped = false;
        while(!isCardFlipped) {
            String input = "";
            input = console.getCMDFromUser("Type 'FLIP' to play the cards at the top of your pile");
            if (input.equals("flip")) {
                isCardFlipped = true;
            } else {
                System.out.println("Sorry, I don't understand that command.");
            }
        }
        return isCardFlipped;
    }

    public void setScanner(Scanner scanner) {
        this.console.setScanner(scanner);
    }
}

