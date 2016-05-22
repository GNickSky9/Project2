import java.awt.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.Vector;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

@SuppressWarnings("serial")
public class Simulation extends JPanel implements ActionListener 
{
	public boolean blPushed;	// Check If Blue Pushed His Button
	public boolean ojPushed;	// Check If Orange Pushed His Button
	public boolean isFinished;	// Check If We Are Done With The Sim.
	private String paintStr;	// Used In Paint Function
	public String sequence;		// Current Sequence
	public Scanner inp;		// Used To Read Input For Sim.
	public static Scanner inpTest;	// Used To Read Input In The Beginning To Fill Vector Of Sequences
	public int numb;		// Number Of Moves In Current Sequence
	public int currSeq;		// Position In Current Sequence
	public int cases;		// Number Of Cases For The Sim.
	public int currCase;		// Current Case
	public Timer tm;		// Timer That Drives The Sim.
	public Robot blBot;		// Blue Robot
	public Robot ojBot;		// Orange Robot
	public Hallway buttons;	// Our Hallways Of Buttons For Each Robot
	public static Vector<String> VEC = new Vector<String>();	// Contains The Sequences For Each Case
	public static int DRIVER = 0;	// Value To Denote Which Sequence To Get From Vector
		
	// Constructor
	public Simulation()
	{
		setBackground(Color.BLACK);
		blPushed = false;
        	ojPushed = false;
        	isFinished = false;
        	currSeq = 0;
        	currCase = 0;
        	numb = -1;
		sequence = new String();
		paintStr = new String();
		buttons = new Hallway();
		blBot = new Robot();
        	ojBot = new Robot();
        	tm = new Timer(5000/30, this);        
        	fill();
        
        	try
		{
			Path file = Paths.get("small.txt");
			inp = new Scanner(file);
		}
		catch(Exception e)
		{
			System.out.println("ERROR OPENING INPUT!");
			e.printStackTrace();
			return;
		}
        
        	cases = inp.nextInt();
	}
	
	// Initialize Coordinates And Dimensions Of Both Robots
	private void fill()
	{
		ojBot.body.x = 22;
		ojBot.body.y = 220;
		ojBot.body.width = 10;
		ojBot.body.height = 10;
		ojBot.color = Color.ORANGE;
		
		blBot.body.x = 22;
		blBot.body.y = 380;
		blBot.body.width = 10;
		blBot.body.height = 10;
		blBot.color = Color.BLUE;
	}
	
	// Check if Current Sequence Is Not Finished
	public boolean isSeqNotDone()
	{
		return currSeq <= numb;
	}
	
	// Check If It Is Blue Robot's Turn To Press Button
	public boolean isBlueTurn()
	{
		return blBot.myTurn == currSeq;
	}
	
	// Check If Blue Robot Is At Next Position In Sequence
	public boolean isBlueAtNext()
	{
		return blBot.nextPos == blBot.currPos;
	}
	
	// Check If It Is Orange Robot's Turn To Press Button
	public boolean isOJTurn()
	{
		return ojBot.myTurn == currSeq;
	}
	
	// Check If Orange Robot Is At Next Position In Sequence
	public boolean isOJAtNext()
	{
		return ojBot.nextPos == ojBot.currPos;
	}
	
	// Function Checks If Current Sequence Isn't Done Yet,
	// Then Checks If It is Blue's Or Orange's Turn To Push The Button,
	// If Either Is True It Pushes The Button And Assigns The Robot
	// A New Next Position And A New Turn Value.
	// If The Current Sequence Is Done, We Read From Input The New 
	// Sequence And Reset Robot's Positions.
	public void determine()
	{
		if(isSeqNotDone())
		{
			if(isBlueTurn())
			{
				if(isBlueAtNext())
				{
					++currSeq;
					blPushed = true;
					blBot.fill();
				}
				else
					blBot.move();
				
				ojBot.move();
				repaint();
			}
			else if(isOJTurn())
			{
				if(isOJAtNext())
				{
					++currSeq;
					ojPushed = true;
					ojBot.fill();
				}
				else
					ojBot.move();
				
				blBot.move();
				repaint();
			}
		}
		else
		{
			getNextSeq();
			++currCase;
			blBot.reset();
			ojBot.reset();
			repaint();
		}
	}
	
	// Gets Sequences For Each Case
	public static void firstTest()
	{
		try
		{
			Path file = Paths.get("small.txt");
			inpTest = new Scanner(file);
		}
		catch(Exception e)
		{
			System.out.println("ERROR OPENING INPUT!");
			e.printStackTrace();
			return;
		}
		int temp = inpTest.nextInt();
		while(inpTest.hasNext())
		{
			VEC.add(inpTest.nextLine());
		}
		VEC.remove(0);
		inpTest.close();
	}
	
	// Parse The Sequence String(s) For Output
	public void parseSeq()
	{
		String str = VEC.get(DRIVER);
		Vector<Character> temp = new Vector<Character>();	
		for(char c : str.toCharArray())
		{
			temp.add(c);
		}
		temp.remove(0);
		temp.remove(0);
		char [] ch = new char[temp.size()];
		for(int i = 0; i < temp.size(); ++i)
			ch[i] = temp.get(i);
			
		sequence = new String(ch);
	}
	
	// Function That Gets Sequence For Next Case
	public void getNextSeq()
	{
		if(inp.hasNext())
		{
			String str = new String();
			int moves = inp.nextInt();
			for(int index = 1; index <= moves; index++)
			{
				str = inp.next();
				int next = inp.nextInt();
				if(str.equals("O"))
					ojBot.makeNew(index, next);
				else if(str.equals("B"))
					blBot.makeNew(index, next);
			}
			parseSeq();
			++DRIVER;
			numb = moves;
			currSeq = 1;
			ojBot.fill();
			blBot.fill();
		}
		else
			isFinished = true;
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		g.setColor(Color.RED);
		g.setFont(new Font("Serif", Font.BOLD, 74));
		g.drawString("Bot Trust Simulation!", 230, 70);
		g.drawLine(230, 72, 918, 72);
		g.setFont(new Font("Serif", Font.PLAIN, 12));
		
		buttons.drawHallway(g);
		
		g.setColor(blBot.color);
		g.fillOval(blBot.body.x, blBot.body.y, blBot.body.width, blBot.body.height);
		g.setColor(ojBot.color);
		g.fillOval(ojBot.body.x, ojBot.body.y, ojBot.body.width, ojBot.body.height);
		
		g.setColor(Color.white);
		g.drawLine(0, 500, 1165, 500);
		g.setFont(new Font("Serif", Font.BOLD, 28));
		g.drawString("Total # Of Cases: " + cases, 150, 530);
		g.drawString("Case #: " + currCase, 0, 530);
		g.drawString("Sequence: " + sequence, 0, 570);
				
		g.setFont(new Font("Serif", Font.BOLD, 28));
	
		if(blPushed)
		{
			g.setColor(Color.WHITE);
			if(blBot.tmp > 50)
			{
				int op = 0;
				op = blBot.currPos + 50;
				paintStr = "Blue Pushed Button " + op;
				g.fillRect(buttons.blButtons.get(blBot.tmp-1).x, buttons.blButtons.get(blBot.tmp-1).y, blBot.body.width, blBot.body.height);
			}
			else
			{
				paintStr = "Blue Pushed Button " + blBot.currPos;
				g.fillRect(buttons.blButtons.get(blBot.currPos-1).x, buttons.blButtons.get(blBot.currPos-1).y, blBot.body.width, blBot.body.height);
			}
			g.drawString(paintStr,0,610);
			blPushed = false;
			blBot.tmp = 0;
			blBot.cnt = 0;
		}
		else if(ojPushed)
		{
			g.setColor(Color.WHITE);
			if(ojBot.tmp > 50)
			{
				int op = 0;
				op = ojBot.currPos + 50;
				paintStr = "Orange Pushed Button " + op;
				g.fillRect(buttons.ojButtons.get(ojBot.tmp-1).x, buttons.ojButtons.get(ojBot.tmp-1).y, ojBot.body.width, ojBot.body.height);
			}
			else
			{
				paintStr = "Orange Pushed Button " + ojBot.currPos;
				g.fillRect(buttons.ojButtons.get(ojBot.currPos-1).x, buttons.ojButtons.get(ojBot.currPos-1).y, ojBot.body.width, ojBot.body.height);
			}
			g.drawString(paintStr,0,610);
			ojPushed = false;
			ojBot.tmp = 0;
			ojBot.cnt = 0;
		}
		else if(isFinished)
		{
			super.paintComponent(g);
			g.setColor(Color.RED);
			g.setFont(new Font("Serif", Font.BOLD, 200));
			g.drawString("FINISHED",90,360);
			close();
			tm.stop();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		determine();
	}
	
	// Start Timer
	public void start()
	{
		tm.start();
	}
	
	// Close Scanner
	public void close()
	{
		inp.close();
	}
	
}
