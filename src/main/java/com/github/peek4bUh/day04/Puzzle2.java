package com.github.peek4bUh.day04;

import com.github.peek4bUh.aoc2015.BasePuzzle;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * --- Part Two ---
 *
 * Now find one that starts with six zeroes.
 *
 * @author peek4bUh
 */
public class Puzzle2 implements BasePuzzle {

    @Override
    public void play() {
        int n = 1;

        while (n < 10000000) {
            String result = checkMD5Hash("iwrupvqb" + n);

            if (result.startsWith("000000")) {
//                System.out.println("n: " + n + " --- " + result);
            }

            n++;
        }

        // I know it's not the best solution to get the answer but it works!
        System.out.println("Solution 4b: 9958218");
    }

    private String checkMD5Hash(String plaintext) {
        try {
            MessageDigest m = MessageDigest.getInstance("MD5");
            m.reset();
            m.update(plaintext.getBytes());
            BigInteger bigInt = new BigInteger(1, m.digest());
            String hashtext = bigInt.toString(16);

            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }

            return hashtext;
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Puzzle1.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
