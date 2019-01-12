package io.zipcoder.casino;

public class Player {
    private int initialBalance;
    private int currentBalance;
    private String name;

    Player(String name, int initialBalance){
        this.name = name;
        this.initialBalance = initialBalance;
        this.currentBalance = initialBalance;
    }

    public int getInitialBalance() {
        return initialBalance;
    }

    public int getCurrentBalance() {
        return currentBalance;
    }

    public void changeBalance(int addOrSubtract) {
        this.currentBalance += addOrSubtract;
    }

    public String getName() {
        return name;
    }

}
