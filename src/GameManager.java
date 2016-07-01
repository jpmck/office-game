/***********************************************************
 * Class    GameManager
 * @author  jpmckenna
 * Purpose:
 ***********************************************************/


import java.awt.*;
import java.io.File;
import java.util.*;
import javax.sound.sampled.*;
import javax.swing.JApplet;
import javax.swing.JOptionPane;

@SuppressWarnings("serial")


public class GameManager extends JApplet implements Runnable
{
 // INSTANCE VARIABLES
  public static final int WIDTH = 800;     // width of the window
  public static final int HEIGHT = 600;    // height of the window
  private int score;                       // player score
  private Boolean running;                 // is the game running
  private Boolean jim;                     // are you good at games?
  private Boolean pamAvailable;
  private Boolean pamOnScreen;
  private Boolean pamUp;


 // OBJECT DECLARATIONS
  private Thread loop;                       // thread that allows player/enemies to be drawn
  private Image offscreenCanvas;             // offscreen canvas for double buffering
  private Graphics offscreenBrush;           // brush to paint on the offscreen canvas
  private KeyboardGameListener keyListener;  // keylistener for moving the player
  private Player thePlayer;                  // player object (Jim)
  private Player thePam;
  private ArrayList<Enemy> theEnemies;       // arrayList to hold Enemy objects (Dundies)
  private ArrayList<Paper> thePapers;        // ArrayList to hold the Paper objects
  private Randomizer rando;                  // randomizer that provides various random things
  private static File theme = new File("theme.wav");

 // CONSTRUCTOR (INIT)
  public void init()
  {
    // set the width, height, color and status of the screen
    this.setSize(WIDTH, HEIGHT);
    this.setBackground(Color.LIGHT_GRAY);

    // create the image for off screen drawing, and grab it's brush
    offscreenCanvas = createImage(WIDTH, HEIGHT);
    offscreenBrush = offscreenCanvas.getGraphics();

    // initialize a new randomizer
    rando = new Randomizer();

    // initialize a new player, add a keyListener to it and the game
    thePlayer = new Player((WIDTH/2)-28, (HEIGHT/2)-40, "jim.png");
    thePam = new Player((WIDTH/2)-28, (HEIGHT/2)-40, "pam.png");
    keyListener=new KeyboardGameListener(thePlayer, this);
    this.addKeyListener(keyListener);

    // call createPapers and createEnemies to initialize both groups
    createPapers();
    createEnemies();

    // set the score and set the game as running
    score = 0;
    running = true;

    // Set Pam as available, and her powers off
    pamAvailable = true;
    pamOnScreen = false;
    pamUp = false;

    //Opening dialogs
    Object[] options = {"Jim?", "Jody?"};
    int n = JOptionPane.showOptionDialog(this, "Is your name...", "Inane warning",
        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options,  options[0]);

    if (n==0)
    {
      jim = true;
      JOptionPane.showMessageDialog(this, "You are about to start.  Ready?", "Inane warning",
        JOptionPane.QUESTION_MESSAGE);
    }
    else
    {
      jim = false;
      JOptionPane.showMessageDialog(this, "Hi Jody, I think this is going to be easy!\n" +
        "\"That's what she said!!\"\n\n" + "\"Not now Dwight!\"", "Inane warning",
        JOptionPane.QUESTION_MESSAGE);
    }
  }


 // THREAD METHODS
  public void start()
  {
    // set the status of the window to start
    this.showStatus("Start!");

    playSound(theme);

    // instantiate a new thread to draw on the screen and start it
    loop = new Thread(this);
    loop.start();
  }

  public void run()
  {
    // while the game is running...
    while(running)
    {
      // try to...
      try
      {
        // ... let the thread sleep so the game isn't on crack
        Thread.sleep(60);
      }
      catch(Exception e)
      {
        System.out.println("I can't sleep....");
      }
      // call update to redraw everything
      update();
    }

    // update everything once to get rid of any left over paper
    update();

    if (thePapers.isEmpty())
    {
      JOptionPane.showMessageDialog(null, "Fact: Bears eat beets.  Bears.  Beets.  Battlestar Galactica.", "Inane winning",
            JOptionPane.WARNING_MESSAGE);
      offscreenBrush.drawString("Score: " + Integer.toString(score)+ " points! You Win!", 300, 20);
    }
    else
    {
      JOptionPane.showMessageDialog(null, "Idiot.", "Inane warning",
            JOptionPane.ERROR_MESSAGE);
      offscreenBrush.drawString("Score: 0 points! You Lose!", 300, 20);
    }
  }


 // PUBLIC METHODS
  public void paint(Graphics g)
  {
    g.drawImage(offscreenCanvas,0,0,this);
  }

 // PRIVATE METHODS
  private void createEnemies()
  {
    // instantiate ArrayList of enemies
    theEnemies = new ArrayList<Enemy>();

    // add the enemies using theRandomizer
    theEnemies.add(new Enemy(rando.intWithConstraint(WIDTH, Enemy.WIDTH),
        rando.intWithConstraint(HEIGHT, Enemy.HEIGHT),
        rando.intNeg5to5No0()*2, rando.intNeg5to5No0()*2,"dwight.png"));
    theEnemies.add(new Enemy(rando.intWithConstraint(WIDTH, Enemy.WIDTH),
        rando.intWithConstraint(HEIGHT, Enemy.HEIGHT),
        rando.intNeg5to5No0(), rando.intNeg5to5No0(), "creed.png"));
    theEnemies.add(new Enemy(rando.intWithConstraint(WIDTH, Enemy.WIDTH),
        rando.intWithConstraint(HEIGHT, Enemy.HEIGHT),
        rando.intNeg5to5No0(), rando.intNeg5to5No0(), "toby.png"));
    theEnemies.add(new Enemy(rando.intWithConstraint(WIDTH, Enemy.WIDTH),
        rando.intWithConstraint(HEIGHT, Enemy.HEIGHT),
        rando.intNeg5to5No0(), rando.intNeg5to5No0(), "kelly.png"));

    // for all of the enemies in the ArrayList...
    for (int i=0; i<theEnemies.size(); i++)
    {
      // load the image from the filename string
      theEnemies.get(i).loadImage();

    }
  }

  private void createPapers()
  {
    // instantiate ArrayList of papers
    thePapers = new ArrayList<Paper>();

    // for 10 times...
    for (int i=0; i<10; i++)
    {
      // add a new Paper to the ArrayList using theRandomizer
      thePapers.add(new Paper(rando.intWithConstraint(WIDTH, Paper.WIDTH),
          rando.intWithConstraint(HEIGHT, Paper.HEIGHT), "paper.png"));
      // load the image into the Paper objects in the ArrayList
      thePapers.get(i).loadImage();
    }
  }

  private void update()
  {
    this.requestFocus();
    offscreenBrush.clearRect(0,0,WIDTH, HEIGHT);

    DrawPapers();
    updateAndDrawEnemies();

    thePlayer.loadImage();
    thePlayer.drawOffscreen(offscreenBrush);

    checkCollisions();

    if (score>1199 & pamAvailable)
    {
      thePam.loadImage();
      thePam.drawOffscreen(offscreenBrush);
      pamOnScreen = true;
    }

    repaint();

    if (score < 0 || thePapers.isEmpty())
    {
      running = false;
    }
  }

  private void updateAndDrawEnemies()
  {
    for (int i=0; i<theEnemies.size(); i++)
    {
      if (!pamUp)
      {
        theEnemies.get(i).updateX();
        theEnemies.get(i).updateY();
      }
      theEnemies.get(i).drawOffscreen(offscreenBrush);
    }
  }

  private void DrawPapers()
  {
    for (int i=0; i<thePapers.size(); i++)
    {
      thePapers.get(i).drawOffscreen(offscreenBrush);
    }
  }



  private void checkCollisions()
  {
    Rectangle playerBoundingBox = thePlayer.getBounds();
    Rectangle objectsBoundingBox;

    if (pamOnScreen)
    {
      objectsBoundingBox = thePam.getBounds();

      if (playerBoundingBox.intersects(objectsBoundingBox))
      {
        score += 500;
        thePam = null;
        pamUp = true;
        pamAvailable = false;
        pamOnScreen = false;
      }
    }

    for (int i=0; i<theEnemies.size(); i++)
    {
      objectsBoundingBox = theEnemies.get(i).getBounds();

      if (playerBoundingBox.intersects(objectsBoundingBox))
      {
        if (!pamUp & jim)
        {
          score -= 10;
        }
        if (pamUp)
        {
          score += 300;
          theEnemies.remove(theEnemies.get(i));
          pamUp = false;
          pamAvailable = false;
        }
      }
    }

    for (int i=0; i<thePapers.size(); i++)
    {
      objectsBoundingBox = thePapers.get(i).getBounds();

      if (playerBoundingBox.intersects(objectsBoundingBox))
      {
        score += 300;
        thePapers.remove(thePapers.get(i));
      }
    }
    offscreenBrush.drawString("Score: " + Integer.toString(score)+ " points!", 300, 20);
  }

  // I got this from stackoverflow and modified it a little bit
  public static synchronized void playSound(final File soundIn)
  {

    new Thread(new Runnable()
    {
      public void run()
      {
        try
        {
          Clip clip = AudioSystem.getClip();
          AudioInputStream inputStream = AudioSystem.getAudioInputStream(soundIn);
          clip.open(inputStream);
          clip.start();
        }

        catch (Exception e)
        {
          System.err.println(e.getMessage());
        }
      }
    }
    ).start();
  }
}
