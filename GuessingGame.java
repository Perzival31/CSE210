package package1;

import java.util.*;

class Bounds {

	public int Lower = 0;
	public int Upper = 0;

}

public class  GuessingGame {
	/*
	 * Erik Nielsen erik.nielsen@bellevuecollege.edu 
	 * Student ID: 950696790 
	 * May 22nd, 2019 
	 * The purpose of this program is to guess a users number 
	 * There are two methods for AI to use to guess number 
	 * One is guess(); which randomly guesses numbers but takes into account new UB and LB 
	 * The other is getAnswer(); which uses a simple algorithm to find the answer quicker.
	 * There are two methods for User to answer AI
	 * AskUserManual(); asks the user every time if the number is higher or lower
	 * Getplayeranswer(); and Checkplayeranswer(): and asks user to input their number and will automatically tell AI if number is higher or lower.
	 */
	public static void main(String[] args) {
		int playeranswer = 0;
		boolean playagain = true;

		do { // Loop to play again
			Bounds bounds = getBounds();
			// int playeranswer = getplayerAnswer(bounds.Lower, bounds.Upper); //Method to
			// get user answer for auto guess
			int Turns = AIGuess(bounds, playeranswer);

			System.out.println("Yay I got the correct answer in " + Turns + " turn(s)!"); // AI outputting that its answer was correct
			playagain = getPlayagain(); // Method to ask user if they want to play again

		} while (playagain);
	}

	public static int getUpper() {
		System.out.print("\nPlease input upper bound: ");
		Scanner input = new Scanner(System.in);
		int Upperbound = input.nextInt();
		return Upperbound;
	}

	public static int getLower() {
		System.out.print("\n Please input lower bound: ");
		Scanner input = new Scanner(System.in);
		int Lowerbound = input.nextInt();
		return Lowerbound;
	}

	public static int guess(int LB, int UB) {
		int Difference = UB - LB;
		Random rand = new Random();
		int Rand = rand.nextInt(Difference);
		return Rand + LB;
	}

	public static int getAIAnswer(int LB, int UB) {
		int Difference = UB - LB;
		int HalfDiff = Difference / 2;
		int AIanswer = UB - HalfDiff;
		return AIanswer;
	}

	public static int AskUserManual() {
		int HigherorLower = -1;
		do {
			Scanner input = new Scanner(System.in);
			if (HigherorLower == -2) {
				System.out.println("Incorrect input, please type again");
			}
			String UserinputString = input.next();
			UserinputString = UserinputString.toUpperCase();
			if (UserinputString.startsWith("H")) {
				HigherorLower = 1;
			} else if (UserinputString.startsWith("L")) {
				HigherorLower = 2;
			} else if (UserinputString.startsWith("C")) {
				HigherorLower = 0;
			} else {
				HigherorLower = -2;
			}

		} while (HigherorLower != 0 && HigherorLower != 1 && HigherorLower != 2);

		return HigherorLower;
	}

	public static int getplayerAnswer(int LB, int UB) {
		int validnumber = 1;
		int usernumber = 0;
		System.out.println("Please input your number");
		do {
			Scanner input = new Scanner(System.in);
			if (validnumber == 2) {
				System.out.println("Number must be between bounds, try again.");
			}

			usernumber = input.nextInt();
			if (usernumber >= LB && usernumber <= UB) {
				validnumber = 0;
			} else {
				validnumber = 2;
			}

		} while (validnumber != 0);

		return usernumber;
	}

	public static int checkplayerAnswer(int AIanswer, int playeranswer) {
		int HigherorLower = 0;
		if (AIanswer > playeranswer) {
			HigherorLower = 1;
		}

		if (AIanswer < playeranswer) {
			HigherorLower = 2;
		}

		return HigherorLower;
	}

	public static boolean getPlayagain() {
		Scanner input = new Scanner(System.in);
		System.out.println("Would you like to play again? (Yes or No)");
		boolean playagain = false;
		boolean correctinput = false;
		do {
			String InputPlayagain = input.next();
			InputPlayagain = InputPlayagain.toUpperCase();
			if (InputPlayagain.startsWith("Y")) {
				playagain = true;
				correctinput = true;
				return playagain;
			} else if (InputPlayagain.startsWith("N")) {
				correctinput = true;
				return playagain;
			}

			System.out.println("Incorrect input, please try again.");
		} while (!correctinput);

		return playagain;
	}

	public static Bounds getBounds() {
		Bounds bounds = new Bounds();
		boolean correctbound = false; // Variable for User inputing valid boundries for AI

		do { // Loop for getting Upperbound and Lowerbound
			bounds.Lower = getLower();
			bounds.Upper = getUpper();
			if (bounds.Upper - bounds.Lower < 20) { // Tests to see if LB and UB are far enough apart
				correctbound = false;
				System.out.println("Please enter an upperbound and lowerbound with a difference more that 20");
			}

			else if (bounds.Lower <= 0) { // Tests to see if LB is greater than 0
				correctbound = false;
				System.out.println("Please enter a lowerbound greater than 0");
			} else {
				correctbound = true;
			} // Sets correct bound to true if correct input is applied.
		} while (!correctbound);

		return bounds;
	}

	public static int AIGuess(Bounds bounds, int playeranswer) {
		int Turns = 0; // Counts turns it takes for AI to guess
		int Userinput = 0; // Variable for AI guessing the correct number
		do { // Loop to for AI to guess
			int AIanswer = guess(bounds.Lower, bounds.Upper); // Uses random number generator for getting AI answer
			// int AIanswer = getAIAnswer(bounds.Lower, bounds.Upper); //Gets AIanswer using a better algorithm
			System.out.println("Is " + AIanswer + ", correct, higher or lower?"); // Asks user if answer is correct
			Userinput = AskUserManual(); // Method to get user input each time
			// Userinput = checkplayerAnswer(AIanswer, playeranswer); //For use with getplayerAnswer and checkplayerAnswer

			if (Userinput == 1) { // Sets new UB if user inputs that number is higher that what the AI said
				bounds.Upper = AIanswer;
				// System.out.println("It is Lower"); //Only for use with getplayerAnswer and checkplayerAnswer
			}

			if (Userinput == 2) { // Sets new LB if user inputs that number is higher that what the AI said
				bounds.Lower = AIanswer;
				// System.out.println("It is Higher"); //Only for use with getplayerAnswer and checkplayerAnswer
			}
			Turns++;
		} while (Userinput != 0); // Ends loop if answer is correct

		return Turns;
	}
}
