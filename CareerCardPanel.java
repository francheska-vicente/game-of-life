import javax.swing.*;
import java.awt.*;

/**
	* <p> CareerCardPanel manages the creation of the panel that would show the CareerCard that the user got. </p>
	*/
public class CareerCardPanel extends JPanel
{
	@Override
	/** provides the background that the CareerCard would use.
		*
		* @param g the graphics object that should be protected
		*/
	protected void paintComponent (Graphics g)
	{
		super.paintComponent (g);
                ImageIcon image = new ImageIcon("images/careerCard.png");
		g.drawImage (image.getImage(), 0, 0, null);
	}
}