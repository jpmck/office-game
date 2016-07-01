# The Office Game

> _The Office Game was an object-oriented Java program written as the final project for my object-oriented programming course while in school._

In “The Office Game,” the player (Jim from _The Office_) has to move around the Dunder Mifflin office collecting paper supply order sheets, while trying to avoid his fellow officemates (Dwight, Creed, Kelly, and Toby).  As Jim moves about the office he collects 300 points (the elusive “Schrute Bucks”) for each order sheet he picks up.  However, bumping into his officemates causes him to lose points while he’s distracted by them.  After collecting 1200 points (the equivalent of four order sheets), Pam appears in the office.  If Jim goes to Pam, she will allow him to pick an officemate to distract, thus eliminating them from the office.  The game is over when Jim picks up all ten of his order sheets.

The game is run by the class `GameManager`, which controls all aspects of the game and keeps track of all other classes that are used by the game.  The `GameManager` creates a `Player` object for the player, as well as for the power up player (Pam).  The `GameManager` also creates an ArrayList of `Paper` as well as an ArrayList of `Enemy` objects.  The `GameManager` also sets all appropriate variables to their needed values.  Control is then handed off to the `start()` method.

The `start()` method in the `GameManager` is responsible for starting the background music and creating a new Thread called “loop” which is then started.  The loop method calls the `run()` method in `GameManager`.  Within the `run()` method, the game is put to sleep every 60 milliseconds to smooth the animation of the game.  The `update()` method is called within `run()`, which is the main “powerhouse” of the game.

`update()` is in charge of clearing the off screen canvas, calling the `DrawPapers()` method to instruct the papers to draw themselves on the off screen canvas, calling `updateAndDrawEnemies()` method to do the same and drawing the player on the off screen canvas.  If the parameters for drawing Pam are met, she is drawn on the off screen canvas.  Collisions are detected using the `checkCollisions()` method and the off screen canvas is then drawn to the screen.

The sprite class and its subclasses are simple and self-documented and shown in the UML diagram on the previous page.  These classes are used to represent the onscreen objects that the player can interact with (as well as representing the player himself).
