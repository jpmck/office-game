/***********************************************************
 * Class    Sprite
 * @author  jpmckenna
 * Purpose: Abstract class used as a framework for drawing
 *          on screen objects for the game.
 ***********************************************************/

import java.awt.*;

public abstract class Sprite
{
 // INSTANCE VARIABLES
  protected boolean visible;    // is the sprite visible
  protected boolean active;     // is the sprite update-able
  protected double locX, locY;  // x and y location of the object on the screen
  protected double velx, vely;  //velocity of the sprite


 // OBJECT DECLARATIONS


 // CONSTRUCTOR (INIT)


 // ACCESSORS & MUTATORS
  public boolean isVisible()
  {
    return visible;
  }

  public void setVisible(boolean visible)
  {
    this.visible = visible;
  }

  public boolean isActive()
  {
    return active;
  }

  public void setActive(boolean active)
  {
    this.active = active;
  }

  public double getLocx()
  {
    return locX;
  }

  public void setLocx(int locx)
  {
    this.locX = locx;
  }

  public double getLocy()
  {
    return locY;
  }

  public void setLocy(int locy)
  {
    this.locY = locy;
  }

  public double getVelx()
  {
    return velx;
  }

  public void setVelx(double velx)
  {
    this.velx = velx;
  }

  public double getVely()
  {
    return vely;
  }

  public void setVely(int vely)
  {
    this.vely = vely;
  }

 // PUBLIC METHODS
  abstract public void drawOffscreen(Graphics pOffscreenBrush);

 // PRIVATE METHODS

}
