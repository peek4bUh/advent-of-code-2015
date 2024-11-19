package com.github.peek4bUh.day03;

import com.github.peek4bUh.aoc2015.BasePuzzle;
import com.github.peek4bUh.utils.ReadFile;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * --- Day 3: Perfectly Spherical Houses in a Vacuum ---
 *
 * Santa is delivering presents to an infinite two-dimensional grid of houses.
 *
 * He begins by delivering a present to the house at his starting location, and
 * then an elf at the North Pole calls him via radio and tells him where to move
 * next. Moves are always exactly one house to the north (^), south (v), east
 * (>), or west {@literal (<)}. After each move, he delivers another present to
 * the house at his new location.
 *
 * However, the elf back at the north pole has had a little too much eggnog, and
 * so his directions are a little off, and Santa ends up visiting some houses
 * more than once. How many houses receive at least one present?
 *
 * For example:
 *
 * > delivers presents to 2 houses: one at the starting location, and one to the
 * east. ^>v{@literal (<)} delivers presents to 4 houses in a square, including
 * twice to the house at his starting/ending location. ^v^v^v^v^v delivers a
 * bunch of presents to some very lucky children at only 2 houses.
 *
 * @author peek4bUh
 */
public class Puzzle1 implements BasePuzzle {

    private int x = 0;
    private int y = 0;
    private List<List<Integer>> infiniteGrid = new ArrayList<>();

    @Override
    public void play() {
        String rawInput = ReadFile.getResourceFileAsString("input-day03.txt");
        List<String> directions = rawInput.chars()
                .mapToObj(Character::toString)
                .collect(Collectors.toList());
        infiniteGrid.add(new ArrayList<>(List.of(x, y)));

        directions.stream().forEach(this::getTotalHouses);
        List<List<Integer>> housesWithAtLeastOnePresent = removeDuplicates(infiniteGrid);

        System.out.println("Solution 3a: " + housesWithAtLeastOnePresent.size());
    }

    private void getTotalHouses(String direction) {
        switch (direction) {
            case "^":
                y += 1;
                break;
            case ">":
                x += 1;
                break;
            case "v":
                y -= 1;
                break;
            case "<":
                x -= 1;
                break;
            default:
                System.out.println("Found invalid direction: " + direction);
                System.exit(1);
        }
        infiniteGrid.add(new ArrayList<>(List.of(x, y)));
    }

    public static List<List<Integer>> removeDuplicates(List<List<Integer>> twoDList) {
        Set<List<Integer>> uniqueRows = new LinkedHashSet<>(twoDList);
        return new ArrayList<>(uniqueRows);
    }

}
