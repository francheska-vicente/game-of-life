import javax.swing.*;
import java.awt.*;

/**
	* <p> SalaryCardPanel manages the creation of the panel that would show the SalaryCard that the user got. </p>
	*/
public class SalaryCardPanel extends JPanel
{
	@Override
	/** provides the background that the SalaryCard would use.
		*
		* @param g the graphics object that should be protected
		*/
	protected void paintComponent (Graphics g)
	{
		super.paintComponent (g);
                ImageIcon image = new ImageIcon("images/salaryCard.png");
		g.drawImage (image.getImage(), 0, 0, null);
	}
}