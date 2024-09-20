import java.io.PrintWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;


/**
 *	MVCipher - Encrypts and decrypts a text file with the MV Cipher.
 *	Requires Prompt and FileUtils classes.
 *	
 *	@author	Elaina Pan
 *	@since	9/10/24
 */
public class MVCipher {
	
	// fields go here
		
	/** Constructor */
	public MVCipher() { }
	
	public static void main(String[] args) {
		MVCipher mvc = new MVCipher();
		mvc.run();
	}
	
	/**
	 *	Method header goes here
	 */
	public void run() {
		System.out.println("\n Welcome to the MV Cipher machine!\n");
		
		/* Prompt for a key and change to uppercase
		   Do not let the key contain anything but alpha
		   Use the Prompt class to get user input */
		
		
		/* Prompt for encrypt or decrypt */
			
			
		/* Prompt for an input file name */
		
		
		/* Prompt for an output file name */
		
		
		/* Read input file, encrypt or decrypt, and print to output file */
		
		
		/* Don't forget to close your output file */
	}
	
	// other methods go here
	
}

class Prompt
{
	// BufferedReader variables
	private static InputStreamReader streamReader = 
								new InputStreamReader(System.in);
	private static BufferedReader bufReader = 
								new BufferedReader(streamReader);
	
	
	/**
	 *	Prompts user for string of characters and returns the string.
	 *	@param ask  The prompt line
	 *	@return  	The string input
	 */
	public static String getString (String ask)
	{
		System.out.print(ask + " -> ");
		String input = "";
		try {
			input = bufReader.readLine();
		}
		catch (IOException e) {
			System.err.println("ERROR: BufferedReader could not read line");
		}
		
		return input;
	}
	
	/**
	 *	Prompts the user for an integer and returns the integer.
	 *	@param ask  The prompt line
	 *	@return  	The integer input
	 */
	public static int getInt (String ask)
	{
		int val = 0;
		boolean found = false;
		while(! found) {
			String str = getString(ask);
			try {
				val = Integer.parseInt(str);
				found = true;
			}
			catch (NumberFormatException e) {
				found = false;
			}
		}
		return val;
	}
	
	/**
	 *	Prompts the user for an integer using a range of min to max,
	 *	and returns the integer.
	 *	@param ask  The prompt line
	 *	@param min  The minimum integer accepted
	 *	@param max  The maximum integer accepted
	 *	@return  	The integer input
	 */
	public static int getInt (String ask, int min, int max)
	{
		int val = 0;
		do {
			val = getInt(ask + " (" + min + ", " + max + ")");
		} while (val < min || val > max);
		
		return val;
	}
	
	/**
	 *	Prompts the user for a double and returns the double.
	 *	@param ask  The prompt line
	 *	@return  The double input
	 */
	public static double getDouble (String ask)
	{
		double val = 0.0;
		boolean found = false;
		while(! found) {
			String str = getString(ask);
			try {
				val = Double.parseDouble(str);
				found = true;
			}
			catch (NumberFormatException e) {
				found = false;
			}
		}
		return val;
	}
	
	/**
	 *	Prompts the user for a double and returns the double.
	 *	@param ask  The prompt line
	 *	@param min  The minimum double accepted
	 *	@param max  The maximum double accepted
	 *	@return  The double input
	 */
	public static double getDouble (String ask, double min, double max)
	{
		double val = 0.0;
		do {
			val = getDouble(ask + " (" + min + ", " + max + ")");
		} while (val < min || val > max);
		
		return val;
	}
	
	/**
	 *	Prompts the user for a double and returns the double.
	 *	@param ask  The prompt line
	 *	@param min  The minimum double accepted
	 *	@param max  The maximum double accepted
	 *	@return  The double input
	 */
	 public static char getChar (String ask) 
	 {
		String val = "";
		boolean found = false;
		do {
			val = getString(ask);
		} while(val.length() != 1);
		return val.charAt(0);
	}	 
}
class FileUtils {
	/**
	 * Opens a file to read using the Scanner class.
	 * @param fileName		name of the file to open
	 * @return 				the scanner object to the file
	 */
	public static java.util.Scanner openToRead(String fileName) {
		java.util.Scanner input = null;
		try {
			input = new java.util.Scanner(new java.io.File(fileName));
		}
		catch (java.io.FileNotFoundException e) {
			System.err.println("ERROR: Cannot open " + fileName + 
						" for reading.");
			System.exit(4);
		}
		return input;
	}
	/**
	 * Opens a file to write using the PrintWriter class
	 * @param fileName		name of the file to pen
	 * @return 				the PrintWriter object to the file
	 */
	public static PrintWriter openToWrite (String fileName) {
		PrintWriter output = null;
		try {
			output = new PrintWriter(new File(fileName));
		}
		catch (FileNotFoundException e) {
			System.err.println("ERROR: Cannot open " + fileName +
							" for writing.");
			System.exit(5);
		}
		return output;
	}
}
