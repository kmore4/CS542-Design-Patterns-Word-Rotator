package wordPlay.metrics;

import wordPlay.util.Results;

/**
 * The MetricsCalculator class to perform metric calculation.
 */
public class MetricsCalculator {
	private int totalCharacters;
	private int totalWords;
	private int totalSentences;
	
	float wordsPerSen;
	float wordLen;
	
	public MetricsCalculator(int chars, int words, int sen) {
		// TODO Auto-generated constructor stub
		totalCharacters = chars;
		totalWords = words;
		totalSentences = sen;
	}

	Results resObj1 = new Results();
	
	
	public void setCharacters(int val) {
		totalCharacters = val;
	}
	
	public void setWords(int val) {
		totalWords = val;
	}
	
	public void setSentences(int val) {
		totalSentences = val;
	}
	
	public int getTotalChar() {
		return totalCharacters;
	}
	
	public int getToatalWords() {
		return totalWords;
	}
	
	public int getTotalSen() {
		return totalSentences;
	}
	
	public float getWordsPerSen() {
		return wordsPerSen;
	}
	
	public float getWordLen() {
		return wordLen;
	}
	
	/**
	 * implements of metric calculation and store it in result object.
	 *
	 * @param  null
	 * @exception ArithmeticException for division by 0.
	 * @return null
	 */
	public void metricCalculations() {
		if(totalSentences != 0 && totalWords != 0 && totalCharacters != 0) {
			wordsPerSen = (float)totalWords/(float)totalSentences;
			wordLen = (float)totalCharacters/(float)totalWords;
			
			float roundedWordsPerSen =  (float) (Math.round(wordsPerSen * 100.0) / 100.0);
			float roundedWordLen =  (float) (Math.round(wordLen * 100.0) / 100.0);
			
			resObj1.setAvgWordsPerSen(roundedWordsPerSen);
			resObj1.setAvgWordLen(roundedWordLen);
		}
		else {
			throw new ArithmeticException("/ by 0");
		}
	}
	
	/**
     * Default toString, needed for debugging here.
     * @return String with values that will be used
     */
    public String toString() {
        return ("Total Characters: " + totalCharacters + "\nTotal Words: " + totalWords + "\nTotal Sentences: " + totalSentences);
    }
}
