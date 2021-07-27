import org.junit.Assert;
import org.junit.Test;
public class UserTest {

	
	@Test
	public void testCalculations ()
	{
		User TestedUser=User.getInstance();
		TestedUser.setName("Jani");
		TestedUser.setAge(22);
		TestedUser.setHeight(180);
		TestedUser.setWeight(70);
		TestedUser.setSex(Sex.male);
		//10W + 6.25H - 5A + 5
		double expected=(10*TestedUser.getWeight())+(6.25*TestedUser.getHeight())-(5*TestedUser.getAge())+5;

		Assert.assertEquals(expected,TestedUser.Maintain(),0);
		Assert.assertEquals(expected-500,TestedUser.Lose(),0);
		Assert.assertEquals(expected+350,TestedUser.Gain(),0);
		
	}
}
