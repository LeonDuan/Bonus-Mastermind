import java.util.Random;

public class Gameboard {
	public Peg gameboard[][];
	public Peg feedback[][];
	public Peg code[];
	public String codeString = "";
	public Game game;
	
	public Gameboard(int guessesAllowed, int numColumns)
	{
		gameboard = new Peg[guessesAllowed][numColumns];
		feedback = new Peg[guessesAllowed][numColumns];
		code = new Peg[numColumns];
		
	}
	
	public void generateCode()
	{
		Random rand = new Random();
		for(int i = 0; i < game.num_columns; i++)
		{
			int color = rand.nextInt(6);
			if(color == 0)
			{
				code[i] = new Peg("B");
				codeString = codeString.concat("B");
			}
			else if(color == 1)
			{
				code[i] = new Peg("G");
				codeString = codeString.concat("G");
			}
			else if(color == 2)
			{
				code[i] = new Peg("O");
				codeString = codeString.concat("O");
			}
			else if(color == 3)
			{
				code[i] = new Peg("P");
				codeString = codeString.concat("P");
			}
			else if(color == 4)
			{
				code[i] = new Peg("R");
				codeString = codeString.concat("R");
			}
			else if(color == 5)
			{
				code[i] = new Peg("Y");
				codeString = codeString.concat("Y");
			}
		}
	}
	
	public void putInPegs(String guess) {
		for(int i = 0; i < guess.length(); i++)
		{
			Peg peg = new Peg(String.valueOf(guess.charAt(i)));
			gameboard[game.current_num_of_guess-1][i] = peg;
		}
		generateFeedback(guess);
	}
	
	private void generateFeedback(String guess)
	{
		String tempCodeString = codeString;
		String tempGuess = guess;
		String feedbackString = "";
		
		//generate black pegs
		for(int i = 0; i<guess.length(); i++)
		{
			if(codeString.charAt(i) == guess.charAt(i))
			{
				feedbackString = feedbackString.concat("B");
				tempCodeString = tempCodeString.substring(0,i) + "1" + tempCodeString.substring(i+1);
				tempGuess = tempGuess.substring(0,i) + "2" + tempGuess.substring(i+1);
			}
		}
		//generate white pegs
		for(int i = 0; i<tempGuess.length(); i++)
		{
			for(int j = 0; j<tempCodeString.length(); j++)
			{
				if(tempGuess.charAt(i) == tempCodeString.charAt(j))
				{
					feedbackString = feedbackString.concat("W");
					tempCodeString = tempCodeString.substring(0,j) + "3" + tempCodeString.substring(j+1);
					tempGuess = tempGuess.substring(0,i) + "4" + tempGuess.substring(i+1);
					break;
				}
			}
		}
		
		for(int i = 0; i < feedbackString.length(); i++)
		{
			feedback[game.current_num_of_guess-1][i] = new Peg(String.valueOf(feedbackString.charAt(i)));
		}
	}

	public String getCurrentResult() {
		int black = 0;
		int white = 0;
		String result = "";
		for(int i = 0; i< feedback[game.current_num_of_guess-1].length; i++)
		{
			if(feedback[game.current_num_of_guess-1][i] == null) break;
			if(feedback[game.current_num_of_guess-1][i].color.equals("B")) black ++;
			else if(feedback[game.current_num_of_guess-1][i].color.equals("W")) white ++;
		}
		result = result.concat(Integer.toString(black));
		if(black == 0 || black == 1) result = result.concat(" black peg and ");
		else result = result.concat(" black pegs and ");
		
		result = result.concat(Integer.toString(white));
		if(white == 0 || white == 1) result = result.concat(" white peg.");
		else result = result.concat(" white pegs.");
		
		return result;
	}
}
