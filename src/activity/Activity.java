package activity;

import destination.Destination;
import traveller.Membership;
import traveller.Passenger;

public class Activity{

	private String name;
	private String description;
	private Destination spot;
	private double cost;
	private int capacity;
	private int filledSlots;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Destination getSpot() {
		return spot;
	}

	public void setSpot(Destination spot) {
		this.spot = spot;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}
	
	public int getCapacity() {
		return capacity;
	}

	public Activity(String name,
			String desc,
			double cost,
			int capacity,
			Destination spot) {
        this.setName(name);
        this.setDescription(desc);
        this.setCost(cost);
        this.setSpot(spot);
        this.capacity = capacity;
        this.filledSlots=0;
    }
	
	public void updateSlots() {
		// TODO Auto-generated method stub
		this.filledSlots++;
	}
	
	public void selectActivity() {
		// TODO Auto-generated method stub
		if(hasAvailableSlots()) {
			updateSlots();
			return;
		}
		System.out.println("No available slots. Please select another activity");
		
	}
	
	public void addPassenger(Passenger passenger) throws Exception{
		if(!this.hasAvailableSlots()) {
			throw new Exception("Activity slots filled");
		}
		double requestedPrice=this.getPrice(passenger.getMemberType());
		if(passenger.getBalance()<requestedPrice)
			throw new Exception("Insufficient balance to perform activity");
		this.updateSlots();
		passenger.setBalance(passenger.getBalance()-requestedPrice);
	}

	public boolean hasAvailableSlots() {
		// TODO Auto-generated method stub
		if(filledSlots>=capacity)
			return false;
		return true;
	}

	public int getAvailableSlots() {
		// TODO Auto-generated method stub
		return (capacity-filledSlots);
	}
	
	public double getPrice(Membership.type memType) {
		if(memType == Membership.type.GOLD)
			return 0;
		if(memType == Membership.type.STANDARD)
			return getCost();
		double price=(getCost()*0.9);
		return price;
	}

	
	
}
