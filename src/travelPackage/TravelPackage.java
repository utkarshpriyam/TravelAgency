package travelPackage;

import java.util.ArrayList;

import destination.Destination;
import traveller.Passenger;

public class TravelPackage {
	String name;
	int passengerCapacity;
	ArrayList<Passenger> passengerList;
	ArrayList<Destination> destinations;
	
	public TravelPackage(String name,
			int passengerCapacity,
			ArrayList<Passenger> passengerList,
			ArrayList<Destination> destinations
			) {
		
		this.name=name;
		this.passengerCapacity=passengerCapacity;
		this.passengerList=passengerList;
		this.destinations=destinations;
	}
	
	public TravelPackage(String name, int passengerCapacity) {
		this.name=name;
		this.passengerCapacity=passengerCapacity;
		this.passengerList= new ArrayList<Passenger>();
		this.destinations = new ArrayList<Destination>();
	}
	
	public Destination getDestinationByName(String name) {
		return destinations.stream().filter(destination->destination.getName().equals(name)).findAny().get();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<Passenger> getPassengerList() {
		return passengerList;
	}
	
	public void addPassenger(Passenger passenger) throws Exception{
		if(passengerList.size()>=passengerCapacity) {
			throw new Exception("Capacity of package reached");
		}
		passengerList.add(passenger);
	}

	public void setPassengerList(ArrayList<Passenger> passengerList) {
		this.passengerList = passengerList;
	}
	
	public int getCurrentStrength() {
		return passengerList.size();
	}

	public int getPassengerCapacity() {
		return passengerCapacity;
	}

	public void setPassengerCapacity(int passengerCapacity) {
		this.passengerCapacity = passengerCapacity;
	}
	
	public ArrayList<Destination> getDestinations() {
		return destinations;
	}

	public void setdestinations(ArrayList<Destination> destinations) {
		this.destinations = destinations;
	}
	
	public void addDestination(Destination dest) {
		destinations.add(dest);
	}
	
}
