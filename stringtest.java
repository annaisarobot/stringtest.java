package stringtest;

/*
 In the programming language of your choice, write a method that modifies a 
string using the following rules: 
1. Each word in the input string is replaced with the following: the first 
letter of the word, the count of distinct letters between the first and last 
letter, and the last letter of the word. For example, "Automotive" would be 
replaced by "A6e". 
2. A "word" is defined as a sequence of alphabetic characters, delimited by any 
non-alphabetic characters. 
3. Any non-alphabetic character in the input string should appear in the output 
string in its original relative location. 
*/

import java.util.HashSet;
import java.util.Set;

public class stringtest {

    private static boolean nextWord = true;
    /* Decided on a HashSet to store uniques because it can't have duplicated
    and has a seek time of O(1). */
    private static Set<String> hashSet = new HashSet<>();
    
    public static void main(String[] args) {
        // Simple timer to find speed of algorithm.
        long startTime = System.currentTimeMillis();
        String[] testString = {"Automotive", "1defabc1 cb!a %%%def1123", "at", 
            "x", "tipu-is-smart", "a1234567890", "a98779079745092740", 
            "98779079745092740a"}; 
        for(int i=0; i<testString.length; ++i){
            String newString = modifyString(testString[i]);
            System.out.println(testString[i] + " -> " + newString);
        }
        double endTime = System.currentTimeMillis();
        System.out.println("Time elapsed: " + (endTime - startTime));
    }
    
    /* The assumption was made that if a single character existed between 
    delineations then that is all that should be returned in the new String. For
    example "x" should return just "x" so that it can be differentiated from
    "xx" which would return "x0x".
    */
    private static String modifyString(String word) {
        String returnString = "";
        char[] chars = word.toCharArray();
        for(int i=0; i<chars.length; ++i) {
            if(Character.isLetter(chars[i])) {
                if(nextWord == true) {
                    /* If the character is in the alphabet and it's the start of
                    the word add it to the return sring. */
                    returnString += chars[i];
                    /* Since it's no longer the start of a new word set nextWord
                    to false. */
                    nextWord = false;
                }
                // Otherwise it's not the start of the word so...
                else {
                    /* If the next character is a letter and is not out of
                    bounds then add it to the hashSet. */
                    if(chars.length-1 != i && Character.isLetter(chars[i+1])){
                        /* The character will not be added to the hash_Set if it
                        is not unique. */
                        hashSet.add(Character.toString(chars[i]));
                    }
                    /* If the next character is a number then add the size of 
                    the hash to the return string, this is the number of unique
                    characters. */ 
                    else {
                        returnString += hashSet.size();
                        // Clear the hashSet for the next word.
                        hashSet.clear();
                        /* Add the current character to the return string after 
                        the number of uniques. */
                        returnString += chars[i];
                        /* We've reached the end of the word so set nextWord to 
                        true. */
                        nextWord=true;
                    }
                }
            }
            // If the character is not in the alphabet.
            else {
                // Add the non alphabet character to the return string.
                returnString += chars[i];
                nextWord = true;
            }
        }
        return returnString;
    }
    
}
