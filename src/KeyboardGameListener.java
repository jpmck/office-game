/***********************************************************
 * Class    KeyboardGameListener
 * @author  jpmckenna
 * Purpose:
 ***********************************************************/

import java.awt.event.*;

import javax.swing.JApplet;

public class KeyboardGameListener implements KeyListener
{
  //The player representation
  private Player thePlayer;
  private JApplet theApplet;

  public KeyboardGameListener(Player pPlayer, JApplet pApplet)
  {
    //Need to assign the object that I passed into
    //my listener to the one I declared above to
    //avoid a null pointer exception
    thePlayer = pPlayer;
    theApplet = pApplet;
  }

  //Handle the events of the player using the left and right arrow
  public void keyPressed(KeyEvent e)
  {
    if (e.getKeyCode()==(KeyEvent.VK_LEFT))
    {

      thePlayer.updateX(-5);
    }

    if (e.getKeyCode()==(KeyEvent.VK_RIGHT))
    {
      thePlayer.updateX(5);
    }

    if (e.getKeyCode()==(KeyEvent.VK_UP))
    {
      thePlayer.updateY(-5);
    }

    if (e.getKeyCode()==(KeyEvent.VK_DOWN))
    {
      thePlayer.updateY(5);
    }

    theApplet.repaint();

  }

  @Override
  public void keyReleased(KeyEvent arg0)
  {
    // TODO Auto-generated method stub
  }

  @Override
  public void keyTyped(KeyEvent arg0)
  {
    // TODO Auto-generated method stub
  }

}
