package com.github.peek4bUh.day02;

import com.github.peek4bUh.aoc2015.BasePuzzle;
import com.github.peek4bUh.utils.ReadFile;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * --- Day 2: I Was Told There Would Be No Math ---
 *
 * The elves are running low on wrapping paper, and so they need to submit an
 * order for more. They have a list of the dimensions (length l, width w, and
 * height h) of each present, and only want to order exactly as much as they
 * need.
 *
 * Fortunately, every present is a box (a perfect right rectangular prism),
 * which makes calculating the required wrapping paper for each gift a little
 * easier: find the surface area of the box, which is 2*l*w + 2*w*h + 2*h*l. The
 * elves also need a little extra paper for each present: the area of the
 * smallest side.
 *
 * For example:
 *
 * A present with dimensions 2x3x4 requires 2*6 + 2*12 + 2*8 = 52 square feet of
 * wrapping paper plus 6 square feet of slack, for a total of 58 square feet. A
 * present with dimensions 1x1x10 requires 2*1 + 2*10 + 2*10 = 42 square feet of
 * wrapping paper plus 1 square foot of slack, for a total of 43 square feet.
 *
 * All numbers in the elves' list are in feet. How many total square feet of
 * wrapping paper should they order?
 *
 * @author peek4bUh
 */
public class Puzzle1 implements BasePuzzle {

  @Override
  public void play() {
    List<String> rawBoxesDimensions = ReadFile.getResourceFileAsList("input-day02.txt");
    List<List<Integer>> boxesDimensions = rawBoxesDimensions
            .stream()
            .map(str -> Arrays.stream(str.split("x"))
            .map(n -> Integer.valueOf(n))
            .collect(Collectors.toList()))
            .collect(Collectors.toList());
    int totalFeetWrappingPaper = boxesDimensions.stream()
            .mapToInt(boxDimensions -> {
              int l = boxDimensions.get(0);
              int w = boxDimensions.get(1);
              int h = boxDimensions.get(2);
              int extra = getSmallestArea(boxDimensions);

              return (2 * l * w) + (2 * w * h) + (2 * h * l) + extra;
            })
            .sum();

    System.out.println("Solution: " + totalFeetWrappingPaper);
  }

  private int getSmallestArea(List<Integer> myList) {
    Integer n1 = myList.stream().sorted().findFirst().get();
    Integer n2 = myList.stream().sorted().skip(1).findFirst().get();
    return n1 * n2;
  }

}
