package main.java;

import java.util.function.Predicate;
import java.util.stream.IntStream;

public class Day8June {
    public static void main(String[] args) {
        String str = "world";
        String myData = "madam  ";
        System.out.print(reverseStringInJava8(str));
        System.out.println("====================================");
        System.out.println("Is String is Palindrome : "+isPalindromeInJava8(myData));
        System.out.println("====================================");
        System.out.println("Is String is Palindrome Using AllMatch: "+isPalindromUsingStreamAndAllMatch(myData));



    }

    /**
     * Approach: Reversing with Streams + StringBuilder
     * @param str
     * @return
     *
     *
     * Easy Approach : StringBuilder.reverse().
     *
     * 🧩 How It Works
     * IntStream.range(0, length) generates a sequence of indices [0,1,...,n-1].
     *
     * map(i → char from end) uses input.charAt(length - 1 - i) to traverse the string backwards.
     *
     * collect(...) with:
     *
     * Supplier: StringBuilder::new – to create a fresh builder.
     *
     * Accumulator: StringBuilder::appendCodePoint – adds each character (int/char) to builder.
     *
     * Combiner: StringBuilder::append – merges partial results (important for parallel streams).
     *
     * Convert to String with .toString().
     *
     * ✅ Time complexity: O(n)
     * ✅ Memory usage: O(n) additional for the builder
     * 💡 This method is both functional and efficient, and more performant than using reduce() with string concatenation
     */
    private static String reverseStringInJava8(String str) {
        return IntStream.range(0, str.length())
                .mapToObj(i -> str.charAt(str.length() - 1 - i))
                .collect(StringBuilder::new,
                        StringBuilder::appendCodePoint,StringBuilder::append)
                .toString();
    }




    /**
     * StringBuilder.reverse() : Approch One
     *
     *  Lambda + Predicate<String> : Approch Two
     * Thought process:
     *
     * Wrap the palindrome logic in a lambda expression using the Predicate<String> functional interface.
     *
     * Within the predicate:
     *
     * Normalize the string.
     *
     * Reverse it with StringBuilder.reverse().
     *
     * Return the result of the .equals() comparison.
     * w3resource.com
     * +1
     * stackoverflow.com
     * +1
     *
     * Why use this?
     *
     * Promotes a functional style—store the predicate, reuse across contexts.
     *
     * Integrates nicely with stream pipelines (e.g., filter(isPalindrome)).
     *
     * Keeps palindrome logic in a self-contained functional object.
     *
     *
     * When to Choose What
     * ===================
     * Use Reverse & Compare when you need a quick, clear palindrome check — easy to understand and implement.
     *
     * Use a Predicate lambda when you're working with collections or need the logic reusable in stream operations.
     *
     * Use Streams with IntStream.range(...) when minimal extra memory and purely functional style are priorities.
     * @param strData
     * @return
     */
    private static boolean isPalindromeInJava8(String strData) {
        Predicate<String> strPredicate = str -> {
            // Normalized the string to lower case and remove alphanumeric
            String normalized = str.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
            String reversedString = new StringBuilder(normalized).reverse().toString();
            return normalized.equals(reversedString);
        };
       return strPredicate.test(strData);
    }


    /**
     * Example: Palindrome Check Revisited
     *
     * You used:
     * IntStream.range(0, length / 2)
     *          .allMatch(i -> ...)
     * This is exactly the kind of clean, index-based numeric stream usage IntStream.range() was designed for:
     *
     * You know the bounds (0 to length/2)
     *
     * You’re performing numeric comparisons on indices
     *
     * You avoid boxing/unboxing overhead
     *
     * The stream abstraction cleanly expresses the logic.
     *
     * Summary
     * =======
     *
     * Choose IntStream over Stream<Integer> for numeric logic—better performance, cleaner API.
     * Use IntStream.range() to replace index-based for-loops with functional code.
     * Use rangeClosed() when you need inclusive endpoints.
     *Great for tasks like palindrome checks, array indexing, calculations, and sequence generation.
     *
     * @param strData
     * @return
     */
    private static boolean isPalindromUsingStreamAndAllMatch(String strData) {
        //generaliuzed the string data
        String normalString = strData.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
        return IntStream.range(0,normalString.length()/2)
                .allMatch(i -> normalString.charAt(i) == normalString.charAt(normalString.length()-1-i));
    }
}
