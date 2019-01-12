package io.zipcoder.casino;

public class Card {
    private Suit suit;
    private CardValue cardValue;
    private boolean isVisible;
    private String name;

    public Card (CardValue cardValue, Suit suit)
    {
        this.cardValue = cardValue;
        this.suit = suit;
        this.isVisible = false;
        this.name = cardValue + " of " + suit;
    }


    public String getName() {
        return name;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisibility(boolean visibility){
        isVisible = visibility;
    }

    public Suit getSuit()
    {
        return suit;
    }

    public int getCardValue()
    {
        return cardValue.getCardValue();
    }

    public enum CardValue
    {
        TWO(2),
        THREE(3),
        FOUR(4),
        FIVE(5),
        SIX(6),
        SEVEN(7),
        EIGHT(8),
        NINE(9),
        TEN(10),
        JACK(11),
        QUEEN(12),
        KING(13),
        ACE(14);

        private int cardValue;

        CardValue (int cardValue)
        {
            this.cardValue = cardValue;
        }

        public int getCardValue() {
            return cardValue;
        }
    }

    public enum Suit
    {
        HEARTS("HEARTS"),
        SPADES("SPADES"),
        CLUBS("CLUBS"),
        DIAMONDS("DIAMONDS");

        private String suitValue;

        Suit (String suitValue)
        {
            this.suitValue = suitValue;
        }}
}
