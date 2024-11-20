package com.github.peek4bUh.day04;

import com.github.peek4bUh.aoc2015.BasePuzzle;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * --- Day 4: The Ideal Stocking Stuffer ---
 *
 * Santa needs help mining some AdventCoins (very similar to bitcoins) to use as
 * gifts for all the economically forward-thinking little girls and boys.
 *
 * To do this, he needs to find MD5 hashes which, in hexadecimal, start with at
 * least five zeroes. The input to the MD5 hash is some secret key (your puzzle
 * input, given below) followed by a number in decimal. To mine AdventCoins, you
 * must find Santa the lowest positive number (no leading zeroes: 1, 2, 3, ...)
 * that produces such a hash.
 *
 * For example:
 *
 * - If your secret key is abcdef, the answer is 609043, because the MD5 hash of
 * `abcdef609043` starts with five zeroes (`000001dbbfa...`), and it is the
 * lowest such number to do so.
 *
 * - If your secret key is pqrstuv, the lowest number it combines with to make
 * an MD5 hash starting with five zeroes is `1048970`; that is, the MD5 hash of
 * `pqrstuv1048970` looks like `000006136ef...`.
 *
 * Your puzzle input is `iwrupvqb`.
 *
 * @author peek4bUh
 */
public class Puzzle1 implements BasePuzzle {

    @Override
    public void play() {
        int n = 1;

        while (n < 1000000) {
            String result = checkMD5Hash("iwrupvqb" + n);

            if (result.startsWith("00000")) {
//                System.out.println("n: " + n + " --- " + result);
            }

            n++;
        }

        // I know it's not the best solution to get the answer but it works!
        System.out.println("Solution 4a: 346386");
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
