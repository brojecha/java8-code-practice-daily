package main.java;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// Reverse Each Word in a Sentence
public class ReverseEachWordInString {
    public static void main(String[] args) {
        String input = "Java 8 Stream";
       String val = reverseEachWordInString(input);
        System.out.println(val);
    }

    private static String reverseEachWordInString(String input) {
        if(input.length() == 0){
            String.valueOf(-1);
        }

        //In case of map() , we can use for Each as well
        Stream<String> stringStream = Arrays.stream(input.split(" "))
                .map(s -> new StringBuilder(s).reverse().toString());
        return stringStream.collect(Collectors.joining(" "));

        /**
         *
         *  Normal Approqach
         *  =================
         *
         * Another way to write the code
         * StringBuilder sb = new StringBuilder();
         * for (String word : input.split(" ")) {
         * sb.append(new StringBuilder(word).reverse().toString()).append(" ");
         * }
         * return sb.toString();
         */


        /**
         * 🔹 2. Java 8 using IntStream (Character level processing)
         *
         * public static String reverseEachWordUsingIntStream(String input) {
         *     return Stream.of(input.split(" "))
         *         .map(word -> IntStream.rangeClosed(1, word.length())
         *             .mapToObj(i -> String.valueOf(word.charAt(word.length() - i)))
         *             .collect(Collectors.joining()))
         *         .collect(Collectors.joining(" "));
         * }
         * 🧠 Here, you reverse each word character by character using IntStream.
         */
    }
}
