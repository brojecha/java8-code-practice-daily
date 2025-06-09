package main.java;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Collections.*;
import static java.util.stream.Collectors.toList;

public class Day9June {
    public static void main(String[] args) {
        String str = "madam";
        boolean val = checkAnagramUsingJava8("listen","silent");
        System.out.println("val : "+val);
        boolean condition = isAnagram("listen", "silent");
        System.out.println("Is anagram : "+condition);


    }

    /**
     * Bruteforce Approach
     * @param str1
     * @param str2
     * @return
     *
     *
     * Sorting & Comparing
     * ==================
     * Normalize: Convert both strings to lowercase (and optionally strip whitespace/punctuation).
     * Sort characters: Convert to character arrays and use Arrays.sort(...).
     * Compare arrays: If the sorted arrays are identical, they’re anagrams.
     *
     * Time: O(logn) due to sorting
     *
     * Space: O(n) for the sorted copies
     */
    private static boolean isAnagram(String str1, String str2) {
        if (str1.length() != str2.length()) {
            return false;
        }
        char[] arr1 = str1.toCharArray();
        char[] arr2 = str2.toCharArray();
        Arrays.sort(arr1);
        Arrays.sort(arr2);
        //We can use Arrays.equals(arr1, arr2); as well to check if two arrays are equal or not or
        //anagram of each other or not.
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }
    private static boolean checkAnagramUsingJava8(String str1,String str2) {
        if(str1.length() != str2.length()) {
            return false;
        }

        String sortedStr1 = Stream.of(str1.split(""))
                .sorted()
                .collect(Collectors.joining());

        String sortedStr2 = Stream.of(str2.split(""))
                .sorted()
                .collect(Collectors.joining());

        if(sortedStr1.equals(sortedStr2)) {
            return true;
        }

        return false;
    }
}
