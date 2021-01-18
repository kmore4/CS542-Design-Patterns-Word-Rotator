package wordPlay.driver;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.InvalidPathException;

import wordPlay.handler.WordRotator;
import wordPlay.util.FileProcessor;
import wordPlay.util.Results;
import wordPlay.validator.EmptyFileException;
import wordPlay.validator.EmptyLineException;
import wordPlay.validator.FileNotExistException;
import wordPlay.validator.InvalidFileFormatException;
import wordPlay.validator.InvalidWordException;
import wordPlay.metrics.MetricsCalculator;

/**
 * @author Kasturi T. More
 *
 */

/**
 * The Driver class
 */
public class Driver {
	/**
	 * A string to store valid input filename received from command 
	 * line arguments.
	 */
	static String inputfile;
	
	/**
	 * Check weather input file exist at given path or not. 
	 *
	 * @param  inputfile   input file name
	 * @return         	   boolean value
	 */
	private static boolean checkIfFileExists(String inputfile) {
		if (inputfile == null)
			return false;
		File file = new File(inputfile);
		return file.exists();
	}
	
	
	/**
	 * Main method to call appropriate class objects to perform
	 * word rotation and metric calculation. 
	 *
	 * @param  command line argument
	 * @exception FileNotFoundException if file does not exist.
	 * @exception IOException if file can't be written to/closed.
     * @exception SecurityException if a SecurityManager exists and
     * disallows read or write access to aFile.
     * @exception InvalidPathException if path is not valid.
     * @exception InvalidWordException if Words contain characters 
     * other than [a-zA-Z0-9].
     * @exception ArithmeticException if division by 0.
     * @exception EmptyLineException if Empty line in input file.
     * @exception EmptyFileException if Empty input file.
     * @exception InvalidFileFormatException if filename provided in
     * command line argument does not end with .txt
     *  
	 * @return  null
	 */
	public static void main(String[] args) throws FileNotFoundException,  InvalidPathException, SecurityException, IOException, InvalidWordException, ArithmeticException, EmptyLineException, EmptyFileException, InvalidFileFormatException, FileNotExistException{

		/*
		 * As the build.xml specifies the arguments as input,output or metrics, in case the
		 * argument value is not given java takes the default value specified in
		 * build.xml. To avoid that, below condition is used
		 */
		if ((args.length != 3) || (args[0].equals("${input}")) || (args[1].equals("${output}")) || (args[2].equals("${metrics}"))) {
			System.err.println("Error: Incorrect number of arguments. Program accepts 3 arguments.");
			System.exit(0);
		}
		//System.out.println("Hello World! Lets get started with the assignment");
		
		try {
			if(args[0].endsWith(".txt") == false) {
				throw new InvalidFileFormatException("Input File should end with .txt");
			}
			if(!checkIfFileExists(args[0])){
				throw new FileNotExistException(args[0] + " does not exist.!");
			}
			inputfile = args[0];
			
			String line;
			FileProcessor myProcessor = new FileProcessor(inputfile);
			WordRotator wr = new WordRotator();
			Results res1 = new Results();
			int count = 0;
			while ((line = myProcessor.poll()) != null)
			{
				count++;
				if (line.isEmpty()){
					throw new EmptyLineException("Line is NULL...!!!");
			    }
				else {
					wr.print(line);
				}
			}
			if (count == 0) {
				throw new EmptyFileException("File is Empty...!!!");
			}
			
			res1.setOutputFileName(args[1]);
			res1.setmatricsFileName(args[2]);
			MetricsCalculator mcObj = new MetricsCalculator(wr.getChars(),wr.getWords(),wr.getSen());
			mcObj.metricCalculations();
			res1.writeToFile();
			res1.writeToStdout();
		} catch (InvalidPathException e) {
			// TODO catch block
			System.err.println(e);
		} catch (SecurityException e) {
			// TODO catch block
			System.err.println(e);
		} catch (FileNotFoundException e) {
			// TODO catch block
			System.err.println(e);
		} catch (IOException e) {
			// TODO catch block
			System.err.println(e);
		} catch (InvalidWordException e) {
			// TODO catch block
			System.err.println(e);
		} catch (ArithmeticException e) {
			// TODO catch block
			System.err.println(e);
		} catch (EmptyLineException e) {
			// TODO catch block
			System.err.println(e);
		} catch (EmptyFileException e) {
			// TODO catch block
			System.err.println(e);
		} catch (InvalidFileFormatException e) {
			// TODO catch block
			System.err.println(e);
		} catch (FileNotExistException e) {
			// TODO catch block
			System.err.println(e);
		} 
		
	}
}
