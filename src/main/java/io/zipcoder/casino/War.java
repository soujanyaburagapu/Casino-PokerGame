package io.zipcoder.casino;

import java.util.*;

public class War extends CardGame implements Gamble, Game {

    private ArrayList<Card> tableCards = new ArrayList<Card>();
    private ArrayList<CardPlayer> warMembers = new ArrayList<CardPlayer>();
    private Console console = new Console();

    War(int ante) { super(ante); }

    public void playCard(boolean cardFace){
        if(super.getPlayersTurn().getHand().size() > 0) {
            playCardInHand(cardFace);
        } else if(super.getPlayersTurn().getHand().size() == 0 && super.getPlayersTurn().getDiscard().size() > 0) {
            playCardFromPile(cardFace);
        } else if(super.getPlayersTurn().getHand().size() == 0 && super.getPlayersTurn().getDiscard().size() == 0){
            super.setLoser(super.getPlayersTurn().getPlayer());
            Printer.printMessage(super.getPlayersTurn().getPlayer().getName() + " has lost the match!");
        }
    }

    public void playCardInHand(boolean cardFace){
        Card card = getCardFromHand(cardFace);
        if(cardFace) {
            Printer.printMessage(super.getPlayersTurn().getPlayer().getName() + " has played a " + card.getName() + " and has " + super.getPlayersTurn().getHand().size() + " cards left.");
        } else {
            Printer.printMessage(super.getPlayersTurn().getPlayer().getName() + " has played a card face down.");
        }
    }

    public Card getCardFromHand(boolean cardFace){
        Card card = super.getPlayersTurn().getHand().get(0);
        card.setVisibility(cardFace);
        tableCards.add(card);
        super.getPlayersTurn().setPlayedCard(card);
        super.getPlayersTurn().getHand().remove(card);
        return card;
    }

    public void playCardFromPile(boolean cardFace){
        Printer.printMessage(super.getPlayersTurn().getPlayer().getName() + " ran out of cards and picked up their discard pile.");
        super.getPlayersTurn().getHand().addAll(super.getPlayersTurn().getDiscard());
        super.getPlayersTurn().setDiscard(new ArrayList<Card>());
        playCard(cardFace);
    }

    public CardPlayer warMethod(){
        Printer.printMessage("War!");
        int max = 0;
        CardPlayer winner = null;
        for(int i = 0; i < warMembers.size(); i ++){
            for(int m = 0; m < 2; m ++){
                playCard(false);
            }
            playCard(true);
            super.chooseNextTurn();
        }
        winner = determineWinner(warMembers);
        warMembers = new ArrayList<>();
        return winner;
    }

    public CardPlayer determineWinner(ArrayList<CardPlayer> playerList){
        int max = 0;
        CardPlayer winner = null;
        boolean war = false;
        for(int i = 0; i < playerList.size(); i ++){
            CardPlayer player = playerList.get(i);
            if(player.getPlayedCard().getCardValue() > max)
            {
                max = player.getPlayedCard().getCardValue();
                winner = player;
                war = false;
            }  else if (player.getPlayedCard().getCardValue() == max){
                warMembers.add(player);
                war = true;
            }
        }
        return checkWar(war, winner);
    }

    public CardPlayer checkWar(boolean war, CardPlayer winner){
        if(war)
        {
            warMembers.add(winner);
            winner = warMethod();
            return winner;
        } else if(!war)
        {
            Printer.printMessage("The winner is " + winner.getPlayer().getName());
            return winner;
        }
        return null;
    }

    public void bet(int betAmount) { super.changeTablePot(betAmount); }

    public void payout() {
        if(super.getWinner() != null) { super.getWinner().changeBalance(super.getTablePot()); }
    }

    public void payAnte() {
        for(int i = 0; i < super.getPlayers().size(); i ++)
        {
            CardPlayer player = super.getPlayers().get(i);
            player.getPlayer().changeBalance(super.getAnte() * -1);
        }
    }


    public void startGame(){
        Printer.printMessage("Welcome to War!");
        super.chooseStartingPlayer();
        payAnte();
        deal();
        startRound();
    }



    public void startRound() {
        while(super.getLoser() == null) {
            String input = console.getCMDFromUser("Type 'FLIP' to play the card at the top of your pile");
            if (input.equals("flip")) {
                eachPlayerPlayCard();
                CardPlayer winner = determineWinner(super.getPlayers());
                Printer.printMessage(winner.getPlayer().getName() + " has been rewarded " + tableCards.size() + " cards.");
                winner.addDiscard(tableCards);
                tableCards = new ArrayList<Card>();
            } else {
                Printer.printMessage("Sorry, I don't understand that command.");
            }
        }
    }

    public void eachPlayerPlayCard(){
        for (CardPlayer player : super.getPlayers()) {
            playCard(true);
            super.chooseNextTurn();
        }
    }

    public void deal() {
        while(super.getDeck().size() != 0){
            for(int i = 0; i < super.getPlayers().size(); i ++)
            {
                Card card = super.getDeck().get(super.getDeck().size() - 1);
                CardPlayer player = super.getPlayers().get(i);
                player.getHand().add(card);
                super.getDeck().remove(card);
            }
        }
        Printer.printMessage(super.getPlayersTurn().getPlayer().getName() + "has: " + super.getPlayersTurn().getHand().size() + " cards.");
    }

    public void setScanner(Scanner scanner) {
        this.console.setScanner(scanner);
    }

    public ArrayList<CardPlayer> getWarMembers() {
        return warMembers;
    }
}