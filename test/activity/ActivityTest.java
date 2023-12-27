package activity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import destination.Destination;
import traveller.Membership;
import traveller.Passenger;

public class ActivityTest {

	@Test
	public void addPassengerTest() throws Exception {
		Passenger passenger = new Passenger("Raunak", "196", Membership.type.STANDARD, 9000);
		Destination destination = new Destination("sdcbshdcbjds");
		Activity activity = new Activity("dnchsbd","dscbhyjsdbcjd",6000,20,destination);
		activity.addPassenger(passenger);
//		assert(passenger.getBalance(),3000);
		assertEquals(passenger.getBalance(), 3000);
	}

}
