import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
/**
 *	The game of Pig.
 *	Plays a dice game against a computer where each player decides to 
   	* hold their collective points or roll each turn. A roll of 1 makes 
   	* you lose your round points and try to reach 100.
 *
 *	@author	Elaina Pan
 *	@since	9/10/24
 */
public class PigGame {
	/** variables */
	Dice dice; // the dice object that is used in the game
	int yourTotal; // your total score after each round
	int compTotal; // computer's total score after each round
	int round; // one's score in the specific round
	int roll1; // the value on one roll of the die
	char move; // whether you choose to roll or hold
	/** constructor, initializes methods */
	public PigGame() {
		yourTotal = compTotal = round = roll1 = 0;
	}
	/** main class */
	public static void main(String [] args) {
		PigGame pg = new PigGame(); // creates a copy of the game
		pg.runner();
	}
	/** runner method to run all code */
	public void runner() {
		printIntroduction();
		dice = new Dice();
		char playGame; // whether you play the game or run statistics
		do {
			playGame = Prompt.getChar("Play game or Statistics (p or s)");
		} while(playGame != 'p' && playGame != 's');
		
		if(playGame == 'p') {
			playGame();
		}	
		else if(playGame == 's') {
			statistics();
		}
			
	}
	/** method to rotate between user and computer turns of playing PigGame*/
	public void playGame() {
		while(yourTotal < 100 && compTotal < 100) {
			round = roll1 = 0;
			System.out.print("\n");
			do {
				System.out.println("Your turn score: "+round);
				System.out.println("Your total score: "+yourTotal);
				move = Prompt.getChar("(r)oll or (h)old -> ");
			} while(move != 'r' && move != 'h');
			while(move == 'r' && roll1!=1) {
				System.out.print("\n");
				System.out.println("You ROLL");
				roll1 = dice.roll();
				round += roll1;
				dice.printDice();
				System.out.println("Your turn score: "+round);
				System.out.println("Your total score: "+yourTotal);
				if(roll1 == 1) {
					System.out.println("\nYou LOSE your turn.");
				}
				else {
					move = Prompt.getChar("(r)oll or (h)old -> ");
				}
			}
			if(move == 'h') {
				System.out.println("\nYou HOLD");
				yourTotal += round;
			}
			System.out.println("Your total score: "+yourTotal);
			if(yourTotal < 100) {
				round = roll1 = 0;
				System.out.print("\n");
				System.out.println("Computer turn score: "+round);
				System.out.println("Computer total score: "+compTotal);
				Prompt.getString("Press enter for computer turn");
				while(round < 20 && roll1 != 1) {
					System.out.println("\nComputer will ROLL");
					roll1 = dice.roll();
					round += roll1;
					dice.printDice();
					System.out.println("Computer turn score: "
														+round);
					System.out.println("Computer total score: "
													+compTotal+"\n");
					if(roll1 == 1) {
						System.out.println("\nComputer LOSES their"
														+ " turn.");
					}
					else if(round < 20) {
						Prompt.getString("Press enter for computer "
														+"turn");
					}
				}
				if(roll1 >= 20) {
					System.out.println("\nComputer will HOLD");
					compTotal += round;
				}
				System.out.print("Computer total score: "+compTotal);
			}
		}
		if(yourTotal == compTotal) {
			System.out.print("It's a tie!");
		}
		else if(yourTotal > compTotal) {
			System.out.println("Congratulations!!! You WON!!!");
		}
		else {
			System.out.print("Too bad. COMPUTER WON.");
		}
		System.out.println("\nThanks for playing the Pig Game!!!");
	}
	/** runs statistics */
	public void statistics() {
		int holdNum = 20; // minimum value computer starts to hold at
		System.out.println("\nRun statistical analysis - "
												+ "\"Hold at 20\"\n");
		// number of trials done to calculate probabilities of each outcome
		int turnNum = Prompt.getInt("Number of Turns", 1000, 10000000);
		// keeps track of the number of trials where each score is obtained
		int[] scoreCounts = new int[7];
		for(int i=0;i<7;i++) {
			scoreCounts[i] = 0;
		}
		for(int i=0;i<turnNum;i++) {
			int diceRoll = 0; // simulates each roll of die object
			int rollTotal = 0; // keeps track of round score
			while(rollTotal < 20 && diceRoll != 1) {
				diceRoll = (int)(6*Math.random()+1);
				rollTotal += diceRoll;
			}
			if(diceRoll == 1) {
				scoreCounts[0]++;
			}
			else {
				scoreCounts[rollTotal-holdNum+1]++;
			}
		}
		// array keeping track of calculated probabilities for each outcome
		double[] scorePercents = new double[7]; 
		for(int i=0;i<7;i++) {
			scorePercents[i] = ((double)scoreCounts[i])/turnNum;
		}
		System.out.printf("\n%-8sEstimated Probability","Score");
		System.out.printf("\n%-8d%-10.6f",0,scorePercents[0]);
		for(int i=1;i<7;i++) {
			System.out.printf("\n%-8d%-10.6f",i+holdNum-1,scorePercents[i]);
		}
		System.out.print("\n");
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
