import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class VisualGameboard extends JPanel implements Runnable{

	private Rectangle board;
	private Rectangle feedback;
	private int size_pegs_on_board = 75;
	private int size_candidate_pegs = 75;
	private int size_feedback_pegs = 35;
	public Game game;
	
	Color backgroundColor;
	Color pegs[];

	public VisualGameboard(Game game)
	{
		this.game = game;	
	}
	
	
	@Override
	public void run() 
	{
		JFrame frame = new JFrame("Mastermind");
		frame.add(this);
		frame.setVisible(true);
		frame.setSize(600, 1350);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
		
		backgroundColor = new Color(158,145,126);
		board = new Rectangle(0,0,600,1300);
		feedback = new Rectangle(480,0,120,1200);
		while(true) 
		{
			if(game.gameover == true) 
			{
				frame.setVisible(false);
				frame.dispose();
			}
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			repaint();
		}
	}

	@Override
	public void paint(Graphics g)
	{
		Graphics2D g2 = (Graphics2D)g;
		
		g2.setColor(backgroundColor);
		g2.fill(board);
		g2.setColor(Color.CYAN);
		g2.fill(feedback);
		
		g2.setColor(Color.BLACK);
		for(int i = 100; i<= 1200; i = i + 100)
		{
			g2.fill(new Rectangle(0, i - 2, 600, 5));
		}
		g2.fill(new Rectangle(478, 0, 5, 1200));
		
		for(int i = 1150, i1 = 0; i >=50; i = i-100, i1++)
		{
			for(int j = 60, j1 = 0; j<=420; j = j + 120, j1++)
			{
				int x = j - size_pegs_on_board/2;
				int y = i - size_pegs_on_board/2;
				if(game.gameboard.gameboard[i1][j1] != null)
				{
					if(game.gameboard.gameboard[i1][j1].color.equals("B")) g2.setColor(Color.BLUE);
					else if(game.gameboard.gameboard[i1][j1].color.equals("G")) g2.setColor(Color.GREEN);
					else if(game.gameboard.gameboard[i1][j1].color.equals("O")) g2.setColor(Color.ORANGE);
					else if(game.gameboard.gameboard[i1][j1].color.equals("P")) g2.setColor(new Color(160,32,240));
					else if(game.gameboard.gameboard[i1][j1].color.equals("R")) g2.setColor(Color.RED);
					else if(game.gameboard.gameboard[i1][j1].color.equals("Y")) g2.setColor(Color.YELLOW);

				}
				else g2.setColor(Color.DARK_GRAY);
				g2.fillOval(x,y,size_pegs_on_board,size_pegs_on_board);
			}
		}
		for(int i = 50; i<=550; i = i+100)
		{
			int x = i - size_candidate_pegs/2;
			int y = 1250 - size_candidate_pegs/2;
			if(i == 50) g2.setColor(Color.BLUE);
			else if(i == 150) g2.setColor(Color.GREEN);
			else if(i == 250) g2.setColor(Color.ORANGE);
			else if(i == 350) g2.setColor(new Color(160,32,240));
			else if(i == 450) g2.setColor(Color.RED);
			else if(i == 550) g2.setColor(Color.YELLOW);
			g2.fillOval(x, y, size_candidate_pegs, size_candidate_pegs);
		}
		
		
		
		for(int i = 1100, pegIndex_X = 0, pegIndex_Y = 0; i >= 0; i = i-100, pegIndex_X ++, pegIndex_Y = 0)
		{
			int x1 = 510 - size_feedback_pegs/2 , x2 = 570 - size_feedback_pegs/2 , x3 = 510 - size_feedback_pegs/2, x4 = 570 - size_feedback_pegs/2;
			int y1  = (i + 25) - size_feedback_pegs/2, y2 = (i + 25) - size_feedback_pegs/2, y3 = (i+75) - size_feedback_pegs/2, y4 = (i+75) - size_feedback_pegs/2;
			
			if(game.gameboard.feedback[pegIndex_X][pegIndex_Y] != null)
			{
				if(game.gameboard.feedback[pegIndex_X][pegIndex_Y].color.equals("B")) g2.setColor(Color.BLACK);
				else if(game.gameboard.feedback[pegIndex_X][pegIndex_Y].color.equals("W")) g2.setColor(Color.WHITE);
			}
			else g2.setColor(Color.DARK_GRAY);
			g2.fillOval(x1, y1, size_feedback_pegs, size_feedback_pegs);
			pegIndex_Y ++;
			
			if(game.gameboard.feedback[pegIndex_X][pegIndex_Y] != null)
			{
				if(game.gameboard.feedback[pegIndex_X][pegIndex_Y].color.equals("B")) g2.setColor(Color.BLACK);
				else if(game.gameboard.feedback[pegIndex_X][pegIndex_Y].color.equals("W")) g2.setColor(Color.WHITE);
			}
			else g2.setColor(Color.DARK_GRAY);
			g2.fillOval(x2, y2, size_feedback_pegs, size_feedback_pegs);
			pegIndex_Y ++;
			
			if(game.gameboard.feedback[pegIndex_X][pegIndex_Y] != null)
			{
				if(game.gameboard.feedback[pegIndex_X][pegIndex_Y].color.equals("B")) g2.setColor(Color.BLACK);
				else if(game.gameboard.feedback[pegIndex_X][pegIndex_Y].color.equals("W")) g2.setColor(Color.WHITE);
			}
			else g2.setColor(Color.DARK_GRAY);
			g2.fillOval(x3, y3, size_feedback_pegs, size_feedback_pegs);
			pegIndex_Y ++;
			
			
			if(game.gameboard.feedback[pegIndex_X][pegIndex_Y] != null)
			{
				if(game.gameboard.feedback[pegIndex_X][pegIndex_Y].color.equals("B")) g2.setColor(Color.BLACK);
				else if(game.gameboard.feedback[pegIndex_X][pegIndex_Y].color.equals("W")) g2.setColor(Color.WHITE);
			}
			else g2.setColor(Color.DARK_GRAY);
			g2.fillOval(x4, y4, size_feedback_pegs, size_feedback_pegs);
		}
	}
}
