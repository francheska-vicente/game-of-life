import javax.swing.*;
import java.awt.*;

/**
	* <p> HouseCardPanel manages the creation of the panel that would show the ActionCard that the user got. </p>
	*/
public class HouseCardPanel extends JPanel
{
	@Override
	/** provides the background that the HouseCard would use.
		*
		* @param g the graphics object that should be protected
		*/
	protected void paintComponent (Graphics g)
	{
		super.paintComponent (g);
                ImageIcon image = new ImageIcon("images/houseCard.png");
		g.drawImage (image.getImage(), 0, 0, null);
	}
}