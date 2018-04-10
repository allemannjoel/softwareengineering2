package ch.fhnw.richards.lecture01.kissSolutions;

import java.util.ArrayList;
import java.util.Random;

/**
 * There are three main problems with the original code:
 * 
 * - Useless code: The new array is created, destroyed, then created again
 * 
 * - Misunderstood booleans: There is no need to use an if statement to set a boolean to true or
 * false, when you could just assign the value directly.
 * 
 * - No loops: If you have an array, and need to process all of the elements, use a loop!
 */
public class ReplaceOneValue {

    public static void main(String[] args) {
        // Create initial array holding three integer values from 1 to 10 inclusive.
        ArrayList<Integer> oldArray = new ArrayList<>();
        for (int i = 0; i < 3; i++)
            oldArray.add((int) (Math.random() * 10 + 1));

        // Print the initial array, pick a random value from 0 to 2, and call the method
        printArray("                    Initial values: ", oldArray);
        int randomIndex = (int) (Math.random() * 3);
        ArrayList<Integer> newArray = replaceOneValue(oldArray, randomIndex);

        // Print the initial array and the new array
        printArray("Initial values should be unchanged: ", oldArray);
        printArray("                        New values: ", newArray);

    }

    private static void printArray(String msg, ArrayList<Integer> array) {
        System.out.print(msg);
        for (int i = 0; i < array.size(); i++)
            System.out.print(array.get(i) + ", ");
        System.out.println();
    }

    public static ArrayList<Integer> replaceOneValue(ArrayList<Integer> OldIntarray, int index) {

        final int MAXVALUES = 3;

        // Create a new value for the selected index. Only accept the value if it is different from
        // the current value
        Random rn = new Random();
        int randomValue = OldIntarray.get(index);
        while (randomValue == OldIntarray.get(index)) {
            randomValue = rn.nextInt(10) + 1;
        }

        // Copy the array list
        ArrayList<Integer> NewIntarray = new ArrayList<Integer>(MAXVALUES);
        NewIntarray.addAll(OldIntarray);

        // Replace the selected element
        NewIntarray.set(index, randomValue);

        // Return the result
        return NewIntarray;
    }
}
