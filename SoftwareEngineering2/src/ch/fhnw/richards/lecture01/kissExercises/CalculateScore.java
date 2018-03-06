package ch.fhnw.richards.lecture01.kissExercises;

import java.util.Random;

/**
 * In a game, you collect six objects, which are numbered 1, 2 and 3. The number of objects you
 * collect gives you points, based on which object it is, and how many you have.
 * 
 * In this example, the method calcScore() takes an array of integers (1, 2, or 3) and calculates
 * the total score. The main method creates a random array, calls the method and prints the score.
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

        int score = 0;
        int score1 = 0;
        int score2 = 0;

        switch (counter1) {
        case 3:
            score = 1;
            break;

        case 4:
            score = 2;
            break;

        case 5:
            score = 3;
            break;

        case 6:
            score = 4;
            break;
        }

        switch (counter2) {
        case 3:
            score1 = 2;
            break;

        case 4:
            score1 = 3;
            break;

        case 5:
            score1 = 4;
            break;

        case 6:
            score1 = 5;
            break;
        }

        switch (counter3) {
        case 3:
            score2 = 3;
            break;

        case 4:
            score2 = 4;
            break;

        case 5:
            score2 = 5;
            break;

        case 6:
            score2 = 6;
            break;
        }

        int totalScore = score + score1 + score2;

        return totalScore;
    }
}
