package io.zipcoder.casino;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.*;

public class ConsoleTest {

    @Test
    public void testCreateAccont()
    {
        Console console = new Console();
        String name = "Jon \n";
        Integer balance = 100;
        ByteBuffer b = ByteBuffer.allocate(balance);
        byte[] result = b.array();


        byte[] one = result;
        byte[] two = name.getBytes();
        byte[] combined = new byte[one.length + two.length];

        System.arraycopy(one,0,combined,0,one.length);
        System.arraycopy(two,0,combined,one.length,two.length);


        String data = "Jon" + "\nA second line of user input.";
        System.setIn(new ByteArrayInputStream(combined));

        console.setScanner(new Scanner(new ByteArrayInputStream(combined)));

    }

}
