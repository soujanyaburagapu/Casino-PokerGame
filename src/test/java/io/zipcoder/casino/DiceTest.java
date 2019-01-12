package io.zipcoder.casino;

import org.junit.Assert;
import org.junit.Test;

public class DiceTest {

    @Test
    public void getValueTest()
    {
        Dice dice = new Dice();
        dice.roll();
        int actual = dice.getValue();
        boolean expected = true;
        boolean x;
        if (actual<=6 || actual>=1)
        {
            x = true;
        }
        else
            {
                x =false;
            }

        Assert.assertEquals(expected,x);
    }
}
