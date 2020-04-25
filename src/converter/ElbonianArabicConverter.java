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
    private final String number;

    //list of letters from smallest to largest
    private List<Character> letters = new LinkedList<>(Arrays.asList('I', 'J', 'K', 'X', 'Y', 'Z', 'C', 'D', 'E', 'M'));
    private HashMap<Character, Integer> letterPriority = new HashMap<>();

    //list of numbers smallest to largest
    private List<Integer> numberList = new LinkedList<>(Arrays.asList(1, 3, 6, 10, 30, 60, 100, 300, 600, 1000));
    private HashMap<Character, Integer> characterToElbonianAribicValue = new HashMap<>();
    private HashMap<Integer, Character> aribicValueToElbonianCharacter = new HashMap<>();

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
        this.number = number.replaceAll("\\s", "");
        if(this.number.matches("[0-9]+") && this.number.matches("[a-zA-Z]+")){
            throw new MalformedNumberException("Cannot have both numbers a letters");
        }
        if(!(this.number.matches("[0-9]+") || this.number.matches("[a-zA-Z]+"))){
            throw new MalformedNumberException("Must only be numbers or letters");
        }
        // count of how many letters are in it
        HashMap<Character, Integer> hashOfLetters = new HashMap<Character, Integer>();
        //letters with rules about how many times they appear
        //Letters that can only appear once
        List<Character> twoBadLetters = Arrays.asList('D', 'E', 'Y', 'Z', 'J', 'K');
        HashSet<Character> hashOf2BadLetters = new HashSet<Character>();
        // Letters than can only appear twice
        List<Character> threeBadLetters = Arrays.asList('M', 'C', 'X', 'I');
        HashSet<Character> hashOf3BadLetters = new HashSet<Character>();

        //filling hashsets of the letters that can only appear X number of times
        twoBadLetters.stream().map(hashOf2BadLetters::add);
        threeBadLetters.stream().map(hashOf3BadLetters::add);

        //filling hashmap to connect Elbonian to integers
        for (int i = 0; i < letters.size(); i++) {
            characterToElbonianAribicValue.put(letters.get(i), numberList.get(i));
        }

        //filling hashmap to connect integers to elbonian
        for (int i = 0; i < letters.size(); i++) {
            aribicValueToElbonianCharacter.put(numberList.get(i), letters.get(i));
        }


        // putting list into priority hashmap
        for (int i = 0; i < letters.size(); i++) {
            letterPriority.put(letters.get(i), i);
        }

//        Check for lowercase
        for (int i = 0; i < this.number.length(); i++) {
            //lowercase checker
            if (Character.isLowerCase(this.number.charAt(i))) {
                throw new MalformedNumberException("error lowercase");
            }
        }

        if (!this.number.matches("[0-9]+")) {
            // checking for proper order of letters
            // checking for lowercase letters
            int minVal = 1000;
            for (int i = 0; i < this.number.length(); i++) {

                // Check for ordering of letter priority
                int lastEbloianLetter = letterPriority.get(this.number.charAt(i));
                if (lastEbloianLetter > minVal) {
                    throw new MalformedNumberException("error improper order");
                }
                minVal = lastEbloianLetter;

                //add number each type of letter
                if (hashOfLetters.containsKey(this.number.charAt(i))) {
                    hashOfLetters.put(this.number.charAt(i), hashOfLetters.get(this.number.charAt(i)) + 1);
                } else {
                    hashOfLetters.put(this.number.charAt(i), 1);
                }
            }

            for (int i = 0; i < this.number.length(); i++) {
                if (hashOf2BadLetters.contains(this.number.charAt(i))) {
                    if (hashOfLetters.get(this.number.charAt(i)) >= 2) {
                        throw new MalformedNumberException("error 2 bad letters");
                    }
                }
                if (hashOf3BadLetters.contains(this.number.charAt(i))) {
                    if (hashOfLetters.get(this.number.charAt(i)) >= 3) {
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
        }
//        Check if the number is out of bounds and return an error if it is
        int arabicNum = toArabic();
        if (arabicNum >= 3000 || arabicNum <= 0) {
            throw new ValueOutOfBoundsException("The value is out of bounds");
        }
    }

    /**
     * Converts the number to an Arabic numeral or returns the current value as an int if it is already
     * in the Arabic form.
     *
     * @return An arabic value
     */
    public int toArabic() {
        int convertedNumber = 0;
        //if its in elbonian
        if (!number.matches("[0-9]+")) {
            for (char c : number.toCharArray()) {
                convertedNumber += characterToElbonianAribicValue.get(c);
            }
        } else {
            convertedNumber = Integer.parseInt(number);
        }
        return convertedNumber;
    }

    /**
     * Converts the number to an Elbonian numeral or returns the current value if it is already in the Elbonian form.
     *
     * @return An Elbonian value
     */
    public String toElbonian() {
//        Make returnString
        StringBuilder convertedString = new StringBuilder();

        //If number is in arabic format
        if (number.matches("[0-9]+")) {
//            Convert string to int
            int convertedNumber = Integer.parseInt(number);
//            Place holder for the aribic number of the elbonian number we are on
            int elbonianNumTmp;
//            For every char in the string. Start at the biggest and go downwards to Zero
            for (int i = letters.size() - 1; i >= 0; i--) {
//                Get the aribic value of the char
                elbonianNumTmp = characterToElbonianAribicValue.get(letters.get(i));
                while (convertedNumber >= elbonianNumTmp) {
                    convertedNumber -= elbonianNumTmp;
                    convertedString.append(letters.get(i));
                }
            }
            return convertedString.toString();
        }
        return number;
    }

}
