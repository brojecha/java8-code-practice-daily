package main.java;

import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Remove All Digits and Special Characters
 */
public class RemoveAllDigitAndSpecialCharacters {
    public static void main(String[] args) {
        /**
         * IntStream.range(0, input.length()) → gives indices from 0 to input.length() - 1
         * So each value c represents an index
         * But Character::isAlphabetic expects a character (int code point), not an index
         *
         * So here you are passing index 0, 1, 2... to Character::isAlphabetic, not the character at that index.
         */
        String input = "A1B2#C3$";
        String collect = IntStream.range(0, input.length())
                .filter(i -> Character.isLetter(input.charAt(i)))
                .mapToObj(c -> String.valueOf(input.charAt(c))).collect(Collectors.joining());
        System.out.println(collect);

        System.out.println("==============================================================");
        System.out.println("Printing Another Way by using chars() method");
        //Another way to write code

        /**
         *
         * 🧠 Step-by-Step Breakdown
         * 🔹 Step 1: input.chars()
         * Converts the String into an IntStream of Unicode code points (integers).
         *
         * Input: "A1B2#C3$"
         *
         * Output: [65, 49, 66, 50, 35, 67, 51, 36] → these are ASCII values for 'A', '1', 'B', '2', '#', 'C', '3', '$'
         *
         * 📌 Note: It returns IntStream, not Stream<Character> because .chars() gives primitive int.
         *
         * 🔹 Step 2: .filter(Character::isAlphabetic)
         * Filters out only the characters that are letters (A-Z or a-z, including Unicode letters).
         *
         * This keeps only values where Character.isAlphabetic(codePoint) returns true.
         *
         * From our example:
         *
         * 'A', 'B', 'C' are alphabetic → kept
         *
         * '1', '2', '#', '3', '$' → filtered out
         *
         * Output stream: [65, 66, 67]
         *
         * 🔹 Step 3: .mapToObj(c -> String.valueOf((char) c))
         * Converts each int (ASCII/Unicode code point) into a Character, then into a String.
         *
         * 65 → 'A' → "A"
         *
         * 66 → 'B' → "B"
         *
         * 67 → 'C' → "C"
         *
         * Now you have: Stream<String> = ["A", "B", "C"]
         *
         * 🔹 Step 4: .collect(Collectors.joining())
         * Joins all strings together into a single string, with no delimiter.
         *
         * Input: ["A", "B", "C"]
         * Output: "ABC"
         */
        String value = input.chars().filter(Character::isAlphabetic)
                .mapToObj(c -> String.valueOf((char)c)).collect(Collectors.joining());
        System.out.println(value);

    }
}
