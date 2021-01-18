package wordPlay.util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * The Results class to implement witetofile and writetostdout
 */
public class Results implements FileDisplayInterface, StdoutDisplayInterface {
	
	private static String outputString="";
	private String outputFileName;
	private String matricsFileName;
	private static float avgWordsPerSen;
	private static float avgWordLen;
	
	public void setOutputFileName(String outputFileName1) {
		outputFileName = outputFileName1;
	}
	
	public void setmatricsFileName(String matricsFileName1) {
		matricsFileName = matricsFileName1;
	}
	
	public void setAvgWordsPerSen(float val) {
		avgWordsPerSen = val;
	}
	
	public void setAvgWordLen(float val) {
		avgWordLen = val;
	}
	
	public static String getoutputString() {
		return outputString;
	}
	
	public void store(String rotatedWord) {
		outputString += rotatedWord;
	}
	
	/**
	 * Writes rotated content to output.txt and metric calculation
	 * in metric.txt file. 
	 *
	 * @param  null
	 * @return null
	 */
	public void writeToFile() {
        BufferedWriter writer = null;
        BufferedWriter writer2 = null;
        try {
            writer = new BufferedWriter(new FileWriter(outputFileName, false));
            writer.write(outputString);
            
            writer2 = new BufferedWriter(new FileWriter(matricsFileName, false));
            writer2.write("AVG_NUM_WORDS_PER_SENTENCE = " + avgWordsPerSen + "\n");
            writer2.write("AVG_WORD_LENGTH = " + avgWordLen + "\n");
             
        } catch(IOException e) {
            System.err.println("IO Exception: Filename was not a proper name.");
            System.exit(1);
        } finally {
            try {
                writer.close();
                writer2.close();
            } catch(IOException e) {
                System.err.println("BufferedWriter not found.");
                System.exit(1);
            }
        }
    }
	
	/**
	 * Writes rotated content to and metric calculation on console.
	 *
	 * @param  null
	 * @return null
	 */
	public void writeToStdout() {
		System.out.println("Resulted String is:\n" + outputString);
		System.out.println("AVG_NUM_WORDS_PER_SENTENCE = " + avgWordsPerSen + "\n");
		System.out.println("AVG_WORD_LENGTH = " + avgWordLen + "\n");
	}
	
	/**
     * Default toString, not needed for debugging here.
     * @return String with values that will be stored by Result
     */
    public String toString() {
        return ("Output String: " + outputString + "\nOutput Filename: " + outputFileName + "\nMatrics File Name: " + matricsFileName + "\nAvg Words Per Sentence: " + avgWordsPerSen + "\nAvg Word Length: " + avgWordLen);
    }

}
