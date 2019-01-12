package io.zipcoder.casino;

import java.util.ArrayList;

public class CardPlayer {
    private Player player;
    private ArrayList<Card> hand = new ArrayList<>();
    private ArrayList<Card> discard = new ArrayList<>();
    private Card playedCard = null;


    public CardPlayer(Player player){
        this.player = player;
    }

    public ArrayList<Card> getHand(){
        return hand;
    }

    public void setHand(ArrayList<Card> hand) {
        this.hand = hand;
    }

    public Player getPlayer() {
        return player;
    }

    public Card getPlayedCard() {
        return playedCard;
    }

    public void setPlayedCard(Card playedCard) {
        this.playedCard = playedCard;
    }

    public void addDiscard(ArrayList<Card> cards) {
        this.discard.addAll(cards);
    }
    public ArrayList<Card> getDiscard() {
        return discard;
    }
    public void setDiscard(ArrayList<Card> discard) {
        this.discard = discard;
    }
}
