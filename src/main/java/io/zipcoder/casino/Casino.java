package io.zipcoder.casino;


import java.util.ArrayList;

public class Casino {
    public boolean running = true;
    private ArrayList<String> gameLib = new ArrayList<>();
    private Game[] cardGames = {new Stud(10), new War(10)};
    private ArrayList<Player> players = new ArrayList<>();
    private Console console = new Console();
    private Game game = null;
    private Player player;

    public Casino(){
        gameLib.add("yahtzee");
        gameLib.add("war");
        gameLib.add("stud");
        gameLib.add("slot");
        gameLib.add("quit");
    }

    public static void main(String[] args){
        Casino casino = new Casino();
        //get a player
        casino.setPlayer(casino.getConsole().createAccount());
        //player picks game
        casino.chooseGame();
    }

    public String getGameListing(){
        int i=1;
        String list = "";
        for (String s: gameLib) {
            list += "Enter "+i+ " for : " + s + "\n";
            i++;
        }
        return list;
    }

    public void chooseGame()
    {
        while(running) {

            Printer.printMessage("Please choose a game to play!");
            Printer.printMessage(getGameListing());

            int command = console.getIntFromUser("Please choose a game to play by entering the number next to the game.");

            switch (command) {

                case 2:
                    Game war = cardGames[1];
                    ((War) war).addPlayers(player);
                    ((War) war).addNpc();
                    game = war;
                    war.startGame();
                    break;

                case 3:
                    Game stud = cardGames[0];
                    ((Stud) stud).addPlayers(player);
                    addStudPlayers(stud);
                    game = stud;
                    stud.startGame();
                    break;

                case 4:
                    int slotBet1= console.getIntFromUser("Enter the amount you want to bet on Slot");
                    if ((slotBet1 < 0) || (slotBet1 >player.getCurrentBalance()) )
                    {
                        Printer.printMessage("Invalid bet amount entered, you don't have that much money!");
                        break;
                    }
                    Game slot= new SlotMachine(slotBet1, player);
                    game = slot;
                    slot.startGame();
                    ((SlotMachine) slot).slotResult();
                    ((SlotMachine) slot).payout();
                    break;

                case 1:
                    Game yahtzee = new Yahtzee(player);
                    game = yahtzee;
                    ((Yahtzee) yahtzee).startGame();
                    int betAmount = console.getIntFromUser("How much would you like to bet on this game?");
                    ((Yahtzee) yahtzee).setBid(betAmount);
                    ((Yahtzee) yahtzee).bet(betAmount);
                    yahtzee.startRound();
                    Printer.printMessage("You scored " + ((Yahtzee) yahtzee).getDicePlayer().getScoreSheet().getTotalScore() + " points.");
                    ((Yahtzee) yahtzee).payout();
                    Printer.printMessage(((Yahtzee) yahtzee).getDicePlayer().balanceAtEnd() + "\n");
                    break;

                case 5:
                    running = false;
                    Printer.printMessage("Thanks for the money chump!");
                    break;

                default:
                    Printer.noMatchingGameName(gameLib);
                    break;
            }
        }
    }

    public Console getConsole() {
        return console;
    }

    public int getStudPlayers() {
        int numOfStudPlayers = 0;

        while (numOfStudPlayers <= 1) {

            numOfStudPlayers = console.getIntFromUser("How many players are there?");
        }
        return numOfStudPlayers;
    }

    public void addStudPlayers(Game game){
        int numPlayers = getStudPlayers();

        for(int i = 1; i < numPlayers; i ++) {
            Player player = console.createAccount();
            ((Stud) game).addPlayers(player);
        }
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Game getGame() {
        return game;
    }

    public Game[] getCardGames() {
        return cardGames;
    }

}
