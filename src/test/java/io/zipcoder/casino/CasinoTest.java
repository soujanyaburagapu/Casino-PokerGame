package io.zipcoder.casino;


import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class CasinoTest {

    @Test
    public void testYahtzee(){
        String test = "1";

        Casino casino = new Casino();
        Console console = new Console();

        casino.getConsole().setScanner(new Scanner(new ByteArrayInputStream(test.getBytes())));
        try {
            casino.chooseGame();
        }catch(NoSuchElementException e){

        }

        Assert.assertTrue(casino.getGame() instanceof Yahtzee);
    }

    @Test
    public void testGetStudPlayer(){

        int num = 0;
        String test = "2";
        Casino casino = new Casino();
        Stud stud = new Stud(10);

        casino.getConsole().setScanner(new Scanner(new ByteArrayInputStream(test.getBytes())));
        try {
            num = casino.getStudPlayers();
        }catch(NoSuchElementException e){

        }
        System.out.println(num);
        Assert.assertTrue(num == 2);
    }

    @Test
    public void testWar(){
        Casino casino = new Casino();
        String input = "jon \n 100 \n 2 \n flip";

        casino.getConsole().setScanner(new Scanner(new ByteArrayInputStream(input.getBytes())));
        try {
            casino.getConsole().createAccount();
        }catch(NoSuchElementException e){

        }
    }

    @Test
    public void testAddStudPlayers(){

        Casino casino = new Casino();
        casino.setPlayer(new Player("Lauren", 1000));
        Game studGame = casino.getCardGames()[0];

        String input = "3 \n 2 \n Nick \n 1000 \n";
        String studInput = "flip \n flip \n flip";


        casino.getConsole().setScanner(new Scanner(new ByteArrayInputStream(input.getBytes())));
        ((Stud)studGame).setScanner(new Scanner(new ByteArrayInputStream(studInput.getBytes())));
        try {
            casino.chooseGame();
        }catch(NoSuchElementException e){

        }

        Game game = casino.getGame();

        Assert.assertEquals(2, ((Stud)game).getPlayers().size());
    }
}
