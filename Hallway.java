import java.awt.Color;
import java.awt.Graphics;
import java.util.Vector;

public class Hallway
{
	public Vector<Rectangle> ojButtons;	// Positions Of Buttons For Oj
	public Vector<Rectangle> blButtons; // Positions Of Buttons For Blue
	public Color b1 = Color.ORANGE;		// Color Of Orange
	public Color b2 = Color.BLUE;		// Color Of Blue
	
	// Constructor
	public Hallway()
	{
		ojButtons = new Vector<Rectangle>();
		blButtons = new Vector<Rectangle>();
		fill();
	}
	
	// Paint The Hallways Of Buttons
	public void drawHallway(Graphics g)
	{
		for(int i = 0; i <= 49; ++i)
		{
			g.setColor(Color.white);
			g.drawString(Integer.toString(i+1), (i+1)*22, 190);
			g.setColor(b1);
			g.fillRect(ojButtons.get(i).x, ojButtons.get(i).y, ojButtons.get(i).width, ojButtons.get(i).height);
		}
		for(int i = 50; i <= 99; ++i)
		{
			g.setColor(Color.white);
			g.drawString(Integer.toString(i+1), (i-49)*22, 270);
			g.setColor(b1);
			g.fillRect(ojButtons.get(i).x, ojButtons.get(i).y, ojButtons.get(i).width, ojButtons.get(i).height);
		}
		for(int i = 0; i <= 49; ++i)
		{
			g.setColor(Color.white);
			g.drawString(Integer.toString(i+1), (i+1)*22, 350);
			g.setColor(b2);
			g.fillRect(blButtons.get(i).x,blButtons.get(i).y, blButtons.get(i).width, blButtons.get(i).height);
		}
		for(int i = 50; i <= 99; ++i)
		{
			g.setColor(Color.white);
			g.drawString(Integer.toString(i+1), (i-49)*22, 430);
			g.setColor(b2);
			g.fillRect(blButtons.get(i).x,blButtons.get(i).y, blButtons.get(i).width, blButtons.get(i).height);
		}
	}
	
	// Initialize The Coordinates/etc. Of The Buttons
	public void fill()
	{		
		for(int i = 1; i <= 50; ++i)
		{
			Rectangle temp = new Rectangle();
			temp.width = 10;
			temp.height = 10;
			temp.x = i*22;
			temp.y = 200;
			ojButtons.add(temp);
		}		
		for(int i = 1; i <=50; ++i)
		{
			Rectangle temp = new Rectangle();
			temp.width = 10;
			temp.height = 10;
			temp.x = i*22;
			temp.y = 240;
			ojButtons.add(temp);
		}		
		for(int i = 1; i <= 50; ++i)
		{
			Rectangle temp = new Rectangle();
			temp.width = 10;
			temp.height = 10;
			temp.x = i*22;
			temp.y = 360;
			blButtons.add(temp);
		}		
		for(int i = 1; i <= 50; ++i)
		{
			Rectangle temp = new Rectangle();
			temp.width = 10;
			temp.height = 10;
			temp.x = i*22;
			temp.y = 400;
			blButtons.add(temp);
		}
	}
}
