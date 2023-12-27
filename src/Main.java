import java.io.IOException;

import agency.TravelAgency;
import inputOutputHandler.InputOutputHandler;

public class Main {

	public static void main (String[] args) throws IOException {
		// TODO Auto-generated method stub
		System.out.println("Hello there");
		TravelAgency agency=new TravelAgency();
		InputOutputHandler inputHandler= new InputOutputHandler(agency);
		inputHandler.processInput();
	}

}
