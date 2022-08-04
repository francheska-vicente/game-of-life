import java.util.*;

/**
	* <p> Path handles the creation of the spaces that would be needed to create a path. It allows the access of the spaces that are in an ArrayList. </p>
	*/
public class Path
{
	private final int NO_OF_SPACES;
	private int currentIndex;
	private String name;
	private ArrayList<Space> spaces;

	/** creates a path gives its name and the number of spaces.
		* @param name is the name of the path
		* @param count is the number of spaces in the path
		*/
	public Path (String name, int count)
	{
		this.name = name;
		NO_OF_SPACES = count;
		currentIndex = -1;

		spaces = new ArrayList<>(NO_OF_SPACES);
	}

	/** adds blue and orange spaces in the path 
		*
		* @param color is the color of the space that would be added
		* @param count is the number assiged to the space that would be created
		*/
	public void addSpace (String color, int count)
	{
		if (color.equalsIgnoreCase ("Blue"))
			spaces.add (new BlueSpace (count));
		else
			spaces.add (new OrangeSpace (count));
	}

	/** adds magenta and green spaces in the path 
		*
		* @param color is the color of the space that would be added
		* @param count is the number assiged to the space that would be created
		* @param type is the a special instruction of what the space would do
		*/
	public void addSpace (String color, int count, String type)
	{
		if (color.equalsIgnoreCase ("Magenta"))
			spaces.add (new MagentaSpace (count, type));
		else 
			spaces.add (new GreenSpace (count, type));
	}

	/** returns the name of the path
		*
		* @return the assigned name of the path
		*/
	public String getName () 
	{
		return name;
	}

	/** returns the number of spaces
		*
		* @return number of spaces in the path
		*/
	public int getMaxNoOfSpaces ()
	{
		return NO_OF_SPACES;
	}

	/** returns the current index of the space
		*
		* @return the current index
		*/
	public int getCurrentIndex ()
	{
		return currentIndex;
	}

	/** returns the space assigned to the current index 
		*
		* @return the space at the currentIndex
		*/
	public Space getSpace ()
	{
		return spaces.get (currentIndex);
	}

	/** sets the currentIndex to the specified  number
		*
		* @param number is the number that would be assigned to the currentIndex
		*/
	public void setCurrentIndex (int number)
	{
		currentIndex = number;
	}

	@Override
	/** returns a formatted string that holds the information in Path
		* @return a string in the format of: name + " (" + (currentIndex + 1)+ "/" + NO_OF_SPACES + ")
		*/
	public String toString ()
	{
		return name + " (" + (currentIndex + 1)+ "/" + NO_OF_SPACES + ")\n";
	}
}