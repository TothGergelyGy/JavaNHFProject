import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class Day implements Serializable{

	private static final long serialVersionUID = 363266L;
	private ArrayList<Food> foods;
	private LocalDate date;
	
	public Day()  
	{
		date=LocalDate.now();
		foods=new ArrayList<Food>();
	}
	public Day(String a) //used in Data() to set starting day
	{
		date=LocalDate.parse(a);
		foods=new ArrayList<Food>();
	}
	public Day(LocalDate prevDay) //this is used in filling userinfo's days with empty days
	{
		date=prevDay;
		foods=new ArrayList<Food>();
	}
	public LocalDate getDate() {return date;}
	public void addFood(Food a) {foods.add(a);} //FOODPANEL ADD throught userinfo
	public void removeFood(String[] a) //foodpanel delete based on selected added food
	{
		Double amount=Double.parseDouble(a[1]);
		for (int i=0; i<foods.size(); i++) 
		{
			if (foods.get(i).getName().equals(a[0]) && foods.get(i).getAmount()==amount)
			{
				foods.remove(i);
				return;
			}
		}
	} //FOODPANEL DELETE 
	public ArrayList<Food> getFoods() {return foods;} //return food list, used only in FoodPanel's list
	public double getAggregateKcal() //these are the values FoodPanel shows as output
	{
		double addedKcal=0;
		for (Food i:foods) 
		{
			addedKcal+=i.getKcal();
		}
		return Math.round(addedKcal*100)/100.0d;
	}
	public double getAggregateFat()
	{
		double addedFat=0;
		for (Food i:foods) 
		{
			addedFat+=i.getFat();
		}
		return Math.round(addedFat*100)/100.0d;
	}
	public double getAggregateCarb()
	{
		double addedCarb=0;
		for (Food i:foods) 
		{
			addedCarb+=i.getCarbs();
		}
		return Math.round(addedCarb*100)/100.0d;
	}
	public double getAggregateProt()
	{
		double addedProt=0;
		for (Food i:foods) 
		{
			addedProt+=i.getProtein();
		}
		return Math.round(addedProt*100)/100.0d;
	}
	public String toString() //one line for Trends
	{ 
		if (foods.isEmpty())
			return date.toString()+" -";
		return date.toString()+" kcal:"+getAggregateKcal()+" fat:"+getAggregateFat()+" carbs:"+getAggregateCarb()+" Protein:"+getAggregateProt();
	} 
	
}

