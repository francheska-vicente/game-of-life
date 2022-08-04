import java.util.*;

/**
	* <p> CareerCard holds the information by a player for his career.
	* These information includes the type of career, the degree requirement, pay raise range of a career, and the pay raise limit of a career.</p>
	*/
public class CareerCard
{	
	private static final String[] CAREERS = {"Lawyer", "Accountant", "Computer Consultant", "Doctor", "Server", "Racecar Driver", "Athlete"};

	private int career;
  private boolean degreeReq;
	private int [] payRaiseRange;
  private int payRaiseLimit;

	/** creates a career card given the career, degreeReq, pay raise minimum, pay raise maximum
		* @param career is the number corresponding to the career 
		* @param degreeReq true if degree is required; false otherwise
		* @param prMin is the minimum number of times a player can have a pay raise
		* @param prMax is the maximum number of times  player can have a pay raise 
		*/
	public CareerCard (int career, boolean degreeReq, int prMin, int prMax)
	{
    this.career = career;
    this.degreeReq = degreeReq;
		payRaiseRange = new int[2];
    payRaiseRange[0] = prMin;
    payRaiseRange[1] = prMax;
    payRaiseLimit = generatePayRaiseLimit();
  }
	
	/** returns the career indicated in the card
		*
		* @return the career name 
		*/
	public String getCareer ()
	{
		return CAREERS [career]; 
	}

	/** returns the degree requirement of a career
		*
		* @return true if degree is required; false otherwise 
		*/
	public boolean getDegreeReq ()
	{
		return degreeReq; 
	}

	/** returns the minimum number of times a player can have a pay raise
		*
		* @return the minimum pay raise
		*/
  public int getPayRaiseMinRange ()
	{
		return payRaiseRange[0]; 
	}
  
	/** returns the maximum number of times  player can have a pay raise 
		*
		* @return the maximum pay raise
		*/
  public int getPayRaiseMaxRange ()
	{
		return payRaiseRange[1]; 
	}
  
	/** returns the generated pay raise limit, which is in the range of pay raise minimum and pay raise maximum
		*
		* @return pay raise limit of the career
		*/
  public int getPayRaiseLimit ()
	{
		return payRaiseLimit; 
	}

	/** generates a number of times that a player can have a pay raise 
		*
		* @return the generated number of times that a player can have pay raise
		*/
  private int generatePayRaiseLimit ()
  {
    return Game.randomNumberGenerator(payRaiseRange[0], payRaiseRange[1]);
  }

	@Override
	/** returns the information in the career card
		*
		* @return a string in for format "Career : " + CAREERS [career] + (degreeReq ? "\nDegree Required." : "") + "\nPay Raise maximum value: " + payRaiseLimit
 		*/
	public String toString ()
	{
		return "Career: " + CAREERS [career] + (degreeReq ? "\nDegree Required." : "") + "\nPay Raise maximum value: " + payRaiseLimit;
  }
}