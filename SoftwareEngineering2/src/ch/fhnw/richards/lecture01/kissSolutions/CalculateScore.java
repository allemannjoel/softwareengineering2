package ch.fhnw.richards.lecture01.kissSolutions;

import java.util.Random;

/**
 * A minor but important point: the original code uses two sets of variables: counterX and scoreX,
 * but the names are confusing: The score for counter2 is stored in score1. Use good, consistent
 * variable names!
 * 
 * The actual score is a simple calculation. Why use a long case statement, when you can just
 * calculate the answer?
 */
public class CalculateScore {

    public static void main(String[] args) {
        int numObjects = 6;

        Random rand = new Random();
        int[] objects = new int[numObjects];
        for (int i = 0; i < numObjects; i++)
            objects[i] = rand.nextInt(3) + 1;

        int score = calcScore(objects);

        System.out.print("Objects: ");
        for (int i = 0; i < numObjects; i++)
            System.out.print(objects[i] + " ");
        System.out.println("     Score: " + score);
    }

    public static int calcScore(int[] objects) {
        int counter1 = 0;
        int counter2 = 0;
        int counter3 = 0;

        // Count the number of each object - this loop is unchanged
        for (int i = 0; i < objects.length; i++) {
            if (objects[i] == 1) {
                counter1++;
            }
            if (objects[i] == 2) {
                counter2++;
            }
            if (objects[i] == 3) {
                counter3++;
            }
        }

        // Calculate the scores
        int score1 = 0;
        if (counter1 >= 3)
            score1 = counter1 - 2;

        int score2 = 0;
        if (counter2 >= 3)
            score2 = counter2 - 1;

        int score3 = 0;
        if (counter3 >= 3)
            score3 = counter3;

        int totalScore = score1 + score2 + score3;

        return totalScore;
    }
}