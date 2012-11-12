import static org.junit.Assert.*;

import org.junit.Test;


public class LoginTestCase {

	@Test
	public void test() {
		int[] expecteds = {1,2,3};
		int[] actuals = {1,2,3};
		assertArrayEquals(expecteds, actuals);
	}

}
