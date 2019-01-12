package io.zipcoder.casino;


import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;
import java.util.Random;



public class SlotTest {
    private int betAmount = 10;

    Player player = new Player("Bob", 400);
    private SlotMachine slotmachine = new SlotMachine(betAmount, player);

    @Test
    public void testSlotResult1(){
        String word1="MOUSE";
        String word2="MOUSE";
        String word3="MOUSE";
        //given
        SlotMachine slotmachine = new SlotMachine(betAmount, player);
        slotmachine.setWord1(word1);
        slotmachine.setWord2(word2);
        slotmachine.setWord3(word3);

        slotmachine.slotResult();
        int payout = slotmachine.getPayoutAmt();
        Assert.assertEquals(30,payout);
    }


    @Test
    public void testSlotResult2(){
        String word1="MOUSE";
        String word2="MOUSE";
        String word3="CAT";


        //given
        SlotMachine slotmachine = new SlotMachine(betAmount, player);
        slotmachine.setWord1(word1);
        slotmachine.setWord2(word2);
        slotmachine.setWord3(word3);

        slotmachine.slotResult();
        int payout=slotmachine.getPayoutAmt();
        Assert.assertEquals(20,payout);

    }

    @Test
    public void testSlotResult3(){
        int betAmount=10;
        String word1="MOUSE";
        String word2="RABBIT";
        String word3="CAT";

        //given
        SlotMachine slotmachine = new SlotMachine(betAmount, player);

        slotmachine.setWord1(word1);
        slotmachine.setWord2(word2);
        slotmachine.setWord3(word3);

        slotmachine.slotResult();
        int payout=slotmachine.getPayoutAmt();
        Assert.assertEquals(0,payout);
    }

    @Test
    public void testGenerateWords() {
        //Given
        SlotMachine game = new SlotMachine(100, player);
        String before = game.getOutputword();

        //When
        game.generateWords();
        String after = game.getOutputword();

        //Then
        Assert.assertNotEquals(before, after);


    }

    @Test
    public void testBet() {
        //Given
        SlotMachine game = new SlotMachine(100, player);
        int expected = 300;

        //When
        game.bet(100);
        int actual = game.getPlayer().getCurrentBalance();

        //Then
        Assert.assertEquals(expected, actual);


    }

    @Test
    public void testPayout() {
        //Given
        SlotMachine game = new SlotMachine(100, player);
        int expected = 500;

        //When
        game.bet(100);
        game.setPayoutAmt(200);
        game.payout();
        int actual = game.getPlayer().getCurrentBalance();

        //Then
        Assert.assertEquals(expected, actual);


    }

    @Test
    public void testSetBetAmount(){
        int betAmount=10;
        SlotMachine slotmachine = new SlotMachine(betAmount, player);

        int newBet = 40;

        slotmachine.bet(newBet);

        int actual = slotmachine.betAmount;

        Assert.assertEquals(newBet, actual);

    }

    @Test
    public void testGenerateWords2(){
        Random random = new Random(1);


        String expectedWord1 = "SQUIRREL";
        String expectedWord2 = "FISH";
        String expectedWord3 = "CAT";

        slotmachine.generateWords(random);

        Assert.assertEquals(expectedWord1, slotmachine.word1);

    }

}
