package io.zipcoder.casino;

import java.util.ArrayList;

public class Printer {

    public static void printMessage(String string) {
        System.out.println(string);
    }

    public static void noMatchingGameName(ArrayList<String> gameNames){

        String games = "";

        for(int i = 0; i < gameNames.size(); i ++){
            games += gameNames.get(i) + " ";
        }
        games = games.trim();

        printMessage("Sorry, there is no game with that name, try one of: " + games);
    }
}
