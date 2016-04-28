import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class VisualGameboard extends JFrame implements Runnable{

	private Rectangle board;
	private Rectangle feedback;
	private int size_pegs_on_board = 85;
	private int size_candidate_pegs = 85;
	private int size_feedback_pegs = 45;
	public Game game;
	
	Color backgroundColor;
	Color pegs[];

	public VisualGameboard(Game game)
	{
		this.game = game;
		setSize(600, 1300);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}
	
	@Override
	public void run() 
	{
		backgroundColor = new Color(158,145,126);
		board = new Rectangle(0,0,600,1300);
		feedback = new Rectangle(480,0,120,1200);
		while(true) 
		{
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			repaint();
		}
	}
	

	public void start()
	{
		System.out.println("vsgameboard running...");
	}
	
	@Override
	public void paint(Graphics g)
	{
		Graphics2D g2 = (Graphics2D)g;
		
		g2.setColor(backgroundColor);
		g2.fill(board);
		g2.setColor(Color.CYAN);
		g2.fill(feedback);
		for(int i = 50, i1 = 0; i <= 1150; i = i+100, i1++)
		{
			for(int j = 60, j1 = 0; j<=420; j = j + 120, j1++)
			{
				int x = j - size_pegs_on_board/2;
				int y = i - size_pegs_on_board/2;
				if(game.gameboard.gameboard[i1][j1] != null)
				{
					if(game.gameboard.gameboard[i1][j1].equals("B")) g2.setColor(Color.BLUE);
					else if(game.gameboard.gameboard[i1][j1].equals("G")) g2.setColor(Color.GREEN);
					else if(game.gameboard.gameboard[i1][j1].equals("O")) g2.setColor(Color.ORANGE);
					else if(game.gameboard.gameboard[i1][j1].equals("P")) g2.setColor(new Color(160,32,240));
					else if(game.gameboard.gameboard[i1][j1].equals("R")) g2.setColor(Color.RED);
					else if(game.gameboard.gameboard[i1][j1].equals("Y")) g2.setColor(Color.YELLOW);

				}
				else g2.setColor(Color.BLACK);
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

//		for(int i = 25, i1 = 0,i2 = 0; i <= 1175; i = i+50, i1++)
//		{
//			int x1 = 510 - size_feedback_pegs/2;
//			int x2 = 570 - size_feedback_pegs/2;
//			int y = i - 20;
//			if(i2 == 3) i2 = 0;
//			
//			if(game.gameboard.feedback[i1/2][i2] != null)
//			{
//				if(game.gameboard.gameboard[i1][i2].equals("B")) g2.setColor(Color.BLUE);
//				else if(game.gameboard.feedback[i1][i2].equals("G")) g2.setColor(Color.GREEN);
//				else if(game.gameboard.feedback[i1][i2].equals("O")) g2.setColor(Color.ORANGE);
//				else if(game.gameboard.feedback[i1][i2].equals("P")) g2.setColor(new Color(160,32,240));
//				else if(game.gameboard.feedback[i1][i2].equals("R")) g2.setColor(Color.RED);
//				else if(game.gameboard.feedback[i1][i2].equals("Y")) g2.setColor(Color.YELLOW);
//			}
//			else g2.setColor(Color.BLACK);
//			g2.fillOval(x1, y, size_feedback_pegs, size_feedback_pegs);
//			i2++;
//			
//			if(game.gameboard.feedback[i1/2][i2] != null)
//			{
//				if(game.gameboard.gameboard[i1][i2].equals("B")) g2.setColor(Color.BLUE);
//				else if(game.gameboard.feedback[i1][i2].equals("G")) g2.setColor(Color.GREEN);
//				else if(game.gameboard.feedback[i1][i2].equals("O")) g2.setColor(Color.ORANGE);
//				else if(game.gameboard.feedback[i1][i2].equals("P")) g2.setColor(new Color(160,32,240));
//				else if(game.gameboard.feedback[i1][i2].equals("R")) g2.setColor(Color.RED);
//				else if(game.gameboard.feedback[i1][i2].equals("Y")) g2.setColor(Color.YELLOW);
//			}
//			else g2.setColor(Color.BLACK);
//			g2.fillOval(x2, y, size_feedback_pegs, size_feedback_pegs);
//			i2++;
//		}

		
		
	}

}
