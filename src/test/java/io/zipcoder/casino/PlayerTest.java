package io.zipcoder.casino;

import org.junit.Assert;
import org.junit.Test;

public class PlayerTest {

    @Test
    public void testInitialBalance() {
        //When
        Player player = new Player("Bob", 500);
        int expected = 500;
        int actual = player.getInitialBalance();

        //Then
        Assert.assertEquals(expected, actual);

    }

    @Test
    public void testGetName() {
        //When
        Player player = new Player("Bob", 500);
        String expected = "Bob";
        String actual = player.getName();

        //Then
        Assert.assertEquals(expected, actual);

    }
}
