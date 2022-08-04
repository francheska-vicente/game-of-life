import javax.swing.*;
import java.awt.*;

public class BuyHousePanel extends JPanel
{
	@Override
	protected void paintComponent (Graphics g)
	{
		super.paintComponent (g);
                ImageIcon image = new ImageIcon("images/buyHouse.png");
		g.drawImage (image.getImage(), 0, 0, null);
	}
}