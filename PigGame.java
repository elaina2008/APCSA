import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
/**
 *	The game of Pig.
 *	(Description here)
 *
 *	@author	
 *	@since	
 */
public class PigGame {
	/** variables */
	Dice dice;
	int totalY, totalC, round, rollNum, turnSum;
	char move;
	/** constructor */
	public PigGame() {
		totalY = totalC = round = rollNum = turnSum = 0;
	}
	/** main class */
	public static void main(String [] args) {
		PigGame pg = new PigGame();
		pg.runner();
	}
	/** runner method to run all code */
	public void runner() {
		printIntroduction();
		dice = new Dice();
		char playGame;
		do {
			playGame = Prompt.getChar("Play game or Statistics (p or s)");
		} while(playGame != 'p' && playGame != 'p');
		
		if(playGame == 'p') {
			rollNum = 0;
			System.out.print("\n");
			System.out.println("Your turn score: "+rollNum);
			System.out.println("Your total score: "+totalY);
			do {
				move = Prompt.getChar("(r)oll or (h)old -> ");
			} while(playGame != 'r' && playGame != 'h');
			while(move == 'r') {
				System.out.print("\n");
				System.out.println("You ROLL");
				rollNum += dice.roll();
				dice.printDice();
				System.out.println("Your turn score: "+rollNum);
				System.out.println("Your total score: "+totalY);
				move = Prompt.getChar("(r)oll or (h)old -> ");
			}
			System.out.print("\nYou HOLD");
			totalY += rollNum;
			System.out.print("Your total score: "+totalY);
		}	
	}
	/**	Print the introduction to the game */
	public void printIntroduction() {
		System.out.println("\n");
		System.out.println("______ _         _____");
		System.out.println("| ___ (_)       |  __ \\");
		System.out.println("| |_/ /_  __ _  | |  \\/ __ _ _ __ ___   ___");
		System.out.println("|  __/| |/ _` | | | __ / _` | '_ ` _ \\ / _ \\");
		System.out.println("| |   | | (_| | | |_\\ \\ (_| | | | | | |  __/");
		System.out.println("\\_|   |_|\\__, |  \\____/\\__,_|_| |_| |_|\\___|");
		System.out.println("          __/ |");
		System.out.println("         |___/");
		System.out.println("\nThe Pig Game is human vs computer. Each takes a"
							+ " turn rolling a die and the first to score");
		System.out.println("100 points wins. A player can either ROLL or "
							+ "HOLD. A turn works this way:");
		System.out.println("\n\tROLL:\t2 through 6: add points to turn total, "
							+ "player's turn continues");
		System.out.println("\t\t1: player loses turn");
		System.out.println("\tHOLD:\tturn total is added to player's score, "
							+ "turn goes to other player");
		System.out.println("\n");
	}
	
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
