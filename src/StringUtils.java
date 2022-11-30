/*
 * @author ufszm
 */

public final class StringUtils {
    public static String reverse(String word) {
        char[] reversedWord = word.toCharArray();
        //sets the start value of the end index
        int k = word.length() - 1;
        for (int i = 0; i < word.length() / 2; i++) {
            k -= i;
            //switches the positions of the corresponding characters (with indices i and k)
            reversedWord[k] = word.charAt(i);
            reversedWord[i] = word.charAt(k);
        }
        return String.valueOf(reversedWord);
    }

    public static boolean isPalindrome(String word) {
        //checks if the reversed word is equal to the word from the input
        boolean wordIsPalindrome = reverse(word).equals(word);
        return wordIsPalindrome;
    }

    public static String removeCharacter(String word, int index) {
        int counter = 0;
        String wordWithRemovedCharacter = "";
        while(counter < word.length()){
            //if the counter has reached the symbol to remove
            if(counter == index){
                counter++;
            }
            wordWithRemovedCharacter += word.charAt(counter);
            counter++;
        }

        return wordWithRemovedCharacter;
    }

    public static boolean isAnagram(String first, String second) {
        //returns false if the lengths are different
        if (first.length() != second.length()) {
            return false;
        }

        char[] firstWordArray = first.toCharArray();
        char[] secondWordArray = second.toCharArray();

        //iterates over the words with a nested loop and checks if each symbol from the first word is contained
        int symbolIsContainedCounter = 0;
        for (int i = 0; i < firstWordArray.length; i++) {
            for (int k = 0; k < secondWordArray.length; k++) {
                if (firstWordArray[i] == secondWordArray[k]) {
                    symbolIsContainedCounter++;
                    //we remove the symbol to prevent it from matching again
                    secondWordArray = removeCharacter(String.valueOf(secondWordArray), k).toCharArray();
                    break;
                }
            }
        }
        boolean haveTheSameSymbols = symbolIsContainedCounter == firstWordArray.length;
        return haveTheSameSymbols;
    }

    public static int countCharacter(String word, char character) {
        int index = 0;
        int characterCounter = 0;
        while(index < word.length()){
            if(word.charAt(index) == character){
                characterCounter++;
            }
            index++;
        }
        return characterCounter;
    }
}
