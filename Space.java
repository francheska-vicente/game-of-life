/**
	* <p> Space holds the assigned number to the space. This allows the different spaces to be differentiated with respect due to this number assigned. </p>
	*/
public abstract class Space
{
	protected int number;

	/** creates the space given the color and number (if Orange or Blue space)
		*
		* @param number is the assigned number to the space (1 - 60)
		*/
	public Space (int number)
	{
		this.number = number;
	}

	/** returns the number assigned to the space 
		* @return number (1 - 60) that represents that space 
		*/
	public int getNumber ()
	{
		return number;
	}
}