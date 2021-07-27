import org.junit.Assert;
import org.junit.Test;
public class DayTest {

	@Test
	public void testAggregate() {
		Food food=new Food("Tojás",  5,1,50,500,5000,50000); 
		Food food2=new Food("Tojás2",6,1,60,600,6000,60000); 
		Day day=new Day();
		day.addFood(food);
		day.addFood(food2);


		Assert.assertEquals(11.0,day.getAggregateKcal(),0);
		Assert.assertEquals(1100.0,day.getAggregateFat(),0);
		Assert.assertEquals(11000.0,day.getAggregateCarb(),0);
		Assert.assertEquals(110000.0,day.getAggregateProt(),0);
		
	}
	
	
	@Test
	public void testRemove() {
		Food food=new Food("Tojás",5,1,50,500,5000,50000); 
		Day day=new Day();
		day.addFood(food);
		String[] name={"Tojás","50"};
		day.removeFood(name);
		

		Assert.assertTrue(day.getFoods().isEmpty());
		
	}
}
