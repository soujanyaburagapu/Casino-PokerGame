package io.zipcoder.casino;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Console {

    private Scanner scanner = new Scanner(System.in);

    Console(){

    }

    public Player createAccount()
    {
        String name = getCMDFromUser("Hello, what is your name?");
        int balance = getIntFromUser("How much money are you bringing to the table?");
        return new Player(name, balance);
    }

    public int getIntFromUser(String message){
        Printer.printMessage(message);
        try{
            int num = scanner.nextInt();
            return num;
        }catch(InputMismatchException err){
            Printer.printMessage("Please enter a number.");
            scanner.next();
        }
        return -1;
    }

    public String getCMDFromUser(String msg){
        Printer.printMessage(msg);
        String input = scanner.next();
        input.toLowerCase().trim();
        return input;
    }

    public String getLineFromUser(String msg){
        Printer.printMessage(msg);
        String input = scanner.nextLine();
        input.toLowerCase().trim();
        return input;
    }

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }

}
