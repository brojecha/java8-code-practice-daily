package main.java;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Day10June {
    public static void main(String[] args) {
        String str = "aapplleerrtt";
        removeAllDuplicateCharacterFromStringUsingBruteForce(str);
        System.out.println("===================================");
        removeDuplicatesFromStringJava8Code(str);

        System.out.println("===========================================");
        String str1 = "listen";
        String str2 = "silent";
        boolean val = checkanagramStringJava8Approach(str1,str2);
        System.out.println("Printing Value : "+val);

        System.out.println("===========================================");
        String[] strArray = {"apple","bablu","cc","dd"};
        capitalizeFirstLetterOfEachWord(strArray);
    }

    /**
     * Note : Different approaches to solve the same problem
     * @param str
     *
     * Use LinkedHashSet + stream when you care about order
     *
     * Use filter(seen::add) if you want more control or to tweak logic
     *
     * Use distinct() for a fast, one-liner (good for numeric ASCII or when order is not critical)
     */

    static void removeAllDuplicateCharacterFromStringUsingBruteForce(String str){
        char[] charArray = str.toCharArray();
        Set<Character> hashSet = new HashSet<>();
        for (int i = 0; i < charArray.length; i++) {
            hashSet.add(charArray[i]);
        }
        System.out.println(hashSet.toString());
    }

    /**
     * 1. Using String.chars() + distinct() (Order Not Guaranteed for non-Unicode)
     * @param str
     *
     * Note: .distinct() on primitive stream keeps the first occurrence in stream order,
     * but it works best when input is ASCII-based.
     */
    static void removeDuplicatesFromStringJava8Code(String str){
        String removeDuplicateValue = str.chars().distinct().mapToObj(c -> String.valueOf((char)c))
                .collect(Collectors.joining());
        System.out.println(removeDuplicateValue+" ");
    }


    /**
     * preserve insertion Ordeer by using LinkedHashSet
     *
     * Step 1: input.chars()
     * Converts the String to an IntStream of Unicode code points (integers).
     *
     * "bananA" → Stream of ASCII values: [98, 97, 110, 97, 110, 65]
     *
     * It's essentially a way to loop over each character in the string.
     *
     * 🔹 Step 2: .mapToObj(c -> (char) c)
     * Converts each int back to char, so we get a **Stream<Character>`.
     *
     * After this step: ['b', 'a', 'n', 'a', 'n', 'A']
     *
     * Now we're dealing with actual characters instead of numeric values.
     *
     * 🔹 Step 3: .collect(Collectors.toCollection(LinkedHashSet::new))
     * Collects characters into a LinkedHashSet<Character>, which:
     *
     * Removes duplicates
     *
     * Preserves insertion order
     *
     * So: ['b', 'a', 'n', 'A'] → Only the first occurrence of each character is kept.
     *
     * This is the key step that ensures both de-duplication and order preservation.
     *
     * 🔹 Step 4: .stream()
     * Re-streams the LinkedHashSet so we can perform more operations.
     *
     * Now we again have Stream<Character>: ['b', 'a', 'n', 'A']
     *
     * Without this step, we can't map or join the result further.
     *
     * 🔹 Step 5: .map(String::valueOf)
     * Converts each Character to a String.
     *
     * ['b', 'a', 'n', 'A'] → ["b", "a", "n", "A"]
     *
     * 🔹 Step 6: .collect(Collectors.joining())
     * Joins all the strings into one final string.
     *
     * ["b", "a", "n", "A"] → "banA"
     */

    static void removeDuplicatesUsingHashSet(String str){
        str.chars().mapToObj(c -> String.valueOf((char)c))
                .collect(Collectors.toCollection(LinkedHashSet::new))
                .stream().map(String::valueOf).collect(Collectors.joining());
    }

    /**
     * 3. Using Traditional For-Loop with Java 8 Flavor (Functional + Mutable Set)
     *
     * filter(seen::add) — works because Set.add() returns false if duplicate
     */
    static void removeDuplicatesUsingForLoop(String str){
        Set<Character> seen = new HashSet<>();
        str.chars().mapToObj(c -> (char)c)
            .filter(seen::add).map(String::valueOf).collect(Collectors.joining());
    }

    /**
     * Case-Insensitive Removal (Optional Variant)
     */
    static void removeDuplicatesCaseInsensitive(String str){
        Set<Character> seen = new HashSet<>();
        String retrurnString = str.chars()
                .mapToObj(c -> Character.toLowerCase((char)c))
                .filter(seen::add)
                .map(String::valueOf).collect(Collectors.joining());
        Stream.of(retrurnString).forEach(System.out::println);
    }

    /**
     * Convert to List or Array for Character-Based Logic
     */
    static void removeDuplicatesUsingListOrArray(String str){
        List<Character> collect = str.chars().mapToObj(c -> (char) c).distinct().collect(Collectors.toList());
        System.out.println(collect);
    }

    // Anagram java code logic

    /**
     * 🧠 Thought Process to Follow:
     * Step	Thought	Java 8 Concept
     * 1	Convert string to stream	.chars()
     * 2	Sort the characters	.sorted()
     * 3	Convert int to char	mapToObj(c -> (char)c)
     * 4	Convert char to string	String.valueOf()
     * 5	Join into final string	Collectors.joining()
     * 6	Compare both final strings	.equals()
     * @param str1
     * @param str2
     * @return
     */
    static boolean checkanagramStringJava8Approach(String str1,String str2){
        if(str1.length() != str2.length()){
            return false;
        }
        /**
         * ❌ Why This Is Incorrect:
         *
         *  //String string1 = str1.chars().mapToObj(c -> (char) c).sorted().toString();
         *
         *
         * .toString() on a stream or any object like a stream/collection does not return the joined content.
         * It returns something like: java.util.stream.ReferencePipeline$7@6f496d99
         * So, string1.equals(string2) is comparing object addresses, not the sorted characters.
         */
        //String string1 = str1.chars().mapToObj(c -> (char) c).sorted().toString();

        String string1 = str1.chars().mapToObj(c -> String.valueOf((char) c)).sorted().collect(Collectors.joining());

        String string2 = str2.chars().mapToObj(c -> String.valueOf((char) c)).sorted().collect(Collectors.joining());

        if(string1.equals(string2)){
            return true;
        }
        return false;
    }

    /**
     *
     * Note :
     * =====
     * ❌ Why This Code Fails as an Anagram Checker:
     *
     * IntStream.range(0, str1.length())
     *          .allMatch(i -> str1.charAt(i) == str2.charAt(i))
     * This line checks if each character at the same index in both strings is equal — i.e., it checks for string equality, not anagram.
     *
     * 🔍 What It’s Actually Doing:
     * This is equivalent to:
     *
     * str1.equals(str2)
     * ✅ So for:
     *
     * "abc" and "abc" → returns true (same string)
     *
     * "abc" and "cab" → returns false (even though they are anagrams!)
     * ================================================================
     * Correct Anagram Check using Sorted Stream + allMatch (Java 8):
     * @param str1
     * @param str2
     * @return
     */
    static boolean checkAnagramUsingJava8AllMatch(String str1,String str2) {
        if(str1.length() != str2.length()) {
            return false;
        }

        List<Integer> string1 = str1.chars().sorted().boxed().collect(Collectors.toList());

        List<Integer> string2 = str2.chars().sorted().boxed().collect(Collectors.toList());
        IntStream.range(0,str1.length()).allMatch(i -> string1.get(i).equals(string2.get(i)));
        return false;
    }

    //Capitalize first letter of each word in a string in java

    static void capitalizeFirstLetterOfEachWord(String[] str){
        Arrays.stream(str)
                .map(s -> Character.toUpperCase(s.charAt(0))+s.substring(1))
                .forEach(System.out::println);

        // Note : another way to write the map() method inside logic
        // map(s -> s.substring(0, 1).toUpperCase() + s.substring(1))
    }
}
