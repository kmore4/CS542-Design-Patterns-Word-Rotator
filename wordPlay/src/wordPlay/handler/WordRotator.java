package wordPlay.handler;

import java.io.File;
import java.lang.Character;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.InvalidPathException;

//import wordPlay.util.FileProcessor;
//import wordPlay.driver.Driver;
import wordPlay.util.Results;
//import wordPlay.metrics.MetricsCalculator;
import wordPlay.validator.InvalidWordException;

/**
 * @author Kasturi T. More
 *
 */

/**
 * The WordRotator class to perform word rotation on each word.
 */
public class WordRotator{
	int totalWords = 0;
	int totalCharacters = 0;
	int totalSen = 0;
	int wordIndex = 0;
	Results resObj = new Results();
	
	/**
	 * Checks whether given word is valid or not i.e. Words contain 
	 * characters other than [a-zA-Z0-9].
	 *
	 * @param  String  word to check
	 * @return val	   boolean value true if word is valid else false.
	 */
	public boolean validString(String s1) {
		boolean val = true;
		if(s1 == null) val = false;
		if(Character.isLetterOrDigit(s1.charAt(s1.length()-1)) == true || s1.substring(s1.length() - 1).equals(".") == true) {
			val = true;
		}
		for(int i=0;i<s1.length()-1;i++) {
			if ((Character.isLetterOrDigit(s1.charAt(i)) == false)) {
				val = false;
				break;
		}
	}	
		return val;
	}
		
	public int getChars() {
		return totalCharacters;
	}
	
	public int getWords() {
		return totalWords;
	}
	
	public int getSen() {
		return totalSen;
	}
	
	/**
	 * rotates the word to right by given index position
	 *
	 * @param  String  word to be rotated.
	 * @param  Integer Index of word in a line.
	 * @return String  Rotated word.
	 */
	public String rotate(int index, String str) {
		int d = index % str.length();
		String ans =  str.substring(str.length()-d) + str.substring(0, str.length()-d) ; 	  
		return ans;	
	}
	
	/**
	 * checks validity of word and perform word rotation
	 * and store it in result object store method
	 *
	 * @param  String  word received from fileprocessor.
	 * @return null
	 */
	public void print(String w1) throws InvalidWordException {
		String returnStr = "";
		if(validString(w1) == true) {
			wordIndex++;
			totalWords ++;
			totalCharacters += w1.length();
			if(w1.endsWith(".") == true) {
				String lastWord = "";
				totalCharacters--;
				totalSen++;
				for(int i=0;i<w1.length()-1;i++) {
					lastWord += w1.charAt(i);
				}
				returnStr += rotate(wordIndex,lastWord);
				returnStr += ".\n";
				wordIndex = 0;
			}
			else {
				returnStr += rotate(wordIndex,w1);
				returnStr += " ";
				//resObj.store(returnStr);
			}
			
		}
		else {
			throw new InvalidWordException("Exception: Invalid String Format.");
		}
		resObj.store(returnStr);
	}
	
	/**
     * Default toString, needed for debugging here.
     * @return String with values that will be used
     */
    public String toString() {
        return ("Total Characters: " + totalCharacters + "\nTotal Words: " + totalWords + "Total Sentences: " + totalSen);
    }
}