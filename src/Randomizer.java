/***********************************************************
 * Class    Randomizer
 * @author  jpmckenna
 * Purpose: Class that provides random items that can be
 *          asked for by an object.
 ***********************************************************/

import java.util.*;

public class Randomizer
{
  private Random rand;
  private int randNum;

  public Randomizer()
  {
    rand = new Random();
    randNum = 0;
  }

  public int intWithConstraint(int maxNum, int constraint)
  {
    randNum = rand.nextInt(maxNum);

    while (randNum > GameManager.WIDTH/2 - Player.WIDTH*2 &
         randNum < GameManager.WIDTH/2 + Player.WIDTH*2 ||
         randNum > GameManager.HEIGHT/2 - Player.HEIGHT*2 &
         randNum < GameManager.HEIGHT/2 + Player.HEIGHT*2)
    {
      randNum = rand.nextInt(maxNum);
    }

    if (randNum > maxNum - constraint)
    {
      randNum -= constraint;
    }

    return randNum;
  }

  public int intNeg5to5No0()
  {
    randNum = -6 + rand.nextInt(12);

    if (randNum !=0)
    {
      return randNum;
    }

    else
    {
      return 1;
    }
  }

}
