package io.zipcoder.casino;

import org.junit.Assert;
import org.junit.Test;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class PrinterTest {

    @Test
    public void testWrongGameEntered(){
        Casino casino = new Casino();
        Console console = new Console();

        casino.getConsole().setScanner(new Scanner(new ByteArrayInputStream("6".getBytes())));
        try {
            casino.chooseGame();
        }catch(NoSuchElementException e){

        }

        Assert.assertEquals(null, casino.getGame());
    }
}
