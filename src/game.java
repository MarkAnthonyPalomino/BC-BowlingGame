/*BrightCoders: The Bowling Game
 * By: Mark Anthony Palomino
 * Description: An automatic bowling game shown in console.
 */

import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

//We create the main class of our program
public class game {
	public static void main(String[] args) throws IOException {
		//========================================================================
		/*These are the variables for our game: Strings to save our player name..
		 * Two arrays; one that stores every try points and another with the total
		 * score.
		 */
		String fullPlayerName = "             ";
		String manualOrAutomatic;
		String playerName;
		Integer[] tries = new Integer[0];
		int score;
		int[] total = new int[0];
		Scanner scanner = new Scanner(System.in);
		//========================================================================
		System.out.println("Welcome to BrightCoders Bowling.");
		System.out.println("Please enter your player name to continue. ");
		System.out.println("Your player name can have up to 13 characters: ");
		playerName = scanner.nextLine();
		int playerNameLength = playerName.length();
		
		//========================================================================
		/*If the player's name length is higher than 13, then the program will ask
		 * again for a name. Also the player can quit the program just typing
		 * "quit".
		 */
		while(playerNameLength > 13) {
			System.out.println("Your player name is longer than 13 characters.");
			System.out.println("Please try again, or type 'quit' to exit the program.");
			playerName = scanner.nextLine();
			playerNameLength = playerName.length();
			if(playerName.contains("quit")) {
				System.exit(0);
			}
		}
		//========================================================================
		
		//========================================================================
		/*The player's name is fixed to fit in the plain text table. However,
		 * because of time issues, the table was not developed so line 55 it's
		 * commented; that code is unnecessary right now.  
		 */
		int spaces = 13-playerNameLength;
		fullPlayerName = fullPlayerName.substring(0,spaces);
		//playerName = playerName.concat(fullPlayerName);
		//========================================================================
		
		//========================================================================
		//I use Random class to set points randomly
		Random random = new Random();
		//========================================================================
		
		//========================================================================
		/*This while loop sets points randomly to the first nine frames.
		 * There is a 40% chance to make a strike and a 60% chance of no strike.
		 */
		while(tries.length != 18) {
			int percentage = random.nextInt(101)+1;
			if (percentage < 60) {
				/*All points of every single try, it's stored in an array called
				 * tries. When no strike occurs, in this "if" condition, the
				 * player throws the ball two times. The remaining pins after the
				 * previous throw, it's stored in a variable called "remaining"
				 * so the next random numbers of pins knocked down in the second
				 * opportunity will not give us a total above 10. In other words,
				 * thanks to "remaining" variable, it's impossible to randomly
				 * knock down more than 10 pins in these two tries.
				 */
				score = random.nextInt(10);
				tries = Arrays.copyOf(tries, tries.length+1);
				tries[tries.length-1] = score;
				int remaining = 10-score;
				int secondBall = random.nextInt(remaining+1);
				tries = Arrays.copyOf(tries, tries.length+1);
				tries[tries.length-1] = secondBall;
			}else if(percentage >= 60 && percentage <= 100) {
				/*If a strike occurs, the first slot of the frame is null, while
				 * the second slot is 10.
				 */
				tries = Arrays.copyOf(tries, tries.length+1);
				tries[tries.length-1] = null;
				tries = Arrays.copyOf(tries, tries.length+1);
				tries[tries.length-1] = 10;
			}
		}
		//========================================================================
		//For the tenth frame we have three slots. 
		while(tries.length != 21) {
			score = random.nextInt(11);
			//====================================================================
			/*If for the first slot there is no strike, the player throws the ball
			 * two times trying to get a spare.
			 */
			if(score < 10) {
				tries = Arrays.copyOf(tries, tries.length+1);
				tries[tries.length-1] = score;
				int remaining = 10-score;
				int secondBall = random.nextInt(remaining+1);
				tries = Arrays.copyOf(tries, tries.length+1);
				tries[tries.length-1] = secondBall;
				score = random.nextInt(11);
				tries = Arrays.copyOf(tries, tries.length+1);
				tries[tries.length-1] = score;
			
			//====================================================================
			/*If a strike occurs...well, that occurs :) 
			 * The player throws again for the second slot. And if gets a strike
			 * again, then the next throw will be the last.
			 */
			}else {
				tries = Arrays.copyOf(tries, tries.length+1);
				tries[tries.length-1] = score;
				score = random.nextInt(11);
				if(score == 10) {
					tries = Arrays.copyOf(tries, tries.length+1);
					tries[tries.length-1] = score;
					score = random.nextInt(11);
					tries = Arrays.copyOf(tries, tries.length+1);
					tries[tries.length-1] = score;
				/*If for the second slot there is no strike, then the player
				 * throws the ball two time, trying the get at least a spare.
				 */
				}else {
					tries = Arrays.copyOf(tries, tries.length+1);
					tries[tries.length-1] = score;
					int remaining = 10-score;
					int secondBall = random.nextInt(remaining+1);
					tries = Arrays.copyOf(tries, tries.length+1);
					tries[tries.length-1] = secondBall;
				}
			}
		}
		//========================================================================
		/*The game is over and the program shows the player's name and his record.
		 * Unfortunately i don't know how to make the last row that shows the
		 * total score for each frame individually and for the whole game.
		 */
		System.out.println(playerName+" these are the pins you knocked down: "+Arrays.toString(tries));
	}
}
