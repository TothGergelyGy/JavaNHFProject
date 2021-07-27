import org.junit.Assert;
import org.junit.Test;
public class FoodTest {
	
	@Test
	public void testGetters ()
	{
		Food food=new Food("Tojás",10,1,140,25,45,23); 
		Assert.assertEquals("Tojás",food.getName());
		Assert.assertEquals(10.0,food.getKcal(),0);
		Assert.assertEquals(140.0,food.getAmount(),0);
		Assert.assertEquals(25.0,food.getFat(),0);
		Assert.assertEquals(45.0,food.getCarbs(),0);
		Assert.assertEquals(23.0,food.getProtein(),0);
		
	}
}
