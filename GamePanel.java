import javax.swing.*;
import java.awt.*;

/**
	* <p> GamePanel manages the creation of the panel that would show the view of the main Game. </p>
	*/
public class GamePanel extends JPanel
{
	@Override
	/** provides the background that the Game would use.
		*
		* @param g the graphics object that should be protected
		*/
	protected void paintComponent (Graphics g)
	{
		super.paintComponent (g);
                ImageIcon image = new ImageIcon("images/game.png");
		g.drawImage (image.getImage(), 0, 0, null);
	}
}