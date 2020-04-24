package converter;

import converter.exceptions.MalformedNumberException;
import converter.exceptions.ValueOutOfBoundsException;

import java.util.*;

/**
 * This class implements a converter that takes a string that represents a number in either the
 * Elbonian or Arabic numeral form. This class has methods that will return a value in the chosen form.
 *
 * @version 3/18/17
 */
public class ElbonianArabicConverter {

    // A string that holds the number (Elbonian or Arabic) you would like to convert
    // TODO make final again
    private String number;

    /**
     * Constructor for the ElbonianArabic class that takes a string. The string should contain a valid
     * Elbonian or Arabic numeral. The String can have leading or trailing spaces. But there should be no
     * spaces within the actual number (ie. "9 9" is not ok, but " 99 " is ok). If the String is an Arabic
     * number it should be checked to make sure it is within the Elbonian number systems bounds. If the
     * number is Elbonian, it must be a valid Elbonian representation of a number.
     *
     * @param number A string that represents either a Elbonian or Arabic number.
     * @throws MalformedNumberException  Thrown if the value is an Elbonian number that does not conform
     *                                   to the rules of the Elbonian number system. Leading and trailing spaces should not throw an error.
     * @throws ValueOutOfBoundsException Thrown if the value is an Arabic number that cannot be represented
     *                                   in the Elbonian number system.
     */


    // The Elbonian numerals are represented by the following letters of the alphabet:
    // M = 1,000
    // C = 100		D = 300		E = 600
    // X = 10		Y = 30		Z = 60
    // I = 1		J = 3		K = 6
    // For example, 2394 would be represented in Elbonian as MMDZYJI.
    // •	M, C, X, and I. These letters can only appear a maximum of two times in a number.
    // •	D, E, Y, Z, J, and K. These letters can only appear once in a number.
    // •	If both D and E appear, then C cannot appear in a number
    // •	If both Y and Z appear, then X cannot appear in a number
    // •	If both J and K appear, then I cannot appear in a number
    // •	Numbers are represented by the letters from the greatest value down to the lowest value. In other words, the letter I would never appear before the letters M, D, X, or J. The letter D would never appear before E or M but would appear before Y. The letters are summed together to determine the value.
    // •	Lowercase letters are not permitted
    // https://stackoverflow.com/questions/10575624/java-string-see-if-a-string-contains-only-numbers-and-not-letters
    public ElbonianArabicConverter(String number) throws MalformedNumberException, ValueOutOfBoundsException {
        this.number = number;
        // remove spaces from string
        number = number.replaceAll("\\s", "");
        // count of how many letters are in it
        HashMap<Character, Integer> hashOfLetters = new HashMap<Character, Integer>();
        //letters with rules about how many times they appear
        List<Character> twoBadLetters = Arrays.asList('D', 'E', 'Y', 'Z', 'J', 'K');
        List<Character> threeBadLetters = Arrays.asList('M', 'C', 'X', 'I');
        HashSet<Character> hashOf2BadLetters = new HashSet<Character>();
        HashSet<Character> hashOf3BadLetters = new HashSet<Character>();

        //filling hashset
        twoBadLetters.stream().map(character -> hashOf2BadLetters.add(character));
        threeBadLetters.stream().map(character -> hashOf3BadLetters.add(character));

        //list of letters from smallest to largest
        List<Character> Letters = Arrays.asList('I', 'J', 'K', 'X', 'Y', 'Z', 'C', 'D', 'E', 'M');
        HashMap<Character, Integer> letterNumConversion = new HashMap<Character, Integer>();

        // putting list into hashmap
        for (int i = 0; i < Letters.size(); i++) {
            letterNumConversion.put(Letters.get(i), i);
        }

        // checking for proper order of letters
        // checking for lowercase letters
        int minVal = 0;
        for (int i = 0; i < number.length(); i++) {
            int lastEbloianLetter = letterNumConversion.get(number.charAt(i));
            if (lastEbloianLetter < minVal) {
                throw new MalformedNumberException("error improper order");
            }
            minVal = lastEbloianLetter;
            //lowercase checker
            if (Character.isLowerCase(number.charAt(i))) {
                throw new MalformedNumberException("error lowercase");
            }
            //add number each type of letter
            if (hashOfLetters.containsKey(number.charAt(i))) {
                hashOfLetters.put(number.charAt(i), hashOfLetters.get(i) + 1);
            } else {
                hashOfLetters.put(number.charAt(i), 1);
            }
        }

        for (int i = 0; i < number.length(); i++) {
            if (hashOf2BadLetters.contains(number.charAt(i))) {
                if (hashOfLetters.get(number.charAt(i)) >= 2) {
                    throw new MalformedNumberException("error 2 bad letters");
                }
            }
            if (hashOf3BadLetters.contains(number.charAt(i))) {
                if (hashOfLetters.get(number.charAt(i)) >= 3) {
                    throw new MalformedNumberException("error 3 bad letters");
                }
            }
        }
        if (hashOfLetters.containsKey('D') & hashOfLetters.containsKey('E') & hashOfLetters.containsKey('C')) {
            throw new MalformedNumberException("error D, E, C appeared");
        }
        if (hashOfLetters.containsKey('Y') & hashOfLetters.containsKey('Z') & hashOfLetters.containsKey('X')) {
            throw new MalformedNumberException("error Y, Z, X appeared");
        }
        if (hashOfLetters.containsKey('J') & hashOfLetters.containsKey('K') & hashOfLetters.containsKey('I')) {
            throw new MalformedNumberException("error J, K, I appeared");
        }

        // TODO check to see if the number is valid, then set it equal to the string

    }

    /**
     * Converts the number to an Arabic numeral or returns the current value as an int if it is already
     * in the Arabic form.
     *
     * @return An arabic value
     */
    public int toArabic() {
        // TODO Fill in the method's body
        int convertedNumber;
        //if already is a number cast to int and return
        if () {
            convertedNumber = Integer.parseInt(number);
        }
        //if its in elbonian
        if (number.indexOf('M') != -1) {
            convertedNumber += 1000;
        }
        if (number.indexOf('E') != -1) {
            convertedNumber += 600;
        }
        if (number.indexOf('D') != -1) {
            convertedNumber += 300;
        }
        if (number.indexOf('C') != -1) {
            convertedNumber += 100;
        }
        if (number.indexOf('Z') != -1) {
            convertedNumber += 60;
        }
        if (number.indexOf('Y') != -1) {
            convertedNumber += 30;
        }
        if (number.indexOf('X') != -1) {
            convertedNumber += 10;
        }
        if (number.indexOf('K') != -1) {
            convertedNumber += 6;
        }
        if (number.indexOf('J') != -1) {
            convertedNumber += 3;
        }
        if (number.indexOf('I') != -1) {
            convertedNumber += 1;
        }
        return convertedNumber;
    }

    /**
     * Converts the number to an Elbonian numeral or returns the current value if it is already in the Elbonian form.
     *
     * @return An Elbonian value
     */
    public String toElbonian() {
        // TODO Fill in the method's body
        //if number is already in Elbonian format
        if () {
            return number;
        }
        //if number is in arabic format
        String convertedString;
        int convertedNumber;
        convertedNumber = Integer.parseInt(number);
        if (convertedNumber >= 1000) {
            convertedString += "M";
            convertedNumber -= 1000;
        }
        if (convertedNumber >= 600) {
            convertedString += "E";
            convertedNumber -= 600;
        }
        if (convertedNumber >= 300) {
            convertedString += "D";
            convertedNumber -= 300;
        }
        if (convertedNumber >= 100) {
            convertedString += "C";
            convertedNumber -= 100;
        }
        if (convertedNumber >= 60) {
            convertedString += "Z";
            convertedNumber -= 60;
        }
        if (convertedNumber >= 30) {
            convertedString += "Y";
            convertedNumber -= 30;
        }
        if (convertedNumber >= 10) {
            convertedString += "X";
            convertedNumber -= 10;
        }
        if (convertedNumber >= 6) {
            convertedString += "J";
            convertedNumber -= 6;
        }
        if (convertedNumber >= 3) {
            convertedString += "J";
            convertedNumber -= 3;
        }
        if (convertedNumber >= 1) {
            convertedString += "I";
            convertedNumber -= 1;
        }
        return convertedString;
    }

}
