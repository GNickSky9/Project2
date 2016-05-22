public class Rectangle
{
	public int width;
	public int height;
	public int x;
	public int y;
	private static int BASE_WIDTH = 10;
	private static int BASE_HEIGHT = 10;
	
	public Rectangle()
	{
		width = BASE_WIDTH;
		height = BASE_HEIGHT;
		x = 0;
		y = 0;
	}
	
	public Rectangle(int x, int y)
	{
		width = BASE_WIDTH;
		height = BASE_HEIGHT;
		this.x = x;
		this.y = y;
	}
}
