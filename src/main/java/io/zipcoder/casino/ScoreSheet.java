//Lauren

package io.zipcoder.casino;

import com.sun.tools.javac.code.Attribute;

import java.util.*;

public class ScoreSheet {

    public enum ROW{
        ACES, TWOS, THREES, FOURS, FIVES, SIXES, THREEOFAKIND, FOUROFAKIND, FULLHOUSE, SMALLSTRAIGHT, LARGESTRAIGHT, YAHTZEE, CHANCE;
    }
    private Map<ROW, Integer> scores = new EnumMap<>(ROW.class);
    private static final int size = ROW.values().length;

    ScoreSheet(){

    }

    public int getTotalScore() {

        int totalScore = 0;
        int upperScore = getUpperScore();
        int lowerScore = getLowerScore();
        if (upperScore >= 63) {
            totalScore += 35;
        }
        totalScore = totalScore + upperScore + lowerScore;
        return totalScore;
    }

    public int getUpperScore() {
        int upperScore = 0;
        try {
            upperScore = getScore(ScoreSheet.ROW.ACES) + getScore(ScoreSheet.ROW.TWOS) + getScore(ScoreSheet.ROW.THREES) + getScore(ScoreSheet.ROW.FOURS) + getScore(ScoreSheet.ROW.FIVES) + getScore(ScoreSheet.ROW.SIXES);
        } catch (NullPointerException e){
            upperScore = 0;
        }
        return upperScore;
    }

    public int getLowerScore() {
        int lowerScore = 0;
        try {
            lowerScore = getScore(ScoreSheet.ROW.THREEOFAKIND) + getScore(ScoreSheet.ROW.FOUROFAKIND) + getScore(ScoreSheet.ROW.FULLHOUSE) + getScore(ScoreSheet.ROW.SMALLSTRAIGHT) + getScore(ScoreSheet.ROW.LARGESTRAIGHT) + getScore(ScoreSheet.ROW.YAHTZEE) + getScore(ScoreSheet.ROW.CHANCE);
        } catch (NullPointerException e){
            lowerScore = 0;
        }
        return lowerScore;
    }

    public String rowToString(ROW row, String description) {
        String rowInfo = String.format("%-35s",description);
        if(getScore(row) != null) {
            rowInfo += "** " + getScore(row) + " **\n";
        } else {
            rowInfo += "** open **\n";
        }
        return rowInfo;
    }

    public String scoreCardToString(){
        String scoreCard = rowToString(ScoreSheet.ROW.ACES, "1. Aces: Totals all Ones") +
        rowToString(ScoreSheet.ROW.TWOS, "2. Twos: Totals all Twos") +
        rowToString(ScoreSheet.ROW.THREES, "3. Threes: Totals all Threes") +
        rowToString(ScoreSheet.ROW.FOURS, "4. Fours: Totals all Fours") +
        rowToString(ScoreSheet.ROW.FIVES, "5. Fives: Totals all Fives") +
        rowToString(ScoreSheet.ROW.SIXES, "6. Sixes: Totals all Sixes") +
        rowToString(ScoreSheet.ROW.THREEOFAKIND, "7. 3 of a Kind") +
        rowToString(ScoreSheet.ROW.FOUROFAKIND, "8. 4 of a Kind") +
        rowToString(ScoreSheet.ROW.FULLHOUSE, "9. Full House") +
        rowToString(ScoreSheet.ROW.SMALLSTRAIGHT, "10. Small Straight: Sequence of 4") +
        rowToString(ScoreSheet.ROW.LARGESTRAIGHT, "11. Large Striaght: Sequence of 5") +
        rowToString(ScoreSheet.ROW.YAHTZEE, "12. Yahtzee: 5 of a Kind") +
        rowToString(ScoreSheet.ROW.CHANCE,  "13. Chance: Sum of Dice");

        return scoreCard;

    }

    public void setRow(ROW row, Dice[] cup){

            ArrayList<Integer> numbers = new ArrayList<>();
            for (Dice d : cup) {
                numbers.add(d.getValue());
            }
            Collections.sort(numbers);

            switch (row) {
                case ACES:
                    scores.put(ROW.ACES, scoreNumbers(numbers, 1));
                    break;
                case TWOS:
                    scores.put(ROW.TWOS, scoreNumbers(numbers, 2));
                    break;
                case THREES:
                    scores.put(ROW.THREES, scoreNumbers(numbers, 3));
                    break;
                case FOURS:
                    scores.put(ROW.FOURS, scoreNumbers(numbers, 4));
                    break;
                case FIVES:
                    scores.put(ROW.FIVES, scoreNumbers(numbers, 5));
                    break;
                case SIXES:
                    scores.put(ROW.SIXES, scoreNumbers(numbers, 6));
                    break;
                case THREEOFAKIND:
                    scoreRow(checkOfaKind(numbers,3), ROW.THREEOFAKIND, scoreTotalDice(numbers));
                    break;
                case FOUROFAKIND:
                    scoreRow(checkOfaKind(numbers, 4), ROW.FOUROFAKIND, scoreTotalDice(numbers));
                    break;
                case FULLHOUSE:
                    scoreRow(checkOfaKind(numbers, 5), ROW.FULLHOUSE, 25);
                    break;
                case SMALLSTRAIGHT:
                    scoreRow(checkSmallStraight(numbers), ROW.SMALLSTRAIGHT, 30);
                    break;
                case LARGESTRAIGHT:
                    scoreRow(checkLargeStraight(numbers), ROW.LARGESTRAIGHT, 40);
                    break;
                case YAHTZEE:
                    scoreRow(checkOfaKind(numbers, 5), ROW.YAHTZEE, 50);
                    break;
                case CHANCE:
                    scores.put(ROW.CHANCE, scoreTotalDice(numbers));
                    break;
            }
        }

        public void scoreRow(boolean check, ROW row, int score) {
            if (check) {
                scores.put(row, score);
            } else {
                scores.put(row, 0);
            }
        }


    public boolean checkFullHouse(ArrayList<Integer> numbers) {

        boolean fullHouse = false;
        boolean checkYahtzee = checkOfaKind(numbers, 5);
        if(checkYahtzee) {
            return true;
        } else {
            boolean check2 = checkOfaKind(numbers, 2);
            boolean check3 = checkOfaKind(numbers, 3);
            fullHouse = (check2 && check3);
        }

        return fullHouse;
    }

    public boolean checkSmallStraight(ArrayList<Integer> numbers) {

        for(int i = 0; i < numbers.size() - 1; i++) {
            if(numbers.get(i) == numbers.get(i+1)) {
                numbers.remove(i);
            }
        }

        while(numbers.size() > 4) {
            if (numbers.get(0) + 1 != numbers.get(1)) {
                numbers.remove(0);
            } else {
                numbers.remove(4);
            }
        }
        if(numbers.size() < 4) return false;

        boolean check = false;
        if(numbers.toString().equals("[1, 2, 3, 4]") ||
                numbers.toString().equals("[2, 3, 4, 5]") ||
                numbers.toString().equals("[3, 4, 5, 6]")) {
            check = true;
        } else {
            check = false;
        }
        return check;
    }

    public boolean checkLargeStraight(ArrayList<Integer> numbers) {

        boolean check = false;
        for(int i = 0; i < numbers.size() - 1; i++) {
            if((numbers.get(i) + 1) == numbers.get(i + 1)) {
                check = true;
            } else {
                check = false;
            }
        }
        return check;
    }

    public boolean checkOfaKind(ArrayList<Integer> numbers, int numb) {

        int[] counts = new int[6];

        for (int i = 0; i < numbers.size(); i++)
            counts[numbers.get(i) - 1]++;

        for (int i: counts) {
            if (i >= numb) return true;
        }
        return false;
    }

    public int scoreNumbers(ArrayList<Integer> numbers, int numb){

        int count = 0;
        for(Integer i : numbers) {
            if(i == numb) {
                count++;
            }
        }
        return count * numb;
    }

    public int scoreTotalDice(ArrayList<Integer> numbers) {

        int score = 0;
        for(Integer i : numbers) {
            score += i;
        }
        return score;
    }

    public static int getSize() {
        return size;
    }

    public Integer getScore(ROW row) {
        return scores.get(row);
    }
}
