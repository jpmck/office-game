/***********************************************************
 * Class    Enemy
 * @author  jpmckenna
 * Purpose:
 ***********************************************************/


public class Enemy extends ImageEntity
{
 // INSTANCE VARIABLES
  public static final int WIDTH = 57;
  public static final int HEIGHT = 80;
  public int DX;
  public int DY;
  private Boolean reverseX, reverseY;
  public String tempString;


 // OBJECT DECLARATIONS


 // CONSTRUCTORS
  public Enemy()
  {
    super();
    reverseX = false;
    reverseY = false;
    DX = 5;
    DY = 5;
  }

  public Enemy(int pX, int pY, int deltaX, int deltaY, String pFileName)
  {
    super(pX, pY, pFileName);
    reverseX = false;
    reverseY = false;
    DX = deltaY;
    DY = deltaY;
    tempString = pFileName;
  }
 // ACCESSORS & MUTATORS


 // PUBLIC METHODS
  public void updateX()
  {
    if (locX + DX + WIDTH > GameManager.WIDTH || locX + DX < 0)
    {
      reverseX = true;
    }
    else if (locX - DX + WIDTH > GameManager.WIDTH || locX - DX < 0)
    {
      reverseX = false;
    }

    if (reverseX)
    {
      locX -= DX;
    }

    else if (!reverseX)
    {
      locX += DX;
    }
  }

  public void updateY()
  {
    if (locY + DY + HEIGHT > GameManager.HEIGHT || locY + DY < 0)
    {
      reverseY = true;
    }
    else if (locY - DY + HEIGHT > GameManager.HEIGHT || locY - DY < 0)
    {
      reverseY = false;
    }

    if (reverseY)
    {
      locY -= DY;
    }

    else if (!reverseY)
    {
      locY += DY;
    }
  }


 // PRIVATE METHODS


}
