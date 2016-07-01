/***********************************************************
 * Class    ImageEntity
 * @author  jpmckenna
 * Purpose: Class that represents image based objects that
 *          can be manipulated in the game.
 ***********************************************************/


import java.awt.*;
import java.awt.geom.*;
import java.net.*;


public class ImageEntity extends Sprite
{
 // INSTANCE VARIABLES


 // OBJECT DECLARATIONS
  protected Image image;           // Image used to store the imported *.png
    protected AffineTransform at;  // AffineTransform used to manipulate the image
    protected Graphics2D g2d;      // Graphics used to draw the image offscreen
    protected String filename;     // String of incoming *.png file name

 // CONSTRUCTORS
    public ImageEntity()
    {
      // default constructor
    }

    public ImageEntity(int pLocx, int pLocy, String pFileName)
    {
      setLocx(pLocx);
      setLocy(pLocy);
      filename = pFileName;
      at = new AffineTransform();
      at.setToIdentity();
    }


 // ACCESSORS & MUTATORS
    public Image getImage()
    {
      return image;
    }

    public void setImage(Image image)
    {
        this.image = image;
    }


 // PUBLIC METHODS
  public double getCenterX()
    {
        return getLocx() + image.getWidth(null) / 2.0;
    }

    public double getCenterY()
    {
        return getLocy() + image.getHeight(null) / 2.0;
    }

    public void loadImage()
    {
      URL url = null;
      Toolkit tk = Toolkit.getDefaultToolkit();
        url = this.getClass().getResource(filename);
        image = tk.getImage(url);
    }


    public void drawOffscreen(Graphics pOffScreenBrush)
    {
      Graphics2D g2d = (Graphics2D)pOffScreenBrush;
      g2d.drawImage(image, (int)getLocx(), (int)getLocy(), null);
    }

    public Rectangle getBounds()
    {
        Rectangle r;
        r = new Rectangle((int)getLocx(), (int)getLocy(), image.getWidth(null),
            image.getHeight(null));
        return r;
    }

 // PRIVATE METHODS


}
