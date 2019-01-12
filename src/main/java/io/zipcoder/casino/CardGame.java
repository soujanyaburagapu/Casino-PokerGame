package io.zipcoder.casino;


import java.util.ArrayList;


public abstract class CardGame {

    private int tablePot;
    private int handSize;
    private int ante;
    private CardPlayer playersTurn;
    private Player winner = null;
    private Player loser = null;
    private ArrayList<CardPlayer> players = new ArrayList<CardPlayer>();
    private Deck deck = new Deck();


    CardGame(int ante){
        this.ante = ante;
    }

    public Deck getDeck() {
        return deck;
    }

    public ArrayList<CardPlayer> getPlayers() {
        return players;
    }

    public void addPlayers(Player... players){
        for(Player player : players){
            CardPlayer cardPlayer = new CardPlayer(player);
            this.players.add(cardPlayer);
        }
    }

    public int getAnte(){
        return ante;
    }

    public int getTablePot() {
        return tablePot;
    }

    public void changeTablePot(int amountPlusMinus) {
        tablePot += amountPlusMinus;
    }

    public Player getWinner() {
        return winner;
    }

    public int getHandSize() {
        return handSize;
    }

    public void setHandSize(int handSize) {
        this.handSize = handSize;
    }

    public CardPlayer getPlayersTurn() {
        return playersTurn;
    }

    public void setPlayersTurn(CardPlayer playersTurn) {
        this.playersTurn = playersTurn;
    }

    public void addNpc(){
        addPlayers(new NPC("Opponant", getAnte()));
    }

    public void chooseStartingPlayer(){
        //loop through the players
        for(int i = 0; i < getPlayers().size(); i ++){
            //if one is not an NPC
            if(!(getPlayers().get(i).getPlayer() instanceof NPC)){
                //set this player to have the current turn
                setPlayersTurn(getPlayers().get(i));
                break;
            }
        }
    }

    public void chooseNextTurn(){
        if(playersTurn != null)
        {
            //if it is the end of the turn circle
            if((players.indexOf(playersTurn) + 1) == players.size()){
                //start again at the starting player
                playersTurn = players.get(0);
                //System.out.println("it is now " + playersTurn.getPlayer().getName() + "'s turn");

            //if it is not the end of the turn circle
            } else {
                playersTurn = players.get(players.indexOf(playersTurn) + 1);
                //System.out.println("it is now " + playersTurn.getPlayer().getName() + "'s turn");
            }
        }
    }

    public Player getLoser() {
        return loser;
    }

    public void setLoser(Player loser) {
        this.loser = loser;
    }
}
