/***********************************************************
 * Class    x
 * @author  jpmckenna
 * Purpose: 	
 ***********************************************************/
//import java.awt.*;

public class Paper extends ImageEntity
{
	
	public static final int WIDTH = 57;
	public static final int HEIGHT = 80;
	
	//**********************************************************
	//Constructors
	public Paper()
	{
		super();
	}
	
	public Paper(int pX, int pY, String pFileName)
	{
		super(pX, pY, pFileName);
	}

	//Update the X and Y positions of the player when the arrow keys
	//are pressed -- but this only keeps track of the positions, doesn't
	//do any of the drawing, the RectSprite does that for us
	public void updateX(int pPos)
	{
		locX = locX + pPos;
		if (locX > GameManager.WIDTH)
		{
			locX = 0;
		}
		else if (locX < 0)
		{
			locX = GameManager.WIDTH;
		}	
	}
	
	public void updateY(int pPos)
	{
		locY = locY + pPos;
		if (locY > GameManager.HEIGHT)
		{
			locY = 0;
		}
		else if (locY < 0)
		{
			locY = GameManager.HEIGHT;
		}
	}
}