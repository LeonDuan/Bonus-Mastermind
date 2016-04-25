package bonus;

import java.util.Random;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class Game {
	public static boolean test = false;
	public static boolean firstGame = true;
	public int num_guesses_left = 12;
	public int max_num_guesses_allowed = 12;
	public int num_columns = 4;
	public String guess = "";
	public boolean win = false;
	public Gameboard gameboard;
	public int current_num_of_guess = 0;
	public static boolean testmode = false;

	public static void main(String[] args) {
		testmode = true;
		Scanner sc = new Scanner(System.in);
		Game game = new Game();
		game.runGame();
		if(game.win == true)
		{
			System.out.println("You win!");
		}
		else
		{
			System.out.println("You lose!");
		}
		firstGame = false;
		while(true)
		{
			System.out.println("Do you want to play again?");
			String choice = sc.nextLine();
			while((choice.equals("Y") || choice.equals("N")) == false) choice = sc.nextLine();
			if(choice.equals("Y"))
			{
				Game newgame = new Game();
				newgame.runGame();
				if(newgame.win == true)
				{
					System.out.println("You win!");
				}
				else
				{
					System.out.println("You lose!");
				}
			}
			else
			{
				System.out.println("Bye");
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.exit(0);
			}
		}
	}
	public Game()
	{
		gameboard = new Gameboard(12, 4);
		gameboard.game = this;
	}
	public Game(int max_guesses, int num_colomns)
	{
		gameboard = new Gameboard(max_guesses, num_colomns);
		gameboard.game = this;
	}
	
	private void runGame()
	{
		if(firstGame) greeting();
		System.out.println("Generating secret code ....");
		gameboard.generateCode();
		while(num_guesses_left > 0)
		{
			current_num_of_guess ++;
			System.out.print("You have " + num_guesses_left);
			if(num_guesses_left == 1) System.out.println(" guess left.");
			else System.out.println(" guesses left.");
			Scanner sc = new Scanner(System.in);
			System.out.print("What is your next guess?\nType in the characters for your guess and press enter.\nEnter guess: ");
			guess = sc.nextLine();
			while(isInputValid(guess) == false) 
			{
				System.out.print("Invalid input. Please enter again: ");
				guess = sc.nextLine();
			}
			gameboard.putInPegs(guess);
			String result = gameboard.getCurrentResult();
			System.out.println(guess + " -> Result: " + result);
			if(testmode == true) System.out.println(gameboard.codeString);
			if(result.equals("4 black pegs and 0 white peg."))
			{
				win = true;
				break;
			}
			System.out.println("\n");
			num_guesses_left --;
		}
	}
	


	private boolean isInputValid(String guess) {
		if(guess.length() != num_columns) return false;
		for(int i = 1; i <= guess.length(); i++)
		{
			if(guess.charAt(i-1) == 'B' || guess.charAt(i-1) == 'G' || guess.charAt(i-1) == 'O' || guess.charAt(i-1) == 'P' || guess.charAt(i-1) == 'R' || guess.charAt(i-1) == 'Y') continue;
			else return false;
		}
		return true;
	}

	private void greeting()
	{
		Scanner sc = new Scanner(System.in);
		String rules = "Welcome to Mastermind.  Here are the rules.\n\nThis is a text version of the classic board game Mastermind.\nThe computer will think of a secret code. The code consists of 4 colored pegs.\nThe pegs may be one of six colors: blue, green, orange, purple, red, or yellow. A color may appear more than once in the code. You try to guess what colored pegs are in the code and what order they are in.   After you make a valid guess the result (feedback) will be displayed.\nThe result consists of a black peg for each peg you have guessed exactly correct (color and position) in your guess.  For each peg in the guess that is the correct color, but is out of position, you get a white peg.  For each peg, which is fully incorrect, you get no feedback.\n\nOnly the first letter of the color is displayed. B for Blue, R for Red, and so forth.\nWhen entering guesses you only need to enter the first character of each color as a capital letter.\n\nYou have 12 guesses to figure out the secret code or you lose the game.  Are you ready to play? (Y/N): ";
		System.out.println(rules);
		String input = sc.nextLine();

		while((input.equals("Y") || input.equals("N")) == false)
		{
			System.err.println("Invalid Input. Please enter again");
			input = sc.nextLine();
		}

		if(input.equals("N"))
		{
			sc.close();
			System.out.println("Exiting game...");
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.exit(0);
		}
	}
}
