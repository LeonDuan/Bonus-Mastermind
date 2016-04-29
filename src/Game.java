import java.util.ArrayList;
import java.util.Scanner;

public class Game {
	public static boolean firstGame = true;
	public int num_guesses_left = 12;
	public int max_num_guesses_allowed = 12;
	public int num_columns = 4;
	public String guess = "";
	public boolean win = false;
	public boolean gameover = false;
	public Gameboard gameboard;
	public int current_num_of_guess = 0;
	public static boolean testmode = false;
	public ArrayList<String> history = new ArrayList<String>();
	public ArrayList<String> replies = new ArrayList<String>();

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Do you want to play in developer mode (you can see the secret code when playing. (Y/N): ");
		String test = sc.nextLine();
		while (test == null || (test.equals("Y") || test.equals("N")) == false)
		{
			System.out.print("\nInvalid input. Please Enter again.\nDo you want to play in developer mode (you can see the secret code when playing. (Y/N): ");
			test = sc.nextLine();
		}

		if(test.equals("Y")) testmode = true;
		Game game = new Game();
		game.runGame();
		game.gameover = true;
		if (game.win == true) {
			System.out.println("\nYou win!");
		} else {
			System.out.println("\nYou lose!");
		}
		firstGame = false;
		while (true) {
			System.out.print("\nDo you want to play again?(Y/N): ");
			String choice = sc.nextLine();
			while ((choice.equals("Y") || choice.equals("N")) == false) {
				System.out.print("\nInvalid input. Please try again.\nDo you want to play again?(Y/N): ");
				choice = sc.nextLine();
			}
			if (choice.equals("Y")) {
				Game newgame = new Game();
				newgame.runGame();
				newgame.gameover = true;
				if (newgame.win == true) {
					System.out.println("You win!");
				} else {
					System.out.println("You lose!");
				}
			} else {
				sc.close();
				System.out.println("\nBye");
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.exit(0);
			}
		}
	}

	public Game() {
		gameboard = new Gameboard(12, 4);
		gameboard.game = this;
	}

	public Game(int max_guesses, int num_colomns) {
		gameboard = new Gameboard(max_guesses, num_colomns);
		gameboard.game = this;
	}

	private void runGame() {
		Scanner sc = new Scanner(System.in);
		if (firstGame)
			greeting();
//ask customization
		System.out.print(
				"\nDo you want to customize the number of maximum guesses and the number of holes per row? If not, the default values are 12 and 4.\nA visual game board will be presented for the default settings! Enter your choice(Y/N): ");
		String customize = sc.nextLine();
		while ((customize.equals("Y") || customize.equals("N")) == false) {
			System.out.print("\nInvalid input. Please enter again.\nDo you want to customize the number of maximum guesses and the number of holes per row? If not, the default values are 12 and 4.\nA visual game board will be presented for the default settings! Enter your choice (Y/N): ");
			customize = sc.nextLine();
		}
		if (customize.equals("Y")) {
			customize(sc);
		}
		
//show JFrame
		if(max_num_guesses_allowed == 12 && num_columns == 4)
		{
			new Thread(new VisualGameboard(this)).start();
		}
		
		
		
		System.out.println("Generating secret code ....\n");
		gameboard.generateCode();
		while (num_guesses_left > 0) {
			current_num_of_guess++;
			System.out.print("*************************************\nYou have " + num_guesses_left);
			if (num_guesses_left == 1)
				System.out.println(" guess left.");
			else
				System.out.println(" guesses left.");
			System.out.print(
					"What is your next guess?\nType in the characters for your guess and press enter.\nEnter guess: ");
			guess = sc.nextLine();
			if (guess.equals("history")) {
				printHistory();
				System.out.print(
						"What is your next guess?\nType in the characters for your guess and press enter.\nEnter guess: ");
				guess = sc.nextLine();
			}
			while (isInputValid(guess) == false) {
				System.out.print("\nInvalid input. Please enter again.\nWhat is your next guess?\nFollow the input rules and type in the characters for your guess and press enter.\nEnter guess: ");
				guess = sc.nextLine();
			}
			history.add(guess);
			gameboard.putInPegs(guess);
			String result = gameboard.getCurrentResult();
			replies.add(result);
			System.out.println(guess + " -> Result: " + result);
			if (testmode == true)
				System.out.println(gameboard.codeString + "(Secret Code)");
			if (Character.getNumericValue(result.charAt(0)) == num_columns) {
				win = true;
				break;
			}
			System.out.println("\n");
			num_guesses_left--;
		}
	}

	private boolean isInputValid(String guess) {
		if (guess.length() != num_columns)
			return false;
		for (int i = 1; i <= guess.length(); i++) {
			if (guess.charAt(i - 1) == 'B' || guess.charAt(i - 1) == 'G' || guess.charAt(i - 1) == 'O'
					|| guess.charAt(i - 1) == 'P' || guess.charAt(i - 1) == 'R' || guess.charAt(i - 1) == 'Y')
				continue;
			else
				return false;
		}
		return true;
	}

	private void printHistory() {
		System.out.println("\nHistory for this game: \n");
		for (int i = 0; i < history.size(); i++) {
			System.out.println(history.get(i));
			System.out.println(replies.get(i) + "\n");
		}
	}

	private void greeting() {
		Scanner sc = new Scanner(System.in);
		String rules = "Welcome to Mastermind.  Here are the rules.\n\nThis is a text version of the classic board game Mastermind.\nThe computer will think of a secret code. The code consists of 4 colored pegs.\nThe pegs may be one of six colors: blue, green, orange, purple, red, or yellow. A color may appear more than once in the code. You try to guess what colored pegs are in the code and what order they are in.   After you make a valid guess the result (feedback) will be displayed.\nThe result consists of a black peg for each peg you have guessed exactly correct (color and position) in your guess.  For each peg in the guess that is the correct color, but is out of position, you get a white peg.  For each peg, which is fully incorrect, you get no feedback.\n\nOnly the first letter of the color is displayed. B for Blue, R for Red, and so forth.\nWhen entering guesses you only need to enter the first character of each color as a capital letter.\n\nYou have 12 guesses to figure out the secret code or you lose the game. \n\nThe description above is the default settings. You will be given the opportunity to change the number of guesses and pegs per row. \n\nAre you ready to play? (Y/N): ";
		System.out.print(rules);
		String input = sc.nextLine();

		while ((input.equals("Y") || input.equals("N")) == false) {
			System.out.println("\nInvalid Input. Please enter again");
			input = sc.nextLine();
		}

		if (input.equals("N")) {
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
	private void customize(Scanner sc)
	{
		System.out.println("Please enter the number of maximum guesses(>0): ");
		String str_max_num_guesses_allowed = sc.nextLine();
		while (true) {
			try {
				max_num_guesses_allowed = Integer.parseInt(str_max_num_guesses_allowed);
				if (max_num_guesses_allowed <= 0)
					throw new NumberFormatException();
				break;
			} catch (NumberFormatException e) {
				System.out.println("Please enter a positive integer: ");
				str_max_num_guesses_allowed = sc.nextLine();
			}
		}
		num_guesses_left = max_num_guesses_allowed;
		System.out.println("Please enter the number of holes in each row: ");
		String str_num_columns = sc.nextLine();
		while (true) {
			try {
				num_columns = Integer.parseInt(str_num_columns);
				if (num_columns <= 0)
					throw new NumberFormatException();
				break;
			} catch (NumberFormatException e) {
				System.out.println("Please enter a positive integer: ");
				str_num_columns = sc.nextLine();
			}
		}
		gameboard.gameboard = new Peg[max_num_guesses_allowed][num_columns];
		gameboard.feedback = new Peg[max_num_guesses_allowed][num_columns];
		gameboard.code = new Peg[num_columns];
	}
}
