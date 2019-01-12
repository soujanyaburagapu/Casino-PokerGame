package io.zipcoder.casino;

import org.junit.Assert;
import org.junit.Test;

public class CardTest {

    @Test
    public void testCreateCard(){
        Card card = new Card(Card.CardValue.TWO, Card.Suit.CLUBS);
        String expected = "TWO of CLUBS";
        String actual = card.getName();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testGetSuit(){
        Card card = new Card(Card.CardValue.THREE, Card.Suit.DIAMONDS);
        Card.Suit expected = Card.Suit.DIAMONDS;
        Card.Suit actual = card.getSuit();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testGetCardValue(){
        Card card = new Card(Card.CardValue.ACE, Card.Suit.HEARTS);
        int expected = 14;
        int actual = card.getCardValue();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testVisibility(){
        Card card = new Card(Card.CardValue.KING, Card.Suit.SPADES);
        boolean before = card.isVisible();
        card.setVisibility(true);
        boolean after = card.isVisible();
        Assert.assertNotEquals(before, after);
    }

}
