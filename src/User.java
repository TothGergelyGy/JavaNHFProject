import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

enum Sex { male, female }

public class User implements Serializable {
	
	private class Data implements Serializable 
	{
		private static final long serialVersionUID = 1L;
		private String name;
		private double height;//cm
		private Sex sex;
		private double age; 	//double because multiplied by double in equation
		private double weight; 	//kg
		private ArrayList<Day> days; 
		private LocalDate Today;
		private void fillDays() //create empty days starting from last date
		{
			LocalDate lastdate=days.get(days.size()-1).getDate();
			Today=LocalDate.parse(LocalDate.now().toString());
			if (lastdate.isBefore(Today))					//check if last day.date=today
				while (!lastdate.isEqual(Today)) 			//if not add day last+1 dated day
				{
					days.add(new Day(lastdate.plusDays(1)));
					lastdate=days.get(days.size()-1).getDate();
				}
		}
		private Data() 
		{
			name="";
			days=new ArrayList<Day>();
			days.add(new Day("2020-11-29"));
			fillDays();
		}
	}
	public Data data;//must be public for serialization
	private static User single_instance = null; 
	private LocalDate SelectDate=LocalDate.now(); //the 
	private User() 
	{
		data=new Data();
	}
	public static User getInstance() 
    { 
        if (single_instance == null) 
            single_instance = new User(); 
  
        return single_instance; 
    } 
	public String getName() 		{return data.name;}
	public void setName(String a) 	{data.name=a;}
	public double getHeight() 		{return data.height;}
	public void setHeight(double a) {data.height=a;}
	public Sex getSex() 			{return data.sex;}
	public void setSex(Sex a) 		{data.sex=a;}
	public double getAge() 			{return data.age;}
	public void setAge(double a) 		{data.age=a;}
	public double getWeight() 		{return data.weight;}
	public void setWeight(double a) {data.weight=a;}

	public Day getToday() //Today button selectedDay=user.getToday()
	{
		return data.days.get(data.days.size()-1);
	} 
	public LocalDate getSelectDate() {return SelectDate;}
	public void setSelectDate(String a) {SelectDate=LocalDate.parse(a);}
	public ArrayList<Food> getSelectDateFoods() //this adds foods list to the list on FoodPanel
	{
			for (Day i:data.days)
				if (SelectDate.isEqual(i.getDate()))
					return  i.getFoods();
			return new ArrayList<Food>();
	}
	public Day getSelectDateDay() //get specific day, used in adding Food to FoodPanel,aggregate kcals
	{
		for (Day i:data.days)
			if (i.getDate().isEqual(SelectDate))
				return i; 
		return new Day();
	}
	public ArrayList<Day> getAllDays() {return data.days ;} //get all days, used in adding to Trends
	public double Maintain() 
	{
		if(data.sex==Sex.male) //magic equation explained at bottom
			return (10*data.weight + 6.25*data.height - 5*data.age + 5);
			else
			return (10*data.weight + 6.25*data.height - 5*data.age - 161);
		
	}
	public double Lose() {return Maintain()-500;}
	public double Gain() {return Maintain()+350;}
	//Saves the inner data segment, "save state" to load at a later date, while keeping outer User class singleton
	public void Save(String filename) 
	{
		try
        {
            FileOutputStream fos = new FileOutputStream(filename);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(data);
            oos.close();
            fos.close();
        } 
        catch (IOException ioe) 
        {
            ioe.printStackTrace();
        }
	}
	//Loads the inner data segment from file, keeping the singletonness of User outer class
	public void Load(String filename) 
	{
		try
        {
            FileInputStream fis = new FileInputStream(filename);
            ObjectInputStream ois = new ObjectInputStream(fis);
            data = (Data) ois.readObject();
            ois.close();
            fis.close();
            data.fillDays();
            SelectDate=data.Today;
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
            return;
        } 
	}
	
}



/* CALORIE INTAKE EQUATION
Mifflin-St Jeor Equation:
For men:
BMR = 10W + 6.25H - 5A + 5
For women:
BMR = 10W + 6.25H - 5A - 161
W is body weight in kg
H is body height in cm
A is age
F is body fat in percentage 
*/