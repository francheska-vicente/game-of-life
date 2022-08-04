import javax.swing.*;
import java.awt.*;

/**
	* <p> ActionCardPanel manages the creation of the panel that would show the ActionCard that the user got. </p>
	*/
public class ActionCardPanel extends JPanel
{
	@Override
	/** provides the background that the ActionCard would use.
		*
		* @param g the graphics object that should be protected
		*/
	protected void paintComponent (Graphics g)
	{
		super.paintComponent (g);
                ImageIcon image = new ImageIcon("images/actionCard.png");
		g.drawImage (image.getImage(), 0, 0, null);
	}
}