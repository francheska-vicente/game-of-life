import java.util.*;

public class Main
{
	public static void main (String [] args)
	{
		Game model = new Game ();
		GameView view = new GameView ();
		ControllerHub controller = new ControllerHub (model, view);
  }
}