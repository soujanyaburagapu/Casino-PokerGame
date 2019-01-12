package io.zipcoder.casino;

import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class WarTest {

    @Test
    public void warTest01(){
        War war = new War(10);
        war.addNpc();
        war.addNpc();

        Assert.assertEquals(2, war.getPlayers().size());
    }

    @Test
    public void playCardPileTest(){
        Deck deck = new Deck();
        War war = new War(10);
        Player player = new Player("Jon", 100);
        war.addPlayers(player);
        war.chooseStartingPlayer();
        war.deal();
        war.getPlayers().get(0).setDiscard(deck);

        war.playCardFromPile(false);

        Assert.assertEquals(103, war.getPlayers().get(0).getHand().size());
    }

    @Test
    public void playCardTest() {
        War war = new War(10);
        Player player = new Player("Jon", 100);
        war.addPlayers(player);
        war.chooseStartingPlayer();
        war.playCard(false);

        Assert.assertEquals(war.getLoser(), player);
    }

    @Test
    public void warDealTest(){
        War war = new War(10);
        war.addPlayers(new Player("Jon", 100));
        war.chooseStartingPlayer();
        war.deal();

        Assert.assertTrue(war.getPlayers().get(0).getHand().size() == 52);
    }

    @Test
    public void warEachPlayerPlayCardTest(){
        War war = new War(10);
        war.addPlayers(new Player("Jon", 100), new Player("Jose", 200));

        war.chooseStartingPlayer();
        war.deal();
        war.eachPlayerPlayCard();

        int toalHand = 0;

        for(CardPlayer player : war.getPlayers()){
            toalHand += player.getHand().size();
        }
        Assert.assertTrue(toalHand == 50);
    }

    @Test
    public void testWarStartGame(){
        War war = new War(10);
        Player player1 = new Player("Jose", 100);
        Player player2 = new Player("Lauren", 200);
        war.addPlayers(player1, player2);
        war.chooseStartingPlayer();
        war.deal();

        String input = "flip \n";


            war.setScanner(new Scanner(new ByteArrayInputStream(input.getBytes())));
            try {
                war.startGame();
            } catch (NoSuchElementException e) {

            }


        Assert.assertTrue(war.getPlayersTurn() instanceof CardPlayer);
    }

    @Test
    public void testWarQuit(){
        War war = new War(10);
        Player player1 = new Player("Jose", 100);
        Player player2 = new Player("Lauren", 200);
        war.addPlayers(player1, player2);
        war.chooseStartingPlayer();
        CardPlayer startPlayer = war.getPlayersTurn();
        war.deal();

        String input = "quit \n";


        war.setScanner(new Scanner(new ByteArrayInputStream(input.getBytes())));
        try {
            war.startGame();
        } catch (NoSuchElementException e) {

        }


       Assert.assertTrue(war.getPlayersTurn() == startPlayer);
    }

    @Test
    public void testWarMethod(){
        War war = new War(10);
        Player player1 = new Player("Jose", 100);
        Player player2 = new Player("Lauren", 200);
        war.addPlayers(player1, player2);
        war.chooseStartingPlayer();
        CardPlayer startPlayer = war.getPlayersTurn();

        ArrayList<Card> hand1 = new ArrayList<>();
        hand1.add(new Card(Card.CardValue.FOUR, Card.Suit.HEARTS));
        hand1.add(new Card(Card.CardValue.FOUR, Card.Suit.HEARTS));
        hand1.add(new Card(Card.CardValue.THREE, Card.Suit.HEARTS));
        hand1.add(new Card(Card.CardValue.TWO, Card.Suit.HEARTS));
        hand1.add(new Card(Card.CardValue.ACE, Card.Suit.HEARTS));

        ArrayList<Card> hand2 = new ArrayList<>();
        hand2.add(new Card(Card.CardValue.FOUR, Card.Suit.HEARTS));
        hand2.add(new Card(Card.CardValue.KING, Card.Suit.HEARTS));
        hand2.add(new Card(Card.CardValue.JACK, Card.Suit.HEARTS));
        hand2.add(new Card(Card.CardValue.QUEEN, Card.Suit.HEARTS));
        hand2.add(new Card(Card.CardValue.KING, Card.Suit.HEARTS));

        String input = "flip \n";

        war.getPlayers().get(0).setHand(hand1);
        war.getPlayers().get(1).setHand(hand2);

        war.setScanner(new Scanner(new ByteArrayInputStream(input.getBytes())));
        try {
            war.startRound();
        } catch (NoSuchElementException e) {

        }

        Assert.assertTrue(war.getPlayers().get(0).getHand().size() == 1);
    }

}
