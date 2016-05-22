import java.awt.Color;
import java.util.LinkedList;

public class Robot
{
	public Rectangle body; // Coordinates, Width, And Height Of Robot
	public int currPos;	   // Current Position
	public Color color;	   // Color Of Robot
	public int myTurn;	   // Robot's Turn To Push Button In Sequence
	public int nextPos;    // Next Position Robot Has To Move To
	public LinkedList<Pair> mySeq;	// Sequence For The Robot's Moves And Turns
	public int tmp = 0;
	public int cnt = 0;
		
	// Constructor
	public Robot()
	{
		body = new Rectangle();
		currPos = 1;
		mySeq = new LinkedList<Pair>();
	}
	
	// Move Function
	public void move()
	{
		if(nextPos > 50)
		{
			if(cnt == 0)
				tmp = nextPos;
			nextPos -= 50;
		}
		if(currPos < nextPos && body.x != 1100)
		{
			body.x += 22;
			++currPos;
		}
		else if(currPos > nextPos && body.x != 22)
		{
			body.x -= 22;
			--currPos;
		}
		++cnt;
	}
	
	// Reset Robot's Position/etc.
	void reset()
	{
		body.x = 22;
		currPos = 1;
		tmp = 0;
		cnt = 0;
	}
	
	// Get Next Position And Turn Value For Robot From His Sequence
	void fill()
	{
		if(!mySeq.isEmpty())
		{	
			Pair temp = mySeq.removeFirst();		
			this.nextPos = temp.getSecond();
			this.myTurn = temp.getFirst();
		}
	}
	
	// Add New Position And Turn Value To Robot's Sequence
	void makeNew(int x, int y)
	{
		Pair temp = new Pair(x,y);
		mySeq.add(temp);
	}
	
}
