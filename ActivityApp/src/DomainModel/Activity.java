package DomainModel;

public class Activity
{
	String theDate;
	String theType;
	int theCaloriesBurned;
	
	
	public Activity(String date, String type, int caloriesBurned)
	{
		theDate = date;
		theType = type;
		theCaloriesBurned = caloriesBurned;
		
	}
	
	public void addActivity()
	{
		
	}
	
	public void deleteActivity()
	{
		
	}
	
	public boolean changeActivity(Activity activity)
	{
		
		
		return true;
	}

	public String getDate() 
	{
		return theDate;
	}

	public String getType() 
	{
		return theType;
	}

	public int getCaloriesBurned() 
	{
		return theCaloriesBurned;
	}

}
