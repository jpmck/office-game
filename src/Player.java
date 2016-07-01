/***********************************************************
 * Class    Player
 * @author  jpmckenna
 * Purpose:
 ***********************************************************/


public class Player extends ImageEntity
{
 // INSTANCE VARIABLES
  public static final int WIDTH = 57;
  public static final int HEIGHT = 80;
  private Boolean atWall;


 // CONSTRUCTORS
  public Player()
  {
    super();
    atWall = false;
  }

  public Player(int pX, int pY, String pFileName)
  {
    super(pX, pY, pFileName);
    atWall = false;
  }


 // PUBLIC METHODS

  public void updateX(int pPos)
  {
    // if the player location + it's x movement + width goes off screen...
    if ((locX + pPos + WIDTH > GameManager.WIDTH && pPos > 0) ||
      (locX - pPos < 0 && pPos < 0))
    {
      // ...player is at the wall
      atWall = true;
    }
    // else, the player is in the room...
    else
    {
      atWall = false;
    }

    // if the player isn't at a wall...
    if (!atWall)
    {
      // ...allow the player to move
      locX += pPos;
    }
  }

  public void updateY(int pPos)
  {
    if ((locY + pPos + HEIGHT > GameManager.HEIGHT && pPos > 0) ||
      (locY - pPos < 0 && pPos < 0))
    {
      atWall = true;
    }
    else
    {
      atWall = false;
    }

    if (!atWall)
    {
      locY += pPos;
    }
  }


 // PRIVATE METHODS


}
