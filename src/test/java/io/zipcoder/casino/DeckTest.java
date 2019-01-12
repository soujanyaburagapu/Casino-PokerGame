package io.zipcoder.casino;

import org.junit.Assert;
import org.junit.Test;

public class DeckTest {

    @Test
    public void testDeck(){
        //GIVEN
        Deck deck = new Deck();
        //WHEN
        for (int i = 0; i < 52; i++){
            System.out.println(deck.getCard(i).getName());
        }

        //THEN
    }

    @Test
    public void pullCard(){
        //GIVEN
        Deck deck = new Deck();
        //WHEN
        String expected = deck.getCard(0).getName();
        //THEN
        Card testCard = deck.pullCard();
        String actual = testCard.getName();
        Assert.assertEquals(expected, actual);
    }
}
