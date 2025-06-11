package main.java;

import java.util.Arrays;

public class CountVowels {
    public static void main(String[] args) {
        String input = "Java 8 Stream";
        int count = countVowels(input);
        System.out.println("Count : "+count);
    }

    private static int countVowels(String input) {
        return Math.toIntExact(Arrays.stream(input.split("")).filter(c -> "aeiouAEIOU".contains(c)).count());
    }
}
