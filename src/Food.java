import java.io.Serializable;

public class Food implements Serializable{


	private static final long serialVersionUID = 393812L;
	private String name;
	private double kcal;
	private double portionSize;
	private double amount;
	private double fat;
	private double carbs;
	private double protein;
	
	//Food list contains 100g amounts with appropriate macros and kcal
	//User added foods have their amounts set by user, which in turn sets the kcal and macros
	//FoodPanel/Daily view adds user created foods, with portionSize -1 
	public Food(String name,double kcal,double portionSize,double amount,double fat,double carbs,double protein) 
	{
		//read in from file for combobox amount is always 100g as baseline
		this.name=name;
		this.kcal=kcal;
		this.portionSize=portionSize;
		this.amount=amount;
		this.fat=fat;
		this.carbs=carbs;
		this.protein=protein;
	}
	public String getName() {return name;}
	public double getKcal() {return kcal;}
	public double getPortionSize() {return portionSize;}
	public double getAmount() {return amount;}
	public double getFat() {return fat;}
	public double getCarbs() {return carbs;}
	public double getProtein() {return protein;}
	public String toString() // one line for Daily food
	{
		//amount=amountInput OR portionSize*portionInput stored in amount all the same
		//to get the right kcal and macros is the job of FoodPanel when creating a new food entry
		return name+", "+amount+"g, kcal:"+kcal+", fat:"+fat+", carbs:"+carbs+", protein:"+protein;
	} 
}


