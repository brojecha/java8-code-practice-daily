package main.java;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FindLongestWordInSentence {
    public static void main(String[] args) {
        String input = "Java 8 stream api is powerful";
        longestWordInSentence(input);
        longestWordInStringUsingCustomComparator(input);
    }

    /**
     * ✅ 2. Why use Comparator.comparingInt(String::length)?
     * When you call .max() on a stream, you need to tell Java how to compare elements — especially which field to compare.
     *
     * 🔹 In our case:
     * We want the word with the maximum length.
     *
     * .max(Comparator.comparingInt(String::length))
     * 🧠 Here's what this does:
     *
     * String::length is a method reference — it gives the length of each string.
     * Comparator.comparingInt() tells the stream to compare based on that int value.
     *
     * ✅ So effectively it sorts words by their length and returns the longest.
     * @param input
     */
    private static void longestWordInSentence(String input) {
        //Arrays.stream(words) we can use in place of Stream.of()  // also valid
        Stream.of(input.split(" ")).max(Comparator.comparingInt(String::length))/*.orElse("")*/.ifPresent(System.out::println);

    }

    /**
     * 📌 Summary
     * Part	Purpose
     * input.split(" ") :	Break sentence into words
     * Stream.of(...)   :	Create stream of words
     * Comparator.comparingInt(String::length)  :	Compare words by length
     * .max(...)	: Find longest word
     * .orElse("") :	Handle empty input safely
     */

    static void longestWordInStringUsingCustomComparator(String input){

        Arrays.stream(input.split(" ")).max((c1,c2) -> Integer.compare(c1.length(),c2.length())).ifPresent(System.out::println);

        //Another way to write code
        /*String longest = Stream.of(input.split(" "))
                .sorted(Comparator.comparingInt(String::length).reversed())
                .findFirst()
                .orElse("");*/

    }
}
