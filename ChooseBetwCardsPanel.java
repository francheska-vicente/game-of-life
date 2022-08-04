import javax.swing.*;
import java.awt.*;

/**
	* <p> ChooseBetwCardsPanel manages the creation of the panel that would show the cards that the user can choose from.</p>
	*/	
public class ChooseBetwCardsPanel extends JPanel
{
	@Override
	/** provides the background that the ChooseBetwCardsPanel would use.
		*
		* @param g the graphics object that should be protected
		*/
	protected void paintComponent (Graphics g)
	{
		super.paintComponent (g);
                ImageIcon image = new ImageIcon("images/chooseBetwCards.png");
		g.drawImage (image.getImage(), 0, 0, null);
	}
}