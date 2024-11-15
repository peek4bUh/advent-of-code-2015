package com.github.peek4bUh.day02;

import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner; // Import the Scanner class to read text files
import java.util.stream.Collectors;

/**
 *
 * @author peek4bUh
 */
public class Day2 {

    public void play() {
        try {
            File myObj = new File("input.txt");
            Scanner myReader = new Scanner(myObj);
            int totalFeetWrappingPaper = 0;
            int l = 0;
            int w = 0;
            int h = 0;

            while (myReader.hasNextLine()) {
                List<Integer> boxDimensions = Arrays.asList(myReader.nextLine().split("x")).stream().map(s -> Integer.parseInt(s)).collect(Collectors.toList());

                l = boxDimensions.get(0);
                w = boxDimensions.get(1);
                h = boxDimensions.get(2);

                int calcHeight = 2 * l * w;
                int calcLenght = 2 * w * h;
                int calcWidth = 2 * h * l;
                int extra = getSmallestArea(boxDimensions);

                totalFeetWrappingPaper += calcHeight + calcLenght + calcWidth + extra;
            }
            myReader.close();
            System.out.println("totalFeetWrappingPaper: " + totalFeetWrappingPaper);
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public int getSmallestArea(List<Integer> myList) {
        Integer n1 = myList.stream().sorted().findFirst().get();
        Integer n2 = myList.stream().sorted().skip(1).findFirst().get();
        return Integer.valueOf(n1) * Integer.valueOf(n2);
    }

}
