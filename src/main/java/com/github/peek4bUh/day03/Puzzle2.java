package com.github.peek4bUh.day03;

import com.github.peek4bUh.aoc2015.BasePuzzle;
import static com.github.peek4bUh.day03.Puzzle1.removeDuplicates;
import com.github.peek4bUh.utils.ReadFile;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * --- Part Two ---
 *
 * The next year, to speed up the process, Santa creates a robot version of
 * himself, Robo-Santa, to deliver presents with him.
 *
 * Santa and Robo-Santa start at the same location (delivering two presents to
 * the same starting house), then take turns moving based on instructions from
 * the elf, who is eggnoggedly reading from the same script as the previous
 * year.
 *
 * This year, how many houses receive at least one present?
 *
 * For example:
 *
 * ^v delivers presents to 3 houses, because Santa goes north, and then
 * Robo-Santa goes south. ^>v{@literal (<)} now delivers presents to 3 houses,
 * and Santa and Robo-Santa end up back where they started. ^v^v^v^v^v now
 * delivers presents to 11 houses, with Santa going one direction and Robo-Santa
 * going the other.
 *
 * @author peek4bUh
 */
public class Puzzle2 implements BasePuzzle {

    private int x = 0;
    private int y = 0;
    private int x2 = 0;
    private int y2 = 0;
    private boolean isSantaTurn = true;
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

        System.out.println("Solution 3b: " + housesWithAtLeastOnePresent.size());
    }

    private void getTotalHouses(String direction) {
        switch (direction) {
            case "^":
                if (isSantaTurn) {
                    y += 1;
                } else {
                    y2 += 1;
                }
                break;
            case ">":
                if (isSantaTurn) {
                    x += 1;
                } else {
                    x2 += 1;
                }
                break;
            case "v":
                if (isSantaTurn) {
                    y -= 1;
                } else {
                    y2 -= 1;
                }
                break;
            case "<":
                if (isSantaTurn) {
                    x -= 1;
                } else {
                    x2 -= 1;
                }

                break;
            default:
                System.out.println("Found invalid direction: " + direction);
                System.exit(1);
        }

        if (isSantaTurn) {
            infiniteGrid.add(new ArrayList<>(List.of(x, y)));
        } else {
            infiniteGrid.add(new ArrayList<>(List.of(x2, y2)));
        }

        isSantaTurn = !isSantaTurn;
    }

    public static List<List<Integer>> removeDuplicates(List<List<Integer>> twoDList) {
        Set<List<Integer>> uniqueRows = new LinkedHashSet<>(twoDList);
        return new ArrayList<>(uniqueRows);
    }

}
